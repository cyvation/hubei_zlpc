package com.start.boot.support.structure;

import java.util.List;

/**
 * <h3>EasyUI  datagrid 数据结构</h3>
 *
 * @author 符黄辰君
 * @since 2017/6/29.
 */
public class EasyUIDatagrid<T> {
    /**
     * 总数
     */
    private Long total;
    /**
     * 所有行
     */
    private List<T> rows;

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotal() {
        return total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * 创建一个datagrid对象
     * @param rows
     * @param total
     * @param <T>
     * @return
     */
    public static <T> EasyUIDatagrid<T> create(List<T> rows, Long total) {
        EasyUIDatagrid<T> datagrid = new EasyUIDatagrid<T>();
        datagrid.setRows(rows);
        datagrid.setTotal(total);
        return datagrid;
    }
}
