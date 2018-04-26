package com.start.boot.pojo.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author caomin
 * @date 2018/1/24
 * @说明 返回给前端的对象
 */
public class YearProblemAnalyzeResultVo {
    private String pcjl;
    private List<Integer>data=new ArrayList<>();

    public String getPcjl() {
        return pcjl;
    }

    public void setPcjl(String pcjl) {
        this.pcjl = pcjl;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
