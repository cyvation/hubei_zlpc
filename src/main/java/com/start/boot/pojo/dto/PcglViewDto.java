package com.start.boot.pojo.dto;

import com.start.boot.constant.PatternRules;
import com.start.boot.pojo.vo.SxgzBmVo;

import java.util.List;
import java.util.Map;

/**
 * @author caomin
 * @date 2018/3/29
 * @说明 评查概览返回表格对象
 */
public class PcglViewDto {
    /**
     * 表格头
     */
    private List<SxgzBmVo> header  ;
    /**
     * 数据
     */
    private  List<PcglDto> data ;

    private List<Map> rowList;

    public List<SxgzBmVo> getHeader() {
        return header;
    }

    public void setHeader(List<SxgzBmVo> header) {
        this.header = header;
    }

    public List<PcglDto> getData() {
        return data;
    }

    public void setData(List<PcglDto> data) {
        this.data = data;
    }

    public List<Map> getRowList() {
        return rowList;
    }

    public void setRowList(List<Map> rowList) {
        this.rowList = rowList;
    }
}
