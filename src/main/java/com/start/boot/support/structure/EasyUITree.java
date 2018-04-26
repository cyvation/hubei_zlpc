package com.start.boot.support.structure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <h3>EasyUI  tree 数据结构</h3>
 *
 * @author 符黄辰君
 * @since 2017/6/29.
 */
public class EasyUITree {

    private Object id;

    private String text;

    private String state = "closed";

    private Boolean checked;

    private String iconCls;

    private Object attributes;

    private List<EasyUITree> children;
    public EasyUITree(){}

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Object getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }


    public List<EasyUITree> getChildren() {
        return children;
    }

    public void setChildren(List<EasyUITree> children) {
        this.children = children;
    }

    public void setAttributes(Object attributes) {
        this.attributes = attributes;
    }

    /**
     * 添加子节点
     *
     * @param child
     */
    public void addChild(EasyUITree child) {
        if (children == null) {
            children = new ArrayList<EasyUITree>();
        }
        children.add(child);
    }

    /**
     * 添加子节点
     *
     * @param index 子节点索引
     * @param child
     */
    public void addChild(int index, EasyUITree child) {
        if (children == null) {
            children = new ArrayList<EasyUITree>();
        }
        children.add(index, child);
    }



}
