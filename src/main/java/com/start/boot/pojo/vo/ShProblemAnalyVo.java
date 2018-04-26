package com.start.boot.pojo.vo;

import com.start.boot.pojo.dto.ShPcjbqkerDto;

import java.util.ArrayList;
import java.util.List;

/**
 * 年度性质问题分析图
 * @author caomin
 * @date 2018/1/25
 * @说明
 */
public class ShProblemAnalyVo {

    private String pcxmc;
    private Integer count;
    private List<ShPcjbqkerDto> pcslbmList=new ArrayList<>();

    public String getPcxmc() {
        return pcxmc;
    }

    public void setPcxmc(String pcxmc) {
        this.pcxmc = pcxmc;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ShPcjbqkerDto> getPcslbmList() {
        return pcslbmList;
    }

    public void setPcslbmList(List<ShPcjbqkerDto> pcslbmList) {
        this.pcslbmList = pcslbmList;
    }
}
