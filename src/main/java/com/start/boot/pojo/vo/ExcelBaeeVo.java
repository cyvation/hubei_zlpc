package com.start.boot.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("excle导出实体对象")
public class ExcelBaeeVo {
    @ApiModelProperty("文件路径")
    private String filePath;
    @ApiModelProperty("文件名")
    private String fileName;
    @ApiModelProperty("表格头")
    private List<String>header;


    @ApiModelProperty("第一列标题")
    private List<String>colHeader;

    public List<String> getColHeader() {
        return colHeader;
    }

    public void setColHeader(List<String> colHeader) {
        this.colHeader = colHeader;
    }

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

    public List<String> getHeader() {
        return header;
    }


    public void setHeader(List<String> header) {
        this.header = header;
    }

}
