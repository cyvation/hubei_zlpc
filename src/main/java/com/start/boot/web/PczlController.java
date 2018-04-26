package com.start.boot.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.start.boot.common.MessageResult;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.domain.YX_PC_PCZL;
import com.start.boot.query.PczlQuery;
import com.start.boot.service.PczlService;
import com.start.boot.service.SystemCoreConfigService;
import com.start.boot.support.utils.EasyUIHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by lei on 2018/1/27.
 */
@RestController
@RequestMapping("/material")
public class PczlController extends ArchivesSystemBaseController {

    @Autowired
    SystemCoreConfigService systemCoreConfigService;
    @Autowired
    private PczlService pczlService;

    @ApiOperation("添加/编辑")
    @PostMapping("/addOrEdit")
    public MessageResult addOrEdit(@RequestBody YX_PC_PCZL pczl) {

        MessageResult messageResult ;
        try{
            pczl.setDwbm(getCurrentDwbm());
            pczl.setDwmc(getCurrentDwmc());
            pczl.setGh(getCurrentGh());
            pczl.setMc(getCurrentMC());

            YX_PC_PCZL result = null;
            if(StringUtils.isEmpty(pczl.getBh())){
                result = pczlService.insertPczl(pczl);
            } else {
                result = pczlService.updatePczl(pczl);
            }
            messageResult = new MessageResult("添加成功",200, result);
        }catch (Exception e) {
            messageResult = new MessageResult(e.getMessage(),500,"添加失败");
            super.errMsg(e.getMessage(),pczl.toString(),e);
        }

        return messageResult;
    }

    @ApiOperation("上传文件")
    @PostMapping("/uploadFile")
    public MessageResult uploadFile(HttpServletRequest request, MultipartFile file, String materialtype, String savePath) {

        MessageResult messageResult;

        try {
            String wzbsPath = SystemConfiguration.wzbsPath;
            String pczlPath = systemCoreConfigService.getSystemConfigValue("pczl_file_path");
            String originalFilename = file.getOriginalFilename();
            String realSavePath = pczlPath;
            String dwbm = getCurrentDwbm();
            String thisYear = String.valueOf(LocalDate.now().getYear());
            if ("null".equals(savePath)) {
                // 判断上传资料所属的功能模块
                if ("1".equals(materialtype)) { //1.典型案例
                    realSavePath += "DXAL" + "/" + dwbm + "/" + thisYear + "/";
                } else if ("2".equals(materialtype)) { //2.评查规定
                    realSavePath += "PCGD" + "/" + dwbm + "/" + thisYear + "/";
                } else { //3.新法速递
                    realSavePath += "XFSD" + "/" + dwbm + "/" + thisYear + "/";
                }
            } else {
                realSavePath += savePath + "/" + dwbm + "/" + thisYear + "/";
            }
            realSavePath += UUID.randomUUID();
            String path = wzbsPath + realSavePath;
            File file1 = new File(path + "/" + originalFilename);
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            // 上传文件
            file.transferTo(file1);

            messageResult = new MessageResult("上传成功", 200, realSavePath + "/" + file1.getName());

        } catch (Exception e) {
            messageResult = new MessageResult("上传失败", 500, e.getMessage());
        }

        return messageResult;
    }


    @ApiOperation("获取案子列表")
    @RequestMapping("/getPczl")
    public String getPczl(PczlQuery pczl) {

        // 响应内容
        String result = "";
        try{

            pczl.setDwbm(getCurrentDwbm());
            pczl.setGh(getCurrentGh());

            PageHelper.startPage(pczl.getPage(),pczl.getRows());
            List<Map> list = pczlService.getPczl(pczl);
            PageInfo page = new PageInfo(list);
            List pageList = page.getList();

            result = EasyUIHelper.buildDataGridDataSource(pageList, Math.toIntExact(page.getTotal()));
        } catch (Exception e) {
            super.errMsg(e.getMessage(),"获取案子列表失败",e);
            result = failure(e.getMessage(),"获取案子列表失败");
        }

        return result;

    }

    @ApiOperation("删除典型案子列表")
    @PostMapping("/delPczlByBh")
    public MessageResult delPczlByBh(String bh) {

        MessageResult messageResult ;
        try{
            boolean isSuccess = pczlService.delPczl(bh);

            if (isSuccess) {
                messageResult = new MessageResult("删除案子列表成功",200);
            }else {
                messageResult = new MessageResult("删除案子列表失败",500);
            }
        }catch (Exception e) {
            messageResult = new MessageResult("删除案子列表失败",500,e.getMessage());
            super.errMsg("删除案子失败",bh,e);
        }

        return messageResult;

    }

    @ApiOperation("查看典型案列")
    @GetMapping("/getPczlByBh")
    public MessageResult getPczlByBh(String bh) {

        MessageResult messageResult;

        try{
            YX_PC_PCZL pczl = pczlService.getPczlByBh(bh);
            messageResult = new MessageResult("查看案列成功",200,pczl);
        }catch (Exception e) {
            messageResult = new MessageResult("删除案子失败",500,e.getMessage());
            super.errMsg("查看典型案子失败",bh,e);
        }

        return messageResult;
    }

}
