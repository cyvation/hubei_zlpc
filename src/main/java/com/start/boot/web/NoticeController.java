package com.start.boot.web;

import com.start.boot.common.MessageResult;
import com.start.boot.common.SystemConfiguration;
import com.start.boot.domain.Notice;
import com.start.boot.service.NoticeService;
import com.start.boot.service.SystemCoreConfigService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

/**
 * 通知公告
 *
 * @caomin
 * @create 2017-12-21 10:44
 **/
@RequestMapping("/notice")
@RestController
public class NoticeController extends ArchivesSystemBaseController {


    @Autowired
    SystemCoreConfigService systemCoreConfigService;

    @Autowired
    NoticeService noticeService;


    @ResponseBody
    @PostMapping("/saveNotice")
    @ApiOperation("添加消息")
    public MessageResult saveFile(@RequestBody Notice notice) throws IOException {
        notice.setFbrdwbm(getCurrentDwbm());
        notice.setFbrdwmc(getCurrentDwmc());
        notice.setFbrgh(getCurrentGh());
        notice.setFbrxm(getCurrentMC());
        Notice save = noticeService.save(notice);
        return  new MessageResult("保存成功",200,save);
    }


    @ApiOperation("上传文件")
    @PostMapping("/uploadFile")
    public MessageResult uploadFile(HttpServletRequest request, MultipartFile file) {

        MessageResult messageResult;

        try {

            String wzbsPath = SystemConfiguration.wzbsPath;
            String notice_file_path = systemCoreConfigService.getSystemConfigValue("notice_file_path");
            String originalFilename = file.getOriginalFilename();
            String realSavePath = notice_file_path;
            String dwbm = getCurrentDwbm();
            String thisYear = String.valueOf(LocalDate.now().getYear());
            realSavePath += dwbm + "/" + thisYear + "/" + UUID.randomUUID();
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

    /**
     * @Description
     * @param saveFilename
     * @param savePath
     * @return
     */
    private String makePath(String saveFilename, String savePath) {

        String dir = savePath + UUID.randomUUID(); // savePath/dir1/dir2
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        return dir;
    }


    @ResponseBody
    @GetMapping("/getNotice")
    @ApiOperation("获取单个通知")
    public MessageResult getSingeNotice(String bh){
        Notice noticeByBh = noticeService.getNoticeByBh(bh);
        return  new MessageResult("查询成功",200,noticeByBh);
    }

}
