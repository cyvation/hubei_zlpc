package com.start.boot.pojo.dto;

import java.util.ArrayList;
import java.util.List;

public class CountDo {

    private Integer year;
    private Integer month;
    private String pcjg;
    private String pcdwbm;
    private String pcflbm;
    private Integer count;
    private Double percent;
    public CountDo(){}

    public List<CountDo>init(List<String>pcjs){
        ArrayList<CountDo> countDoArrayList = new ArrayList<>();
        pcjs.stream().forEach(pcjg->{
            if (pcjg!=null){
                countDoArrayList.add(new CountDo(pcjg,0.0));
            }
        });
        return countDoArrayList;
    }
    public CountDo(String s, Double i) {
        this.pcjg=s;
        this.percent=i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CountDo countDo = (CountDo) o;
        if (pcjg==null){
            return false;
        }
        return pcjg.equals(countDo.pcjg);
    }

    @Override
    public int hashCode() {
        if (pcjg==null){
            pcjg="优秀";
        }
        return pcjg.hashCode();
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getPcjg() {
        return pcjg;
    }

    public void setPcjg(String pcjg) {
        this.pcjg = pcjg;
    }

    public String getPcdwbm() {
        return pcdwbm;
    }

    public void setPcdwbm(String pcdwbm) {
        this.pcdwbm = pcdwbm;
    }

    public String getPcflbm() {
        return pcflbm;
    }

    public void setPcflbm(String pcflbm) {
        this.pcflbm = pcflbm;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
