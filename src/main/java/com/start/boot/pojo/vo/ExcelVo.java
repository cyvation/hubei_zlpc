package com.start.boot.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel("excle导出实体对象")
public class ExcelVo extends ExcelBaeeVo{

    @ApiModelProperty("数据")
    private List<List<String>>data;

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }
}
