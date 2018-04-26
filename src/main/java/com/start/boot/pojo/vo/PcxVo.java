package com.start.boot.pojo.vo;


import com.start.boot.pojo.dto.PcxDto;

import java.util.ArrayList;
import java.util.List;

public class PcxVo {
    String name;

    private Integer month;

    List<PcxDto>result=new ArrayList();
    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PcxDto> getResult() {
        return result;
    }

    public void setResult(List<PcxDto> result) {
        this.result = result;
    }

}
