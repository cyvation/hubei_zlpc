package com.start.boot.pojo.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author caomin
 * @date 2018/3/27
 * @说明 评查概览
 */
public class PcglDto {
    private String dwbm;
    private String dwmc;

    private    List<GzbmDto>gzbmDtoList=new ArrayList<>();

    public String getDwbm() {
        return dwbm;
    }

    public PcglDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PcglDto pcglDto = (PcglDto) o;
        return Objects.equals(dwbm, pcglDto.dwbm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dwbm);
    }

    public void setDwbm(String dwbm) {
        this.dwbm = dwbm;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public List<GzbmDto> getGzbmDtoList() {
        return gzbmDtoList;
    }

    public void setGzbmDtoList(List<GzbmDto> gzbmDtoList) {
        this.gzbmDtoList = gzbmDtoList;

    }
}
