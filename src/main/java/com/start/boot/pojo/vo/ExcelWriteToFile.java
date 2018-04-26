package com.start.boot.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("excle导出实体对象")
public class ExcelWriteToFile extends ExcelBaeeVo {

    private int startLine;

    private String sourcefile;
    /**
     * 写到指定文件中
     */
    private String targetFile;
    @ApiModelProperty("数据")
    private List<List<String>>data;

    public List<List<String>> getData() {
        return data;
    }

    public String getSourcefile() {
        return sourcefile;
    }

    public void setSourcefile(String sourcefile) {
        this.sourcefile = sourcefile;
    }

    public String getTargetFile() {
        return targetFile;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public void setTargetFile(String targetFile) {
        this.targetFile = targetFile;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }
}
