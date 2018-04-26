package com.start.boot.support.structure;

import java.io.Serializable;

/**
 * 请求结果封装
 *
 * @author 符黄辰君
 * @since 2017年6月23日
 */
public class RequestResult implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -2774968019246940325L;
    /**
     * 状态
     *
     */
    private int status;
    /**
     * 说明
     */
    private String note;
    /**
     * 业务值
     */
    private Object value;

    public RequestResult() {
    }

    public RequestResult(int status, String note, Object value) {
        this.status = status;
        this.note = note;
        this.value = value;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }


}
