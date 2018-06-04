package com.start.boot.utils;


import com.start.boot.common.SystemConfiguration;
import com.start.boot.pojo.vo.WordVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Map;

/**
 * excel导出
 */
@Component
public class ExportWordUtils {

    public String exportMillCertificateWord(HttpServletRequest request, HttpServletResponse response, WordVo wordVo) throws IOException {
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("utf-8");
        String wzbsPath = SystemConfiguration.wzbsPath;
        String filePath = "/template/";
        String path = wzbsPath + filePath;
        configuration.setDirectoryForTemplateLoading(new File(wordVo.getFtlPath()));//模板位置
        Template freemarkerTemplate = configuration.getTemplate(wordVo.getFtl());//模板
        File file = null;
        InputStream fin = null;
        ServletOutputStream out = null;
        try {
                 // 调用工具类的createDoc方法生成Word文档
             file = createDoc(wordVo.getData(),freemarkerTemplate,path+wordVo.getFileName());
             fin = new FileInputStream(file);
             response.setCharacterEncoding("utf-8");
             response.setContentType("application/msword");
                 // 设置浏览器以下载的方式处理该文件名
             String fileName = wordVo.getFileName();
             response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(fileName, "UTF-8"))));
             out = response.getOutputStream();
             byte[] buffer = new byte[512];  // 缓冲区
             int bytesToRead = -1;
             // 通过循环将读入的Word文件的内容输出到浏览器中
             while((bytesToRead = fin.read(buffer)) != -1) {
                 out.write(buffer, 0, bytesToRead);
             }
         } finally {
             if(fin != null) fin.close();
             if(out != null) out.close();
            // if(file != null) file.delete(); // 删除临时文件
         }
        return filePath+wordVo.getFileName();
     }

     private static File createDoc(Map<?, ?> dataMap, Template template,String file) {
         File f = new File(file);
         if (!f.getParentFile().exists()) {
             f.getParentFile().mkdirs();
         }
         Template t = template;
         try {
                // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
                Writer w = new OutputStreamWriter(new FileOutputStream(f), "utf-8");
                t.process(dataMap, w);
                w.close();
            } catch (Exception ex) {
                ex.printStackTrace();
                 throw new RuntimeException(ex);
            }
         return f;
     }

   /* *//**
     * 图片处理
     * @param src
     * @return
     *//*
    public String getImageBase(String src) {
         if(src==null||src==""){
                 return "";
             }
         File file = new File(getRequest().getRealPath("/")+src.replace(getRequest().getContextPath(), ""));
         if(!file.exists()) {
            return "";
          }
         InputStream in = null;
         byte[] data = null;
         try {
                 in = new FileInputStream(file);
             } catch (FileNotFoundException e1) {
                 e1.printStackTrace();
             }
         try {
                 data = new byte[in.available()];
                 in.read(data);
                 in.close();
             } catch (IOException e) {
               e.printStackTrace();
             }
         BASE64Encoder encoder = new BASE64Encoder();
         return encoder.encode(data);
    }*/
}