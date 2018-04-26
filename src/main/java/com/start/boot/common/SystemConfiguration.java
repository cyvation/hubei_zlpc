package com.start.boot.common;


import com.start.boot.service.SystemCoreConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * <h3>配置，系统的外部配置都初始化到这个类</h3>
 *
 * @author 符黄辰君
 * @since 2017年6月23日
 */
@Component
public class SystemConfiguration implements CommandLineRunner{

    @Autowired
    SystemCoreConfigService systemCoreConfigService;

    /********************系统配置,从数据库读取***************************/
    public static String djdwbm; // 单位
    public static String bmyybs; // 部门有异议标识
    public static String webservice; // WebService
    public static String wssfzpdf; // 文书是否转PDF
    public static String fwwsPath; // WebService文书文件输出路径
    public static String fwjzPath; // WebService电子卷宗文件输出路径
    public static String fwbgPath; // WebService报告文件输出路径
    public static String wzbsPath; // 网站部署位置
    public static String wsjzPath; // 文书卷宗保存位置
    public static String dzjzPath; // 电子卷宗保存位置
    public static String lsbgPath; // 临时报告文件保存位置
    public static String pcjzPath; // 评查卷宗保存位置
    public static String pcfaPath; // 评查方案保存位置
    public static String pcbgPath; // 评查报告保存位置
    public static String resourcefilepath;//我的收藏文件存放路径
    public static String mail_url;//发件人名称
    public static String mail_from;//邮件主题
    public static String mail_subject;//邮件中包含的链接
    public static String falvHtml;//法律法规地址
    public static String alkHtml;//案件库地址

    public void init() throws Exception {
        //从数据库获取最新的配置信息
        djdwbm=systemCoreConfigService.getSystemConfigValue("djdwbm");
        bmyybs=systemCoreConfigService.getSystemConfigValue("bmyybs");
        webservice=systemCoreConfigService.getSystemConfigValue("webservice");
        wssfzpdf=systemCoreConfigService.getSystemConfigValue("wssfzpdf");
        fwwsPath=systemCoreConfigService.getSystemConfigValue("fwwsPath");
        fwjzPath=systemCoreConfigService.getSystemConfigValue("fwjzPath");
        fwbgPath=systemCoreConfigService.getSystemConfigValue("fwbgPath");
        wzbsPath=systemCoreConfigService.getSystemConfigValue("wzbsPath");
        wsjzPath=systemCoreConfigService.getSystemConfigValue("wsjzPath");
        dzjzPath=systemCoreConfigService.getSystemConfigValue("dzjzPath");
        lsbgPath=systemCoreConfigService.getSystemConfigValue("lsbgPath");
        pcjzPath=systemCoreConfigService.getSystemConfigValue("pcjzPath");
        pcfaPath=systemCoreConfigService.getSystemConfigValue("pcfaPath");
        pcbgPath=systemCoreConfigService.getSystemConfigValue("pcbgPath");

        //邮件配置
        mail_from=systemCoreConfigService.getSystemConfigValue("mail_from");
        mail_subject=systemCoreConfigService.getSystemConfigValue("mail_subject");
        mail_url=systemCoreConfigService.getSystemConfigValue("mail_url");
        //法律法规、案件地址
        falvHtml=systemCoreConfigService.getSystemConfigValue("falvHtml");
        alkHtml=systemCoreConfigService.getSystemConfigValue("alkHtml");

        resourcefilepath=systemCoreConfigService.getSystemConfigValue("resource.filepath");

 /*       webservice = "http://localhost:6425//MainService.asmx";
        wssfzpdf = "true";
        fwwsPath = "X:/Document/";
        fwjzPath = "X:/Dossier/";
        fwbgPath = "X:/PCBG/";
        wzbsPath = "D:/Workspace/Java/sh_zlpc/target/classes/static/";
        wsjzPath = "/Temp/Doc/";
        dzjzPath = "/Temp/Dossier/";
        lsbgPath = "/Temp/PCBG/";
        pcjzPath = "/Files/PCJZ/";
        pcfaPath = "/Files/PCFA/";
        pcbgPath = "/Files/PCBG/";*/
    }

    @Override
    public void run(String... args) throws Exception {
        init();
    }
}
