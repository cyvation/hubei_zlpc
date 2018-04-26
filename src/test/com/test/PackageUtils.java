package com.test;

import org.junit.Test;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;

import java.io.*;

/**
 * @author caomin
 * @date 2018/2/28
 * @说明 版本差异，打包工具
 */
public class PackageUtils {

    String diffAdress="d:/diff.txt";
    String startVersion="d3f8a7e ";
    String endVersion="b862869 ";
    //CMD 执行命令
    String cmd = "cmd /c git diff "+startVersion+endVersion +" --name-only >"+diffAdress;

    @Test
    public void test()  {
        try {
            //运行git命令，获取输出。输出有可能要人工排除不需要打包的东西
            //Process process = Runtime.getRuntime().exec(cmd);

            //打包后存放目录
            String targetPath="d:/cqe/";
            //获取文件内容源路径
            String sourcePath="K:/sh_zlpc/target/cqe/";
            //获取差异文件
            File file = new File(diffAdress);


            InputStreamReader rdCto = new InputStreamReader(new FileInputStream(file));

            BufferedReader bfReader = new BufferedReader(rdCto);

            String textline = null;

            while ((textline=bfReader.readLine())!=null){
                if (textline.startsWith("src/test")||textline.endsWith(".png"))continue;
                if (textline.startsWith(".")||textline.startsWith("\"")||textline.endsWith("pom.xml"))continue;

                String replace = textline
                        .replace("src/main/resources/mapper/", "/WEB-INF/classes/mapper/")
                        .replace("src/main/resources/static/", "/WEB-INF/classes/static/")
                        .replace("src/main/java", "/WEB-INF/classes/")
                        .replace(".java",".class");

                if (StringUtils.isEmpty(textline))continue;
                String pathname = targetPath+ replace;
                File file1=new File(pathname);
                File parentFile = file1.getParentFile();
                if(!parentFile.exists()){
                   parentFile.mkdirs();
                }
                if (!pathname.trim().equalsIgnoreCase(targetPath)){
                    String pathname1 = sourcePath + replace;
                    File fileSource = new File(pathname1);
                    File fileTarget = new File(pathname);
                    if (!fileTarget.exists()){
                        fileTarget.createNewFile();
                    }
                    FileCopyUtils.copy(fileSource,fileTarget);
                }
            }
        }catch (Exception e){
                    System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

}
