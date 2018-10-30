package com.start.boot.web;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.start.boot.common.MessageResult;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.dao.ajpc.YxPcJzwjMapper;
import com.start.boot.domain.Message;
import com.start.boot.domain.Param_Jzwj;
import com.start.boot.domain.YxPcJzwj;
import com.start.boot.domain.YxPcSp;
import com.start.boot.query.ReportQuery;
import com.start.boot.service.MessageService;
import com.start.boot.service.PcReportService;
import com.start.boot.service.PcService;
import com.start.boot.support.utils.EasyUIHelper;
import com.start.boot.support.utils.FileUtils;
import com.start.boot.utils.ZipUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 评查报告控制器
 */

@RestController
@RequestMapping("/pcreport")
public class PcReporController extends ArchivesSystemBaseController{

    @Autowired
    private PcReportService pcReportService;

    @Autowired
    YxPcJzwjMapper yxPcJzwjMapper;

    @Autowired
    MessageService messageService;

    @Autowired
    PcService pcService;


    @ApiOperation("获取文书模板")
    @RequestMapping("/getPcbgMb")
    public MessageResult getPcbgMb(String pcflbm, String wsmblb) {

        MessageResult messageResult;
        try{
            List<Map> list =pcReportService.getPcbgMb(pcflbm,wsmblb);
            String s = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "WSMBBH", "FBM", "WSMBMC", "-1");
            messageResult = new MessageResult("获取文书报告模板成功",200,s);
        }catch (Exception e){
            messageResult = new MessageResult(e.getMessage(),500,"获取文书报告模板失败");
        }

        return messageResult;
    }

    @ApiOperation("生成报告")
    @PostMapping("/generateDoc")
    public MessageResult generateDoc(@RequestBody YxPcJzwj yxPcJzwj) {
        MessageResult messageResult;
        try{
            yxPcJzwj.setDwbm(getCurrentDwbm());
            yxPcJzwj.setNzrdwbm(getCurrentDwbm());
            yxPcJzwj.setNzrdwmc(getCurrentDwmc());
            yxPcJzwj.setNzrgh(getCurrentGh());
            yxPcJzwj.setNzrxm(getCurrentMC());
            String wscflj =  pcReportService.generateDoc(yxPcJzwj);

            String filePath =  wscflj;

            // 插入记录
            yxPcJzwj.setWscflj(wscflj);
            yxPcJzwj.setWjkzm(".doc");
            Param_Jzwj param_jzwj =new Param_Jzwj();

            BeanUtils.copyProperties(param_jzwj,yxPcJzwj);

            Param_Jzwj param_jzwj1 = pcService.addJzwj(param_jzwj);
            yxPcJzwj.setJzwjbh(param_jzwj.getJzwjbh());
            // 更新市院报告报送状态
            if (yxPcJzwj.getNzrdwbm().equalsIgnoreCase(SystemConfiguration.djdwbm)){
                yxPcJzwj.setBszt("9");
                pcReportService.updateJzwj(yxPcJzwj);
            }

            messageResult = new MessageResult(200, filePath);
        }catch (Exception e){
            messageResult = new MessageResult(e.getMessage(),500,"生成报告失败");
        }

        return messageResult;
    }

    @ApiOperation("获取报告文件列表")
    @GetMapping("/getReportList")
    public MessageResult getReportList(ReportQuery reportQuery) {
        MessageResult messageResult;

        try{
            // 市院人登录：
            if (SystemConfiguration.djdwbm.equalsIgnoreCase(getCurrentDwbm())){
                reportQuery.setBszt("9");
            }else{
                reportQuery.setBszt("0");
            }

            PageHelper.startPage(reportQuery.getPage(),reportQuery.getRows());
            List<Map> list = pcReportService.getReportList(reportQuery);
            PageInfo pageInfo = new PageInfo(list);

            String s = EasyUIHelper.buildDataGridDataSource(pageInfo.getList(), Math.toIntExact(pageInfo.getTotal()));
            messageResult = new MessageResult(200,s);
        }catch (Exception e){
            messageResult = new MessageResult(e.getMessage(),500,"获取报告文件列表失败");
        }

        return messageResult;
    }

    @ApiOperation("下载报告")
    @RequestMapping("/downloadDoc")
    public MessageResult downloadDoc(String jzwjbhj,String modelName,HttpServletResponse response){
        MessageResult messageResult =null;

        String[] split = jzwjbhj.split(",");


        String desc = "";

        // 单个文件：
        if (split.length == 1){
                YxPcJzwj yxPcJzwj = yxPcJzwjMapper.selectByPrimaryKey(split[0]);
            File file = new File(SystemConfiguration.wzbsPath + SystemConfiguration.pcfaPath + yxPcJzwj.getWscflj());
            if (!file.exists()){
                return new MessageResult(500,"文件不存在");
            }

            String filename =  yxPcJzwj.getWjmc();
            String savePath = SystemConfiguration.lsbgPath + filename + ".doc";
            System.out.println("保存路径为" + savePath);
            try {
                FileUtils.copyFile( file,new File(SystemConfiguration.wzbsPath + savePath));
                messageResult = new MessageResult(200,savePath);
            } catch (IOException e) {
                e.printStackTrace();
                messageResult =  new MessageResult(e.getMessage(),500,"下载失败");
            }
            // return messageResult;
        }


        // 多个文件：
        if (split.length >=2){
            //指定压缩包名称(报告模板名称+ 单位名称 + 当前时间)
            String zipName = modelName + getCurrentDwmc() + DateTime.now().toString("yyy-MM-dd-HH-mm-ss");
            // 错误计数：
            int errorCount =0;
            for(String jzwjbh : split){
                YxPcJzwj yxPcJzwj = yxPcJzwjMapper.selectByPrimaryKey(jzwjbh);

                File file = new File(SystemConfiguration.wzbsPath + SystemConfiguration.pcfaPath + yxPcJzwj.getWscflj());
                if (!file.exists()){
                    errorCount++;
                    continue;
                }

                String filename = yxPcJzwj.getWjmc();
                // 父文件夹：
                String parentFile = SystemConfiguration.wzbsPath + SystemConfiguration.lsbgPath + zipName;
                String newFileName = parentFile + "/" + filename + ".doc";

                try {
                    FileUtils.copyFile( file,new File(newFileName));
                } catch (IOException e) {
                    e.printStackTrace();
                    errorCount ++;
                }
            }

            // 压缩
            // 指定压缩存放位置
            desc = SystemConfiguration.lsbgPath +  zipName + ".zip";
            ZipUtils.compress(SystemConfiguration.wzbsPath + SystemConfiguration.lsbgPath + "/" + zipName,SystemConfiguration.wzbsPath + desc);
            if (errorCount ==split.length){
                messageResult = new MessageResult("文件全部失效",500,"下载失败");
            }else {
                messageResult = new MessageResult(200,desc);
            }

        }
        return messageResult;
    }


    @ApiOperation("删除评查报告--软删除")
    @RequestMapping("/delPcbg")
    public MessageResult delPcbg(YxPcJzwj yxPcJzwj){

        MessageResult messageResult;
        try {
            pcReportService.delPcbg(yxPcJzwj);
            messageResult = new MessageResult("删除成功", 200);
        } catch (Exception e) {
            e.printStackTrace();
            messageResult = new MessageResult("删除失败", 500);
        }
        return messageResult;


    }

    @ApiOperation("获取活动下的评查报告")
    @GetMapping("/getReportHd")
    public MessageResult getReportHd(ReportQuery reportQuery){
        MessageResult messageResult;

        try{
            // 市院人登录：
            if (SystemConfiguration.djdwbm.equalsIgnoreCase(getCurrentDwbm())){
                reportQuery.setBszt("9");
            }else{
                reportQuery.setBszt("0");
            }

            PageHelper.startPage(reportQuery.getPage(),reportQuery.getRows());
            List<Map> list = pcReportService.getReportHd(reportQuery);
            PageInfo pageInfo = new PageInfo(list);

            String s = EasyUIHelper.buildDataGridDataSource(pageInfo.getList(), Math.toIntExact(pageInfo.getTotal()));
            messageResult = new MessageResult(200,s);
        }catch (Exception e){
            messageResult = new MessageResult(e.getMessage(),500,"获取活动列表失败");
        }
        return messageResult;

    }

    @ApiOperation("获取评查报告审批人")
    @GetMapping("/getPcsp")
    public MessageResult getPcsp(String spjsbm){

        List<Map> ryList =  pcReportService.getPcsp(spjsbm,getCurrentDwbm(),getCurrentGh());
        if (!CollectionUtils.isEmpty(ryList)){
            String s = EasyUIHelper.buildDataGridDataSource(ryList, ryList.size());
            return new MessageResult(200,s);
        }

        return new MessageResult(500,"没数据");

    }

    @ApiOperation("发送审批--添加审批记录")
    @RequestMapping("/sendPcbgSp")
    public MessageResult sendPcbgSp(YxPcSp yxPcSp) {

        MessageResult messageResult;
        try {
            boolean isSuccess = pcReportService.sendPcbgSp(yxPcSp);
            messageResult = new MessageResult("送审成功", 200, isSuccess);

//          报告报审，发送消息给领导
            Message message = new Message();
            message.setJsrgh(yxPcSp.getSprgh());
            message.setJsrdwbm(yxPcSp.getSprdwbm());

            message.setDwbm(getCurrentDwbm());
            message.setXxbt("评查报告待审批");
            message.setXxnr(yxPcSp.getSprxm()+" 您好，您有新的报告待审批");
            messageService.save(message);

        } catch (Exception e) {
            e.printStackTrace();
            messageResult = new MessageResult("送审失败", 500);
        }
        return messageResult;
    }

    @ApiOperation("报告审批")
    @RequestMapping("/updateSpyj")
    public MessageResult updateSpyj(YxPcSp yxPcSp){

        MessageResult messageResult;
        try {
            pcReportService.updateSpyj(yxPcSp);
            messageResult = new MessageResult("审批成功", 200);


            YxPcSp yxPcSpdetail = pcReportService.getSpInfo(yxPcSp.getPcspbm());
//            报告审批完成，发送消息给送审人
            Message message = new Message();
            message.setJsrgh(yxPcSpdetail.getSsrgh());
            message.setJsrdwbm(yxPcSpdetail.getSsrdwbm());

            message.setDwbm(getCurrentDwbm());
            message.setXxbt("评查报告已经审批");
            message.setXxnr(yxPcSpdetail.getSsrxm()+" 您好，您送审的报告已经被处理");
            messageService.save(message);

        } catch (Exception e) {
            e.printStackTrace();
            messageResult = new MessageResult("审批失败", 500);
        }
        return messageResult;
    }

    @ApiOperation("获取评查报告详细信息")
    @RequestMapping("/getPcbgDetailInfo")
    public MessageResult getPcbgDetailInfo(YxPcJzwj yxPcJzwj){

        MessageResult messageResult;
        try {
            YxPcJzwj pcbgDetailInfo = pcReportService.getPcbgDetailInfo(yxPcJzwj);
            messageResult = new MessageResult("获取评查报告详细成功", 200,pcbgDetailInfo);
        } catch (Exception e) {
            e.printStackTrace();
            messageResult = new MessageResult("获取评查报告详细失败", 500);
        }
        return messageResult;
    }

    @ApiOperation("获取审批报告列表")
    @RequestMapping("/getPcspbgInfo")
    public MessageResult getPcspbgInfo(YxPcSp yxPcSp){

        MessageResult messageResult;
        try {
            List<Map> list =  pcReportService.getPcspbgInfo(yxPcSp);
            String result = EasyUIHelper.buildDataGridDataSource(list, list.size());
            messageResult = new MessageResult("获取评查报告详细成功", 200, JSON.parse(result));
        } catch (Exception e) {
            e.printStackTrace();
            messageResult = new MessageResult("获取评查报告详细失败", 500);
        }
        return messageResult;


    }


    @ApiOperation("再次发送审批")
    @PostMapping("/sendAgainPcsp")
    public MessageResult sendAgainPcsp(YxPcSp yxPcSp) {

        MessageResult messageResult;
        try{

            pcReportService.updateSpyj(yxPcSp);
            YxPcSp yxPcSpdetail = pcReportService.getSpInfo(yxPcSp.getPcspbm());
//            报告审批完成，发送消息给送审人
            Message message = new Message();
            message.setJsrgh(yxPcSpdetail.getSsrgh());
            message.setJsrdwbm(yxPcSpdetail.getSsrdwbm());

            message.setDwbm(getCurrentDwbm());
            message.setXxbt("评查报告已经审批");
            message.setXxnr(yxPcSpdetail.getSsrxm()+" 您好，您送审的报告已经被处理");
            messageService.save(message);


            pcReportService.sendPcbgSp(yxPcSp);
            //报告报审，发送消息给领导
            Message message2 = new Message();
            message2.setJsrgh(yxPcSp.getSprgh());
            message2.setJsrdwbm(yxPcSp.getSprdwbm());

            message2.setDwbm(getCurrentDwbm());
            message2.setXxbt("评查报告待审批");
            message2.setXxnr(yxPcSp.getSprxm()+" 您好，您有新的报告待审批");
            messageService.save(message2);

            messageResult = new MessageResult(200,"送审成功");
        }catch (Exception e){
            messageResult = new MessageResult(e.getMessage(),500,"送审失败");
        }

        return messageResult;
    }

    @ApiOperation("报送市院")
    @PostMapping("/sendDoc")
    public MessageResult sendDoc(String jzwjbh){

        MessageResult messageResult;
        try{
            pcReportService.sendDoc(jzwjbh);
            messageResult = new MessageResult(200,"报送成功");
        }catch (Exception e){
            messageResult = new MessageResult(e.getMessage(),500,"报送失败");
        }
        return messageResult;
    }

    @ApiOperation("退回报告审批")
    @PostMapping("/backDoc")
    public MessageResult backDoc(YxPcSp yxPcSp){
        MessageResult messageResult;
        try{
            // 1. 干掉审批记录。2.添加日志.3.通知报告创建人
            YxPcJzwj yxPcJzwj = yxPcJzwjMapper.selectByPrimaryKey(yxPcSp.getSpwjbm());
            pcReportService.backDoc(yxPcSp,yxPcJzwj);

            // 报告退回，发送消息给送审人
            Message message = new Message();
            message.setJsrgh(yxPcJzwj.getNzrgh());
            message.setJsrdwbm(yxPcJzwj.getNzrdwbm());

            message.setDwbm(getCurrentDwbm());
            message.setXxbt("评查报告已被退回");
            message.setXxnr(yxPcJzwj.getNzrxm()+" 您好，您送审的报告【" + yxPcJzwj.getWjmc() + "】已经被" + yxPcSp.getSprxm() + "退回");
            messageService.save(message);

            messageResult =  new MessageResult(200,"报告退回成功");
        }catch (Exception e){
            e.printStackTrace();
            messageResult =  new MessageResult(e.getMessage(),500);
        }

        return messageResult;
    }

}
