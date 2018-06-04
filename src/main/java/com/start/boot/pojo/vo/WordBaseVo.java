package com.start.boot.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

@ApiModel("excle导出实体对象")
public class WordBaseVo {
    @ApiModelProperty("文件路径")
    private String filePath;
    @ApiModelProperty("文件名")
    private String fileName;
    @ApiModelProperty("模板")
    private String ftl;
    @ApiModelProperty("模板路径")
    private String ftlPath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFtl() {
        return ftl;
    }

    public void setFtl(String ftl) {
        this.ftl = ftl;
    }


    public String getFtlPath() {
        return ftlPath;
    }

    public void setFtlPath(String ftlPath) {
        this.ftlPath = ftlPath;
    }
}
