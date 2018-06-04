package com.start.boot.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Map;

@ApiModel("excle导出实体对象")
public class WordVo extends WordBaseVo{
    @ApiModelProperty("数据")
    private Map<Object,Object> data;

    public Map<Object, Object> getData() {
        return data;
    }

    public void setData(Map<Object, Object> data) {
        this.data = data;
    }
}
