package com.start.boot.pojo.vo;

import com.start.boot.pojo.dto.ShPcjbqkerDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caomin
 * @date 2018/1/25
 * @说明
 */
public class ShPcjbqkerVo {

    private Integer month;
    private Integer count;
    private List<ShPcjbqkerDto> pcslbmList=new ArrayList<>();

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
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
