package com.start.boot.web;


import com.start.boot.common.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * 文书
 * Created by caomin on 2017/11/7.
 */
@RestController
@RequestMapping("/report")
public class ReportController extends BaseController {


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
}
