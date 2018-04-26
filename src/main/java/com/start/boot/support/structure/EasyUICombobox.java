package com.start.boot.support.structure;


/**
 * <h3>EasyUI Combobox 数据结构</h3>
 *
 * @author 符黄辰君
 * @since 2017/7/5.
 */
public class EasyUICombobox {

    private String text;

    private String value;

    private Boolean selected;

    private Object attributes;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }
}
