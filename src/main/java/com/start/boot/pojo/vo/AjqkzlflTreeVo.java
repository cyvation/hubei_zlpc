package com.start.boot.pojo.vo;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

/**
 * 案件情况质量分析树形实体类
 * 李志恒 2018年5月15日
 */
public class AjqkzlflTreeVo  implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id ; //单位编码

    private String pid; //父单位编码

    private String state;  //状态

    private List<AjqkzlflTreeVo> children = new ArrayList<AjqkzlflTreeVo>() ;

    private String name; //名称

    private int bjs; //办结数

    private int pcajs; //评查案件数

    private String pcajZb; //评查案件占比

    private int yzajs; //优质案件数

    private String yzajZb; //优质案件占比

    private int hgajs; //合格案件数

    private String hgajZb;//合格案件占比

    private int xcajs;//瑕疵案件数

    private String xcajZb;//瑕疵案件占比

    private int bhgajs;//不合格案件数

    private String bhgajZb;//不合格案件占比

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBjs() {
        return bjs;
    }

    public void setBjs(int bjs) {
        this.bjs = bjs;
    }

    public int getPcajs() {
        return pcajs;
    }

    public void setPcajs(int pcajs) {
        this.pcajs = pcajs;
    }

    public String getPcajZb() {
        return pcajZb;
    }

    public void setPcajZb(String pcajZb) {
        this.pcajZb = pcajZb;
    }

    public int getYzajs() {
        return yzajs;
    }

    public void setYzajs(int yzajs) {
        this.yzajs = yzajs;
    }

    public String getYzajZb() {
        return yzajZb;
    }

    public void setYzajZb(String yzajZb) {
        this.yzajZb = yzajZb;
    }

    public int getHgajs() {
        return hgajs;
    }

    public void setHgajs(int hgajs) {
        this.hgajs = hgajs;
    }

    public String getHgajZb() {
        return hgajZb;
    }

    public void setHgajZb(String hgajZb) {
        this.hgajZb = hgajZb;
    }

    public int getXcajs() {
        return xcajs;
    }

    public void setXcajs(int xcajs) {
        this.xcajs = xcajs;
    }

    public String getXcajZb() {
        return xcajZb;
    }

    public void setXcajZb(String xcajZb) {
        this.xcajZb = xcajZb;
    }

    public int getBhgajs() {
        return bhgajs;
    }

    public void setBhgajs(int bhgajs) {
        this.bhgajs = bhgajs;
    }

    public String getBhgajZb() {
        return bhgajZb;
    }

    public void setBhgajZb(String bhgajZb) {
        this.bhgajZb = bhgajZb;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<AjqkzlflTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<AjqkzlflTreeVo> children) {
        this.children = children;
    }

    public static List<AjqkzlflTreeVo> formatTree(List<AjqkzlflTreeVo> list) {
        AjqkzlflTreeVo root = new AjqkzlflTreeVo();
        List<AjqkzlflTreeVo> treelist = new ArrayList<AjqkzlflTreeVo>();//拼凑好的json格式的数据
        if (list != null && list.size() > 0) {
            for(int i= 0; i < list.size(); i++){
                //如果该节点没有父节点那么它就是根（root）节点
                if(!isExistsParentNode(list, list.get(i).getPid())){
                    root = list.get(i) ;
                    //获取该根节点的子节点
                    getChildrenNodes(list,root);
                    treelist.add(root) ;
                }
            }
        }
        return treelist ;
    }

    private static boolean isExistsParentNode(List<AjqkzlflTreeVo> list, String dwbm){
        boolean isExists = false;
        for (int i = 0; i < list.size(); i++) {
            if(dwbm.equals(list.get(i).getId())){
                isExists = true;
                break;
            }
        }
        return isExists;
    }

    private static void getChildrenNodes(List<AjqkzlflTreeVo> nodes, AjqkzlflTreeVo root) {
        for (AjqkzlflTreeVo treeJson : nodes) {
            //在根节点中下寻找它的子节点
            if(treeJson.getPid().equals(root.getId())){//如果找到root的子结点
                //在父节点下添加子节点
                root.getChildren().add(treeJson);
                //寻找子节点的子节点
                getChildrenNodes(nodes,treeJson);
            }
        }
    }
}


