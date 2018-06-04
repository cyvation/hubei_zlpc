package com.start.boot.web;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.start.boot.common.MessageResult;
import com.start.boot.common.Param_Pager;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.domain.*;
import com.start.boot.query.DbrwQuery;
import com.start.boot.query.Query;
import com.start.boot.service.PcAjService;
import com.start.boot.service.PcService;
import com.start.boot.service.PcmbService;
import com.start.boot.support.utils.*;
import com.start.boot.utils.QueryUtils;
import com.start.boot.utils.WebServiceUtils;
import com.start.boot.validate.SfDto;
import com.start.boot.validate.SfInvoke;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 评查管理控制器(评查活动+评查案件+评查人员)
 * Created by lei on 2017/10/30.
 */
@RestController
@RequestMapping("/manage")
public class PcController extends ArchivesSystemBaseController {

    @Autowired
    private PcService pcService;
    @Autowired
    private WebServiceUtils webServiceUtils;
    @Autowired
    private PcAjService pcAjService;
    @Autowired
    PcmbService pcmbService;
    @Autowired
    SfInvoke sfInvoke;
    @Autowired
    QueryUtils queryUtils;


    /**
     * 获取评查分类列表
     *
     * @return
     */
    @RequestMapping("/getpcfl")
    public String getPcfl() {
        //设置相应内容：
        String result = "";

        try {
            List<Map> list = pcService.getPcfl();
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "PCFLBM", "FBM", "PCFLMC", "-1");
        } catch (Exception e) {
            super.errMsg("获取评查分类列表失败", null, e);
        }

        return result;
    }

    /**
     * 获取评查活动
     *
     * @param
     * @return
     */
    @RequestMapping("/get_pchd")
    public String getPchd(String pcflbm) {
        //设置相应内容：
        String result = "";

        try {
            Param_Hd param = new Param_Hd();
            param.setPcdwbm(getCurrentDwbm());
            param.setPcflbm(pcflbm);

            List<Map> list = pcService.getPchd(param);
            result = EasyUIHelper.buildTreeDataSourceWithoutIconCol(list, "PCHDBM", "PCFLBM", "PCHDMC", pcflbm);
        } catch (Exception e) {
            super.errMsg("获取评查活动失败", pcflbm, e);
            result = failure(e.getMessage(), "获取评查活动失败");
        }

        return result;
    }

    /**
     * 获取评查人员库人员列表
     *
     * @param json
     * @return
     */
    @RequestMapping("/getPckryAll")
    public String getPckryAll(String json) {
        String result = "";

        try {
            Param_Ryk ryk = FastJsonUtils.toObject(Param_Ryk.class, json);
            ryk.setRykdwbm(getCurrentDwbm());
            Param_Ryk pckry = pcService.getPckryAll(ryk);
            result = success(pckry.getList(), "获取评查人员库人员列表成功");

        } catch (Exception e) {
            super.errMsg("获取评查人员库人员列表失败", json, e);
            result = failure(e.getMessage(), "获取评查人员库人员列表失败");
        }

        return result;
    }

    /**
     * 新增评查方案
     *
     * @param json
     * @return
     */
    @RequestMapping("/addPcfa")
    public String addPcfa(String json) {
        String result = "";

        try {
            Param_Pcfa param = FastJsonUtils.toObject(Param_Pcfa.class, json);
            param.setPcdwbm(getCurrentDwbm());
            param.setCjrdwbm(getCurrentDwbm());
            param.setCjrdwmc(getCurrentDwmc());
            param.setCjrgh(getCurrentGh());
            param.setCjrmc(getCurrentMC());

            Param_Pcfa data = pcService.addPcfa(param);
            result = success(data, "新增评查方案成功");

        } catch (Exception e) {
            super.errMsg("新增评查方案失败", json, e);
            result = failure(e.getMessage(), "新增评查方案失败");
        }

        return result;
    }

    /**
     * 修改评查活动信息
     *
     * @param json
     * @return
     */
    @RequestMapping("/updPchd")
    public String updPchd(String json) {
        String result = "";

        try {
            Param_Pcfa param = FastJsonUtils.toObject(Param_Pcfa.class, json);

            boolean isSuccess = pcService.updPchd(param);
            result = success(isSuccess, "修改评查活动信息成功");
        } catch (Exception e) {
            super.errMsg("修改评查活动信息失败", json, e);
            result = failure(e.getMessage(), "修改评查活动信息失败");
        }

        return result;
    }


    /**
     * 新增评查分组
     *
     * @return
     */
    @RequestMapping("/addPcz1")
    public String addPcfz1() {
        String result = "调用成功";

        return result;
    }

    /**
     * 新增评查分组
     *
     * @param json
     * @return
     */
    @RequestMapping("/addPcz")
    public String addPcfz(String json) {
        String result = "";

        try {
            Param_Pcz param = FastJsonUtils.toObject(Param_Pcz.class, json);
            String pczbm = pcService.addPcfz(param);

            result = success(pczbm, "新增评查分组成功");
        } catch (Exception e) {
            super.errMsg("新增评查分组失败", json, e);
            result = failure(e.getMessage(), "新增评查分组失败");
        }

        return result;
    }

    /**
     * 校验评查组是否已分配有评查案件
     *
     * @param json
     * @return
     */
    @RequestMapping("/valPcz")
    public String valPcfz(String json) {
        String result = "";

        try {
            Param_Pcz param = FastJsonUtils.toObject(Param_Pcz.class, json);
            String msg = pcService.valPcfz(param);
            result = success(msg, "校验评查组是否已分配有评查案件成功");
        } catch (Exception e) {
            super.errMsg("校验评查组是否已分配有评查案件失败", json, e);
            result = failure(e.getMessage(), "校验评查组是否已分配有评查案件失败");
        }

        return result;
    }

    /**
     * 删除评查分组
     *
     * @param json
     * @return
     */
    @RequestMapping("/delPcz")
    public String delPcfz(String json) {
        String result = "";

        try {
            Param_Pcz param = FastJsonUtils.toObject(Param_Pcz.class, json);
            boolean isSuccess = pcService.delPcfz(param);
            result = success(isSuccess, "删除评查分组成功");
        } catch (Exception e) {
            super.errMsg("删除评查分组失败", json, e);
            result = failure(e.getMessage(), "删除评查分组失败");
        }

        return result;
    }

    /**
     * 校验评查员是否已分配有评查案件
     *
     * @param json
     * @return
     */
    @RequestMapping("/valXzry")
    public String valXzry(String json) {
        String result = "";

        try {
            Param_Pcy param = FastJsonUtils.toObject(Param_Pcy.class, json);
            String tsxx = pcService.valXzry(param);
            result = success(tsxx, "校验评查员是否已分配有评查案件成功");
        } catch (Exception e) {
            super.errMsg("校验评查员是否已分配有评查案件失败", null, e);
            result = failure(e.getMessage(), "校验评查员是否已分配有评查案件失败");
        }

        return result;
    }


    /**
     * 评查案件分配
     *
     * @param json
     * @return
     */
    @RequestMapping("/assignCase")
    public String assignCase(String json) {
        String result = "";

        try {
            List<Param_Pcaj> list = FastJsonUtils.toList(Param_Pcaj.class, json);
            Object userInfo =  getCurrentUser();
            if(userInfo!=null){
                boolean isSuccess = pcService.assignCase((User) userInfo, list);
                result = success(isSuccess, "评查案件分配成功");
            }
        } catch (Exception e) {
            super.errMsg("评查案件分配失败", json, e);
            result = failure(e.getMessage(), "评查案件分配失败");
        }
        return result;
    }


    /**
     * 删除评查活动
     *
     * @param pchdbm
     * @return
     */
    @RequestMapping("/delPchd")
    public String delPchd(String pchdbm) {
        String result = "";

        try {
            boolean isSuccess = pcService.delPchd(pchdbm);
            result = success(isSuccess, "删除评查活动成功");
        } catch (Exception e) {
            super.errMsg("删除评查活动失败", pchdbm, e);
            result = failure(e.getMessage(), "删除评查活动失败");
        }

        return result;
    }

    /**
     * 获取评查人员库人员列表
     *
     * @param json
     * @return
     */
    @RequestMapping("/get_pckry")
    public String getPckry(String json) {
        String result = "";

        try {
            Param_Ryk ryk = FastJsonUtils.toObject(Param_Ryk.class, json);
            ryk.setRykdwbm(getCurrentDwbm());
            ryk.setPage(parsePage(getParameter("page")));
            ryk.setRows(parseRows(getParameter("rows")));

            Param_Ryk pckry = pcService.getPckry(ryk);
            result = EasyUIHelper.buildDataGridDataSource(pckry.getList(), pckry.getCount());

        } catch (Exception e) {
            super.errMsg("获取评查人员库人员列表失败", json, e);
            result = failure(e.getMessage(), "添加评查活动失败");
        }

        return result;
    }

    /**
     * 获取当前单位组织机构人员和人员库的人员列表
     *
     * @param json
     * @return
     */
    @RequestMapping("/get_zzjgry")
    public String get_zzjgry(String json) {
        String result = "";

        try {
            Param_Ryk ryk = FastJsonUtils.toObject(Param_Ryk.class, json);

            ryk.setRykdwbm(getCurrentDwbm());
            ryk.setPage(parsePage());
            ryk.setRows(parseRows());

            Param_Ryk pckry = pcService.getZzjgry(ryk);
            result = EasyUIHelper.buildDataGridDataSource(pckry.getList(), pckry.getCount());

        } catch (Exception e) {
            super.errMsg("获取当前单位组织机构人员和人员库的人员列表失败", json, e);
            result = failure(e.getMessage(), "获取当前单位组织机构人员和人员库的人员列表失败");
        }

        return result;
    }



    /**
     * 从组织机构中提取人员到人员库的人员列表
     *
     * @param json
     * @return
     */
    @RequestMapping("/add_pcryk")
    public String add_pcryk(String json) {
        String errmsg = "";

        try {
            List<Param_Ryk> list = FastJsonUtils.toList(Param_Ryk.class, json);

            List<Param_Ryk> pckry = pcService.add_pcryk(list, getCurrentDwbm());
        } catch (Exception e) {
            super.errMsg("从组织机构中提取人员到人员库的人员列表失败", json, e);
            errmsg = "添加评查人员失败。" + e.getMessage();
        }

        return AjaxHelper.buildResponseDataSource(errmsg, true);
    }

    /**
     * 删除人员库列表人员
     *
     * @param json
     * @return
     */
    @RequestMapping("/del_rykry")
    public String del_rykry(String json) {
        String errmsg = "";

        try {
            List<Param_Ryk> list = FastJsonUtils.toList(Param_Ryk.class, json);

            List<Param_Ryk> pckry = pcService.del_rykry(list, getCurrentDwbm());
        } catch (Exception e) {
            super.errMsg("删除人员库列表人员失败", json, e);
            errmsg = "删除人员库列表人员失败" + e.getMessage();
        }

        return AjaxHelper.buildResponseDataSource(errmsg, true);
    }

    /**
     * 根据条件查询评查活动
     *
     * @param pcflbm
     * @param cjsjStart
     * @param cjsjend
     * @return
     */
    @RequestMapping("/query_PC_HD")
    public String getPchbList(String pcflbm,Integer rows,Integer page, String cjsjStart, String cjsjend) throws ParseException {
        Page<Object> objects = PageHelper.startPage(page, rows);
        SimpleDateFormat sjsjStrat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Map> resultMap = new ArrayList<>();
        Date start=null;
        if(StringUtils.isNotEmpty(cjsjStart)){
            start = sjsjStrat.parse(cjsjStart);
        }
        Date end=null;
        if(StringUtils.isNotEmpty(cjsjend)){
            DateTime dateTime=new DateTime(cjsjend);
             end   = dateTime.plusDays(1).toLocalDate().toDate();
        }
        YX_PC_HD param = new YX_PC_HD();
        param.setPCDWBM(getCurrentDwbm());
        param.setPCFLBM(pcflbm);
        List<YX_PC_HD> query = pcmbService.query(param, start, end);
        query.stream().forEach(data -> {
            try {
                Map describe = BeanUtils.describe(data);
                resultMap.add(describe);
            } catch (Exception e) {
                e.printStackTrace();
                failure("没有查询到相关信息", "没有查询到相关信息");
            }
        });
        long total = objects.getTotal();
        String result = EasyUIHelper.buildDataGridDataSource(resultMap, (int) total);
        return result;
    }

    /**
     * 查询单个评查活动的详情信息
     *
     * @param pchdbm
     * @return
     */
    @RequestMapping("/getPcyInfo")
    public String getPcyInfo(String pchdbm) {
        try {
            Yx_Pc_Param_Pcfa pchdInfo = pcmbService.getPchdInfo(pchdbm);
            return success(pchdInfo, "成功");
        } catch (Exception e) {
            e.printStackTrace();
            return failure(e.getMessage(), "没有查询到相关信息");
        }
    }
    /**
     * 生成临时报告
     *
     * @param json
     * @return
     */
    @RequestMapping("/generateTempDoc")
    public String generateTempDoc(String json) {
        String result = "";

        try {
            Param_Pcfaws param = FastJsonUtils.toObject(Param_Pcfaws.class, json);

            // 调用WebService/GenerateReport生成评查报告
            Map params = new HashMap();
            params.put("pchdbm", param.getPczybm());
            params.put("mblj", param.getWsmblj());
            params.put("pcslbms", param.getPcslbm());
            params.put("dwbm", getCurrentDwbm());
            params.put("gh", getCurrentGh());
            params.put("type", param.getPczylx()); //0.生成在评查活动文件夹，1.生成在评查案件文件夹
            Object data = webServiceUtils.post(SystemConfiguration.webservice + "/GenerateReport", params);
            if (data == null) {
                throw new Exception("调用WebService生成评查文书失败，返回为空。");
            }
            // 解析XML文档
            List<String> list = webServiceUtils.parseXml(data.toString());
            if (org.apache.commons.lang3.StringUtils.isNoneEmpty(list.get(0))) {
                throw new Exception("调用WebService生成评查文书失败，" + list.get(0));
            }

            // 临时报告文件路径
            String filePath = SystemConfiguration.lsbgPath + list.get(1);
            if (!(SystemConfiguration.fwbgPath + list.get(1)).equals(SystemConfiguration.wzbsPath + filePath)) {
                FileUtils.copyFile(new File(SystemConfiguration.fwbgPath + list.get(1)), new File(SystemConfiguration.wzbsPath + filePath));
            }

            result = success(filePath, list.get(1));
        } catch (Exception e) {
            super.errMsg("生成评查文书失败", json, e);
            result = failure(e.getMessage(), "生成评查文书失败");
        }

        return result;
    }

    /**
     * 生成评查方案
     *
     * @param json
     * @return
     */
    @RequestMapping("/generateDoc")
    @Transactional
    public String generateDoc(String json) {
        String result = "";

        try {
            Param_Jzwj param = FastJsonUtils.toObject(Param_Jzwj.class, json);
            param.setDwbm(param.getPchdbm().substring(0, 6));
            param.setNzrdwbm(getCurrentDwbm());
            param.setNzrdwmc(getCurrentDwmc());
            param.setNzrgh(getCurrentGh());
            param.setNzrxm(getCurrentMC());

            // 调用WebService/GenerateReport生成评查报告
            Map params = new HashMap();
            params.put("pchdbm", param.getPchdbm());
            params.put("mblj", param.getWsmblj());
            params.put("pcslbms", param.getPcslbm());
            params.put("dwbm", param.getNzrdwbm());
            params.put("gh", param.getNzrgh());
            params.put("type", "0"); //0.生成在评查活动文件夹，1.生成在评查案件文件夹
            Object data = webServiceUtils.post(SystemConfiguration.webservice + "/GenerateReport", params);
            if (data == null) {
                throw new Exception("调用WebService生成评查方案失败，返回为空。");
            }
            // 解析XML文档
            List<String> list = webServiceUtils.parseXml(data.toString());
            if (org.apache.commons.lang3.StringUtils.isNoneEmpty(list.get(0))) {
                throw new Exception("调用WebService生成评查方案失败，" + list.get(0));
            }

            // 报告文件路径
            String filePath = list.get(1);
            // 评查活动存放路径：评查发起单位编码/年份/评查指标编码/GUID
            String fileSavePath = SystemConfiguration.pcfaPath + filePath;
            FileUtils.copyFile(new File(SystemConfiguration.fwbgPath + filePath), new File(SystemConfiguration.wzbsPath + fileSavePath));

            // 添加评查卷宗信息
            param.setWscflj(filePath);
            param.setWjkzm(FileUtils.getFileExtension(param.getWscflj()));
            // 删除Old方案(通过评查资源编码)
            pcService.delJzwjByPczybm(param);
            // 添加新方案
            Param_Jzwj info = pcService.addJzwj(param);

            result = success(fileSavePath, list.get(1));
        } catch (Exception e) {
            super.errMsg("生成评查方案失败", json, e);
            result = failure(e.getMessage(), "生成评查方案失败");
        }

        return result;
    }

    /**
     * 生成评查报告
     *
     * @param json
     * @return
     */
    @RequestMapping("/generateDocFile")
    public String generateDocFile(String json) {
        String result = "";

        try {
            Param_Jzwj param = FastJsonUtils.toObject(Param_Jzwj.class, json);
            param.setDwbm(param.getPchdbm().substring(0, 6));
            param.setNzrdwbm(getCurrentDwbm());
            param.setNzrdwmc(getCurrentDwmc());
            param.setNzrgh(getCurrentGh());
            param.setNzrxm(getCurrentMC());

            // 调用WebService/GenerateReport生成评查报告
            Map params = new HashMap();
            params.put("pchdbm", param.getPchdbm());
            params.put("mblj", param.getWsmblj());
            params.put("pcslbms", param.getPcslbm());
            params.put("dwbm", param.getNzrdwbm());
            params.put("gh", param.getNzrgh());
            params.put("type", param.getPczylx()); //0.生成在评查活动文件夹，1.生成在评查案件文件夹
            Object data = webServiceUtils.post(SystemConfiguration.webservice + "/GenerateReport", params);
            if (data == null) {
                throw new Exception("调用WebService生成评查文书失败，返回为空。");
            }
            // 解析XML文档
            List<String> list = webServiceUtils.parseXml(data.toString());
            if (org.apache.commons.lang3.StringUtils.isNoneEmpty(list.get(0))) {
                throw new Exception("调用WebService生成评查文书失败，" + list.get(0));
            }

            // 报告文件路径
            String filePath = list.get(1);
            String fileSavePath = "";
            switch (param.getPczylx())
            {
                case "0": // 评查活动存放路径：评查发起单位编码/年份/评查指标编码/GUID
                    fileSavePath = SystemConfiguration.pcfaPath + filePath;
                    break;
                case "1": // 评查案件：评查发起单位编码/年份/评查指标编码/评查受理编码/GUID
                    fileSavePath = SystemConfiguration.pcjzPath + filePath;
                    break;
                default:
                    break;
            }
            FileUtils.copyFile(new File(SystemConfiguration.fwbgPath + filePath), new File(SystemConfiguration.wzbsPath + fileSavePath));

            // 添加评查卷宗信息
            param.setWscflj(filePath);
            param.setWjkzm(FileUtils.getFileExtension(param.getWscflj()));
            Param_Jzwj info = pcService.addJzwj(param);

            result = success(fileSavePath, list.get(1));
        } catch (Exception e) {
            super.errMsg("生成评查文书失败", json, e);
            result = failure(e.getMessage(), "生成评查文书失败");
        }

        return result;
    }

    /**
     * 保存评查方案
     *
     * @param request
     * @return
     */
    @RequestMapping("/uploadFile")
    public String saveFile(HttpServletRequest request) {
        String result = "";
        String path = SystemConfiguration.wzbsPath + SystemConfiguration.pcfaPath + getParameter("wjlj");

        try {
            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (commonsMultipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
                Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();
                for (Map.Entry<String, MultipartFile> entry : entries) {
                    MultipartFile file = entry.getValue();
                    File newFile = new File(path);
                    File parentFile = newFile.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    file.transferTo(newFile);
                }
            }

        } catch (Exception e){
            result = e.getMessage();
            super.errMsg("保存评查方案失败。", path, e);
        }

        return result;
    }

    /**
     * 获取评查方案路径
     *
     * @param filePath
     * @return
     */
    @RequestMapping("/getPcfaPath")
    public String getPcfaPath(String filePath) {

        String result = SystemConfiguration.pcfaPath + filePath;
        /*if (!new File(SystemConfiguration.wzbsPath + result).exists()) {
            result = SystemConfiguration.lsbgPath + filePath;
            if (!new File(SystemConfiguration.wzbsPath + result).exists()) {
                result = "";
            }
        }*/

        return result;
    }

    /**
     * 添加卷宗文件
     *
     * @param json
     * @return
     */
    @RequestMapping("/addJzwj")
    public String addJzwj(String json) {
        String result = "";

        try {
            Param_Jzwj param = FastJsonUtils.toObject(Param_Jzwj.class, json);
            param.setDwbm(getCurrentDwbm());
            param.setNzrdwbm(getCurrentDwbm());
            param.setNzrdwmc(getCurrentDwmc());
            param.setNzrgh(getCurrentGh());
            param.setNzrxm(getCurrentMC());
            param.setWjkzm(FileUtils.getFileExtension(param.getWscflj()));

            Param_Jzwj data = pcService.addJzwj(param);

            result = success(data.getJzwjbh(), "添加卷宗文件成功");
        } catch (Exception e) {
            super.errMsg("添加卷宗文件失败", json, e);
            result = failure(e.getMessage(), "添加卷宗文件失败");
        }

        return result;
    }

    /**
     * 获取卷宗文件
     *
     * @param filePath
     * @return
     */
    @RequestMapping("/getFileStream")
    public byte[] getFileStream(String filePath) {
        try {
            return FileUtils.getBytes(new File(SystemConfiguration.wzbsPath + filePath));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加卷宗文件
     *
     * @param request
     * @return
     */
    @RequestMapping("/fileUpload")
    public String uploadFile(HttpServletRequest request,MultipartFile file) {

        String result  = "";
        String json = request.getParameter("json");
        try {
            if(file==null||file.getOriginalFilename() == null || file.getOriginalFilename() == ""){
                throw new Exception("获取到上传文件名为空。");
            }
            // 评查信息
            Param_Jzwj param = FastJsonUtils.toObject(Param_Jzwj.class, json);
            String wjkzm = FileUtils.getFileExtension(file.getOriginalFilename());
            String filePath = "";
            String fileSavePath = "";

            String pcdwbm = param.getPchdbm().substring(0, 6);
            //String pcflbm = param.getPchdbm().substring(6, 9);
            String year = param.getPchdbm().substring(9, 13).equalsIgnoreCase("0000") ? DateUtils.getCurrentYear() : param.getPchdbm().substring(9, 13);

            // 附件存放路径
            switch (param.getPczylx())
            {
                case "0": // 评查活动存放路径：评查发起单位编码/年份/评查指标编码/GUID
                    filePath = String.format("/%s/%s/%s/%s%s", pcdwbm, year, param.getPchdbm(), IDGenerateUtils.getCleanlyId(), wjkzm);
                    fileSavePath = SystemConfiguration.wzbsPath + SystemConfiguration.pcfaPath + filePath;
                    break;
                case "1": // 评查案件：评查发起单位编码/年份/评查指标编码/评查受理编码/GUID
                    filePath = String.format("/%s/%s/%s/%s/%s%s", pcdwbm, year, param.getPchdbm(), param.getPcslbm(), IDGenerateUtils.getCleanlyId(), wjkzm);
                    fileSavePath = SystemConfiguration.wzbsPath + SystemConfiguration.pcjzPath + filePath;
                    break;
                default:
                    break;
            }

            // 保存文件
            FileUploadResult fileUploadResult = new FileUploadResult();
            File newFile = new File(fileSavePath);
            File parentFile = newFile.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            file.transferTo(newFile);
            fileUploadResult.setFilePathName(filePath);
            fileUploadResult.setFileName(file.getOriginalFilename());

            result = success(fileUploadResult, "上传成功");
        } catch (Exception e) {
            super.errMsg("上传失败", json, e);
            result = failure(e.getMessage(), "上传失败");
        }

        return result;
    }

    @RequestMapping("/updateFile")
    public String updateFile(String json) {
        String result  = "";

        try {
            Param_Jzwj param = FastJsonUtils.toObject(Param_Jzwj.class, json);
            pcService.updJzwj(param);

            result = success(true, "成功");
        } catch (Exception e) {
            super.errMsg("编辑文件失败", json, e);
            result = failure(e.getMessage(), "编辑文件失败");
        }
        return result;
    }

    @RequestMapping("/deleteFile")
    public String deleteFile(String json) {
        String result  = "";

        try {
            Param_Jzwj param = FastJsonUtils.toObject(Param_Jzwj.class, json);
            pcService.delJzwj(param);

            String path = "";
            // 附件存放路径
            switch (param.getPczylx())
            {
                case "0": // 评查活动存放路径：评查发起单位编码/年份/评查指标编码/GUID
                    path = SystemConfiguration.wzbsPath + SystemConfiguration.pcfaPath + param.getWscflj();
                    break;
                case "1": // 评查案件：评查发起单位编码/年份/评查指标编码/评查受理编码/GUID
                    path = SystemConfiguration.wzbsPath + SystemConfiguration.pcjzPath + param.getWscflj();
                default:
                    break;
            }
            File file = new File(path);
            if (file.exists()) {
                try{
                    file.delete();
                }catch(Exception ex){

                }
            }
            result = success(true, "成功");
        } catch (Exception e) {
            super.errMsg("删除文件失败", json, e);
            result = failure(e.getMessage(), "删除文件失败");
        }

        return result;
    }

    @RequestMapping("/getDocFile")
    public String getDocFile(String json) {
        String result = "";

        try {
            Param_Jzwj param = FastJsonUtils.toObject(Param_Jzwj.class, json);
            String path = "";
            // 附件存放路径
            switch (param.getPczylx()) {
                case "0": // 评查活动存放路径：评查发起单位编码/年份/评查指标编码/GUID
                    path = SystemConfiguration.pcfaPath + param.getWscflj();
                    break;
                case "1": // 评查案件：评查发起单位编码/年份/评查指标编码/评查受理编码/GUID
                    path = SystemConfiguration.pcjzPath + param.getWscflj();
                default:
                    break;
            }
            result = success(path, "成功");
        } catch (Exception e) {
            super.errMsg("获取文书存放路径失败", json, e);
            result = failure(e.getMessage(), "获取文书存放路径失败");
        }

        return result;
    }

    @RequestMapping("/getApproveDocFile")
    public String getApproveDocFile(String json) {
        String result = "";

        try {
            Param_Jzwj param = FastJsonUtils.toObject(Param_Jzwj.class, json);
            String path = SystemConfiguration.pcjzPath + param.getWscflj();

            // 调用WebService/AppendTableRow生成评查流转单
           Map params = new HashMap();
            params.put("docPath", SystemConfiguration.wzbsPath + path);
            params.put("firstCellValue", param.getPcyjmc());
            params.put("markName", param.getPcyjsq());
            params.put("luoquan", param.getPcyjlk());
            params.put("context", "");
            Object data = webServiceUtils.post(SystemConfiguration.webservice + "/AppendTableRow", params);
            if (data == null) {
                throw new Exception("调用WebService生成评查流转单失败，返回为空。");
            }
            // 解析XML文档
            List<String> list = webServiceUtils.parseXml(data.toString());
            if (org.apache.commons.lang3.StringUtils.isNoneEmpty(list.get(0))) {
                throw new Exception("调用WebService生成评查流转单失败，" + list.get(0));
            }

            result = success(path, "成功");
        } catch (Exception e) {
            super.errMsg("获取流转单路径失败", json, e);
            result = failure(e.getMessage(), "获取流转单路径失败");
        }

        return result;
    }

    /**
     * 保存文件（评查报告及流转单）
     *
     * @param request
     * @return
     */
    @RequestMapping("/savePCBG")
    public String savePCBG(HttpServletRequest request) {
        String result = "";
        String path = SystemConfiguration.wzbsPath + SystemConfiguration.pcjzPath + getParameter("wjlj");

        try {
            CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            if (commonsMultipartResolver.isMultipart(request)) {
                MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
                Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
                Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();
                for (Map.Entry<String, MultipartFile> entry : entries) {
                    MultipartFile file = entry.getValue();
                    File newFile = new File(path);
                    File parentFile = newFile.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    file.transferTo(newFile);
                }
            }

        } catch (Exception e){
            result = e.getMessage();
            super.errMsg("保存评查报告失败。", path, e);
        }

        return result;
    }

    // 获取评查案件
    @RequestMapping("/getPcaj")
    public String getPcaj(String json) {
        String result = "";

        try {

            Param_Pcaj pcajParam = FastJsonUtils.toObject(Param_Pcaj.class, json);
//            pcajParam.setDwbm(getCurrentDwbm());
            pcajParam.setPage(parsePage(getParameter("page")));
            pcajParam.setRows(parseRows(getParameter("rows")));

            Param_Pager data = pcAjService.getPcaj(pcajParam);

            result = EasyUIHelper.buildDataGridDataSource(data.getList(), data.getCount());
        } catch (Exception e) {
            super.errMsg("评查案件获取失败", json, e);
        }

        return result;
    }

    // 删除评查案件
    @RequestMapping("/delPcaj")
    public String delPcaj(String pcslbm,String pcflbm) {
        String result = "";

        try {
            boolean isSuccess = pcAjService.delPcaj(pcslbm,pcflbm);
            result = success(isSuccess, "删除评查案件成功");

        } catch (Exception e) {

            super.errMsg("删除评查案件失败", pcslbm, e);
            result = failure(e.getMessage(), "删除评查案件失败");
        }

        return result;
    }

    // 获取评查组
    @RequestMapping("/getPcz")
    public String getPcz(String pchdbm,String pcflbm){
        String result = "";

        try {
            List<Map> list = pcAjService.getPcz(pchdbm,pcflbm);
            result = EasyUIHelper.buildDataGridDataSource(list,list.size());
        } catch (Exception e) {
            super.errMsg("获取评查组失败", pcflbm, e);
            result = failure(e.getMessage(), "获取评查组失败");
        }

        return result;
    }

    // 获取评查组人员信息
    @RequestMapping("/getPczry")
    public String getPczry(String pczbm){
        String result = "";

        try {
            List<Map> list = pcAjService.getPczry(pczbm);
            result = EasyUIHelper.buildDataGridDataSource(list, list.size());
        } catch (Exception e) {
            super.errMsg("获取评查活动失败", pczbm, e);
            result = failure(e.getMessage(), "获取评查活动失败");
        }

        return result;
    }

    // 变更评查人
    @RequestMapping("/updPcr")
    public String updPcr(String json) {
        String result = "";

        try {
            YX_PC_JBXX pcjbxxParam = FastJsonUtils.toObject(YX_PC_JBXX.class, json);

            boolean isSuccess = pcAjService.updPcr(pcjbxxParam);
            result = success(isSuccess, "变更评查人成功");
        } catch (Exception e) {

            super.errMsg("变更评查人失败", json, e);
            result = failure(e.getMessage(), "变更评查人失败");
        }

        return result;
    }

    // 获取活动审批、送审接收人员（案管负责人）
    @RequestMapping("/getHdsp")
    public String getHdsp(){

        String result = "";

        try {
            List<Map> list = pcService.getHdsp(getCurrentDwbm(), getCurrentGh());
            result = EasyUIHelper.buildDataGridDataSource(list, list.size());
        } catch (Exception e) {
            super.errMsg("获取评查分类列表失败", null, e);
        }

        return result;
    }

    // 评查方案送审
    @RequestMapping("/addPcfaps")
    public String addPcfaps(String json) {
        String result = "";

        try {
            Param_Pcsp pcspParam = FastJsonUtils.toObject(Param_Pcsp.class, json);
            pcspParam.setSsrdwbm(getCurrentDwbm());
            pcspParam.setSsrdwmc(getCurrentDwmc());
            pcspParam.setSsrgh(getCurrentGh());
            pcspParam.setSsrxm(getCurrentMC());
            boolean isSuccess = pcService.addPcfaps(pcspParam);
            result = success(isSuccess, "评查方案送审成功");
        } catch (Exception e) {

            super.errMsg("评查方案送审失败", json, e);
            result = failure(e.getMessage(), "评查方案送审失败");
        }

        return result;
    }

    // 评查方案审批
    @RequestMapping("/uptDealFasp")
    public String uptDealFasp(String json) {
        String result = "";

        try {
            Param_Pcsp pcspParam = FastJsonUtils.toObject(Param_Pcsp.class, json);

            boolean isSuccess = pcService.uptDealFasp(pcspParam);
            result = success(isSuccess, "评查方案审批成功");
        } catch (Exception e) {

            super.errMsg("评查方案审批失败", json, e);
            result = failure(e.getMessage(), "评查方案审批失败");
        }

        return result;
    }

    /**
     * 启动评查活动
     *
     * @param json
     * @return
     */
    @RequestMapping("/startPchd")
    public String startPchd(String json) {
        String result = "";

        try {
            Param_Hd param = FastJsonUtils.toObject(Param_Hd.class, json);
            param.setQdr_dwbm(getCurrentDwbm());
            param.setQdr_gh(getCurrentGh());
            param.setQdr_mc(getCurrentMC());
            boolean isSuccess = pcService.startPchd(param);
            result = success(isSuccess, "启动评查活动成功");
        } catch (Exception e) {
            super.errMsg("启动评查活动失败", json, e);
            result = failure(e.getMessage(), "启动评查活动失败");
        }

        return result;
    }

    /**
     * 结束评查活动
     *
     * @param json
     * @return
     */
    @RequestMapping("/finishPchd")
    public String finishPchd(String json) {
        String result = "";

        try {
            Param_Hd param = FastJsonUtils.toObject(Param_Hd.class, json);
            param.setJsr_dwbm(getCurrentDwbm());
            param.setJsr_gh(getCurrentGh());
            param.setJsr_mc(getCurrentMC());
            boolean isSuccess = pcService.finishPchd(param);
            result = success(isSuccess, "结束评查活动成功");
        } catch (Exception e) {
            super.errMsg("结束评查活动失败", json, e);
            result = failure(e.getMessage(), "结束评查活动失败");
        }

        return result;
    }

    // 获取评查活动状态
    @RequestMapping("/getHdztBM")
    public String getHdztBM(String pchdbm) {
        String result = "";

        try {
            Param_Pchdzt data = pcService.getHdztBM(pchdbm);
            if(data == null)
                throw new Exception("获取到评查活动状态为空");

            result = success(data, "获取评查活动状态成功");
        } catch (Exception e) {
            super.errMsg("获取评查活动状态失败", pchdbm, e);
            result = failure(e.getMessage(), "获取评查活动状态失败");
        }

        return result;
    }

    // 获取评查审批送审接收人员
    @RequestMapping("/getPcsp")
    public String getPcsp(String spjsbm, String pchdbm, String pcflbm) {
        String result = "";

        try {
            // 封装查询数据：
            Map map = new HashMap();
            map.put("p_dwbm",getCurrentDwbm());
            map.put("p_gh",getCurrentGh());
            map.put("p_spjsbm",spjsbm);
            map.put("p_pchdbm",pchdbm);
            map.put("p_pcflbm",pcflbm);

            List<Map> pcsp = pcService.getPcsp(map);
            result = FastJsonUtils.toString(pcsp);
        } catch (Exception e) {
            super.errMsg("获取评查审批送审接收人员失败", spjsbm, e);
            result = failure(e.getMessage(), "获取评查审批送审接收人员失败");
        }

        return result;
    }

    @ApiOperation("获取评查人员列表")
    @GetMapping("/getPcyInfoList")
    public String getPcyInfoList(String pchdbm){
        List<Map> pcyInfoList = pcmbService.getPcyInfoList(pchdbm);
        String treeGridDataSource = EasyUIHelper.buildTreeGridDataSource(pcyInfoList, pcyInfoList.size(), "获取数据成功");
        return treeGridDataSource;
    }



    @RequestMapping("/getPchdry")
    public String getPchdry(String pchdbm){
        String result = "";

        return result;

    }
    @ApiOperation("获取算法列表")
    @ResponseBody
    @RequestMapping("/getsfList")
    public MessageResult getsfList() throws Exception {
        Query query = new Query();
        query.setClazz(Fpsf.class);
        query.setTableName("XT_PC_FPSF");
        query.setOrderByClause("SFPX ASC");
        List<Fpsf> javaBean = queryUtils.getJavaBean(query);
        return new MessageResult("获取成功",200,javaBean);
    }
    @ApiOperation("执行算法程序")
    @RequestMapping("/invokeSf")
    public String invokeSf(String pchdbm,String type) throws Exception {
        String result  = "";

        try {
            SfDto sfDto = new SfDto();
            sfDto.setCurrentDwbm(getCurrentDwbm());
            sfDto.setCurrentDwmc(getCurrentDwmc());
            sfDto.setCurrentGh(getCurrentGh());
            sfDto.setCurrentMc(getCurrentMC());
            sfDto.setPchdbm(pchdbm);

            Method method = sfInvoke.getClass().getDeclaredMethod(type,SfDto.class);
            method.invoke(sfInvoke, sfDto);

            List<YX_PC_JBXX> list = sfInvoke.getCaseList(pchdbm);

            result = success(list, "自动分配成功");
        } catch (Exception e) {
            super.errMsg("自动分配失败", pchdbm, e);
            result = failure(e.getMessage(), "自动分配失败");
        }

        return result;
    }

    /**
     * 获取当前单位组织机构角色名称
     *
     * @return
     */
    @RequestMapping("/getJsmc")
    public String getJsmc() {
        String result = "";

        try {
            Map<String, Object> map = new HashMap<String, Object>();
            List<Map> list = pcService.getJsmc(map);
            result = EasyUIHelper.buildComboBoxDataSource(list, "JSBM","JSMC");

        } catch (Exception e) {
            super.errMsg("获取当前单位组织机构角色名称失败","", e);
            result = failure(e.getMessage(), "获取当前单位组织机构角色名称失败");
        }

        return result;
    }

    // 湖北支持节点回退
    @RequestMapping("/backspace")
    public String backspace(String pcslbm,String bmsah) {
        String result = "";
        Map map = new HashMap();
        map.put("pcslbm",pcslbm);
        map.put("bmsah",bmsah);
        map.put("dwbm",getCurrentDwbm());
        map.put("dwmc",getCurrentDwmc());
        map.put("gh",getCurrentGh());
        map.put("mc",getCurrentMC());
        map.put("czsm","案件【"+bmsah+"】被回退到办理阶段");

        try {
            pcService.backspace(map);
            result = success(true, "评查节点回退成功");

        } catch (Exception e) {

            super.errMsg("评查节点回退失败", pcslbm, e);
            result = failure(e.getMessage(), "评查节点回退失败");
        }

        return result;
    }
}
