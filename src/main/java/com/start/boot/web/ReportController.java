package com.start.boot.web;


import com.start.boot.common.BaseController;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.constant.SessionNames;
import com.start.boot.domain.FileUploadResult;
import com.start.boot.domain.Param_Jzwj;
import com.start.boot.domain.User;
import com.start.boot.service.PcService;
import com.start.boot.service.ReportService;
import com.start.boot.support.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 文书
 * Created by caomin on 2017/11/7.
 */
@RestController
@RequestMapping("/report")
public class ReportController extends BaseController {
    @Autowired
    ReportService reportService;
    @Autowired
    PcService pcService;
    @ResponseBody
    @RequestMapping("/getPath")
    public String getFilePath(){
        //调用 webservice获取文件路径，并返回
        String path="./plugin/officecontrol/TZS.doc";
        return success(path,"保存成功");
    }

    @RequestMapping("/uploadFile")
    public String saveFile(HttpServletRequest request){
        String path= getParameter("wjlj");
        CommonsMultipartResolver commonsMultipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)){
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
            Set<Map.Entry<String, MultipartFile>> entries = fileMap.entrySet();
            for (Map.Entry<String, MultipartFile> entry : entries) {
                MultipartFile file = entry.getValue();
                File file1 = new File(path);
                try {
                    file.transferTo(file1);
                } catch (IOException e) {
                    e.printStackTrace();
                    return failure(e.getMessage(),"保存失败。");
                }
            }
        }
        return success(path,"保存成功。");
    }
    //上传评查报告
    @RequestMapping("/fileUploadRept")
    public String uploadFileRept(HttpServletRequest request, MultipartFile file) {

        String result = "";
        String json = request.getParameter("json");
        try {
            if (file == null || file.getOriginalFilename() == null || file.getOriginalFilename() == "") {
                throw new Exception("获取到上传文件名为空。");
            }
            // 评查信息
            //Param_Jzwj param = FastJsonUtils.toObject(Param_Jzwj.class, json);
            String wjkzm = FileUtils.getFileExtension(file.getOriginalFilename());
            String filePath = "";
            String fileSavePath = "";

            User user= (User)HttpContextUtils.getSession(SessionNames.SESSION_KEY_USER);

            String pcdwbm = user.getDWBM();
            String year = DateUtils.getCurrentYear();
            String nzrgh = user.getGH();

            // 附件存放路径
            filePath = String.format("/%s/%s/%s/%s%s", pcdwbm, year,nzrgh, IDGenerateUtils.getCleanlyId(), wjkzm);
            fileSavePath = SystemConfiguration.wzbsPath + SystemConfiguration.pcbgPath + filePath;

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
            result = failure(e.getMessage(), "上传失败");
        }
        return result;
    }
    //获取评查报告列表
    @RequestMapping("getPcbg")
    public String getPcgb(String dwbm, String pcflbm) {
        String result = "";
        Map map = new HashMap();
        map.put("dwbm", dwbm);
        map.put("pcflbm", pcflbm);
        map.put("jzmllx", "3");
        try {
            List<Map> list = pcService.getPcbg(map);
            result = FastJsonUtils.toString(list);
        } catch (Exception e) {
            result = failure(e.getMessage(), "获取评查报告列表失败");
        }
        return result;
    }
}
