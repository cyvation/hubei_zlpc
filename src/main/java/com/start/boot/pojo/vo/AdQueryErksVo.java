package com.start.boot.pojo.vo;

/**
 * 高级查询二审抗诉Vo
 *
 * @caomin
 * @create 2017-12-15 14:09
 **/
public class AdQueryErksVo {
    private String key;
    private String value;
    private String description;

    public AdQueryErksVo(String key, String value, String description) {
        this.key=key;
        this.value=value;
        this.description=description;

    }

    public AdQueryErksVo() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
