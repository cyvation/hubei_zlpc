package com.start.boot.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author caomin
 * @date 2018/1/28
 * @说明 分页对象
 */
public class PageInfo implements Serializable{
    private Integer page=1;
    private Integer rows=10;
    private Integer count;
    private List<Map> list;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<Map> getList() {
        return list;
    }

    public void setList(List<Map> list) {
        this.list = list;
    }
}
