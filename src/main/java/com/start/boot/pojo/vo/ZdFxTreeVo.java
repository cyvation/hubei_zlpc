package com.start.boot.pojo.vo;

import com.start.boot.pojo.dto.ZdFxDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lei on 2018/12/20.
 */

public class ZdFxTreeVo extends ZdFxDto {

    private Integer cbrcount; // 办案人数
    private Integer pcrcount; // 评查人数

    private String state;  //状态

    private List<ZdFxTreeVo> children = new ArrayList<>() ;

    private String pcl = "0.00"; // 评查比例
    private String cbrbpcl = "0.00"; // 承办人平均被评查件数
    private String pcrpcl = "0.00"; // 评查人平均评查数

    private Integer yzcount; // 优质案件数量
    private Integer hgcount; //  合格案件数量
    private Integer xccount; //  瑕疵案件数量
    private Integer bhgcount; // 不合格案件数量

    private String yzpcl = "0.00"; // 优质评查率
    private String hgpcl = "0.00"; // 合格评查率
    private String xcpcl = "0.00"; // 瑕疵评查率
    private String bhgpcl = "0.00"; // 不合格评查率


    public Integer getCbrcount() {
        return cbrcount;
    }

    public void setCbrcount(Integer cbrcount) {
        this.cbrcount = cbrcount;
    }

    public Integer getPcrcount() {
        return pcrcount;
    }

    public void setPcrcount(Integer pcrcount) {
        this.pcrcount = pcrcount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ZdFxTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<ZdFxTreeVo> children) {
        this.children = children;
    }

    public String getPcl() {
        return pcl;
    }

    public void setPcl(String pcl) {
        this.pcl = pcl;
    }

    public String getCbrbpcl() {
        return cbrbpcl;
    }

    public void setCbrbpcl(String cbrbpcl) {
        this.cbrbpcl = cbrbpcl;
    }

    public String getPcrpcl() {
        return pcrpcl;
    }

    public void setPcrpcl(String pcrpcl) {
        this.pcrpcl = pcrpcl;
    }

    public Integer getYzcount() {
        return yzcount;
    }

    public void setYzcount(Integer yzcount) {
        this.yzcount = yzcount;
    }

    public Integer getHgcount() {
        return hgcount;
    }

    public void setHgcount(Integer hgcount) {
        this.hgcount = hgcount;
    }

    public Integer getXccount() {
        return xccount;
    }

    public void setXccount(Integer xccount) {
        this.xccount = xccount;
    }

    public Integer getBhgcount() {
        return bhgcount;
    }

    public void setBhgcount(Integer bhgcount) {
        this.bhgcount = bhgcount;
    }

    public String getYzpcl() {
        return yzpcl;
    }

    public void setYzpcl(String yzpcl) {
        this.yzpcl = yzpcl;
    }

    public String getHgpcl() {
        return hgpcl;
    }

    public void setHgpcl(String hgpcl) {
        this.hgpcl = hgpcl;
    }

    public String getXcpcl() {
        return xcpcl;
    }

    public void setXcpcl(String xcpcl) {
        this.xcpcl = xcpcl;
    }

    public String getBhgpcl() {
        return bhgpcl;
    }

    public void setBhgpcl(String bhgpcl) {
        this.bhgpcl = bhgpcl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ZdFxTreeVo)) return false;
        if (!super.equals(o)) return false;

        ZdFxTreeVo that = (ZdFxTreeVo) o;

        if (StringUtils.isNotEmpty(id) ? !id.equals(that.id) : that.id != null) return false;
        return StringUtils.isNotEmpty(pid) ? pid.equals(that.pid) : that.pid == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        return result;
    }


    public static List<ZdFxTreeVo> formatToTree(List<ZdFxTreeVo> list) {
        ZdFxTreeVo root = new ZdFxTreeVo();
        List<ZdFxTreeVo> treelist = new ArrayList<ZdFxTreeVo>();//拼凑好的json格式的数据
        if (list != null && list.size() > 0) {
            for(int i= 0; i < list.size(); i++){
                //如果该节点没有父节点那么它就是根（root）节点
                if(list.get(i).getPid().equals("-1")){
                    root = list.get(i) ;
                    //获取该根节点的子节点
                    if(list!=null)
                        getChildrenNodes(list,root);
                    treelist.add(root) ;
                }
            }
        }
        return treelist ;
    }

    private static void getChildrenNodes(List<ZdFxTreeVo> nodes, ZdFxTreeVo root) {
        for (ZdFxTreeVo treeJson : nodes) {
            //在根节点中下寻找它的子节点
            if(treeJson.getPid().equals(root.getId())){//如果找到root的子结点
                //在父节点下添加子节点
                root.getChildren().add(treeJson);
                //寻找子节点的子节点
                getChildrenNodes(nodes,treeJson);
            }
        }
    }


    // 构建树形结构
    public static List<ZdFxTreeVo> buildTree(List<ZdFxTreeVo> nodes){
        if (CollectionUtils.isEmpty(nodes)) return nodes;

        ZdFxTreeVo root = nodes.get(0);
        nodes.remove(0);

        List<ZdFxTreeVo> treeNodes = new ArrayList<>();
        List<ZdFxTreeVo> rootNodes = getRootNodes(nodes);
        for(ZdFxTreeVo rootNode : rootNodes){
            buildChildNodes(nodes, rootNode);
            treeNodes.add(rootNode);
        }

        treeNodes.add(0,root);
        return treeNodes;
    }

    // 递归子节点
    private static void buildChildNodes(List<ZdFxTreeVo> nodes, ZdFxTreeVo node) {
        List<ZdFxTreeVo> children = getChildNodes(nodes, node);
        if (!children.isEmpty()) {
            for (ZdFxTreeVo child : children) {
                buildChildNodes(nodes, child);
            }
            node.setChildren(children);
        }
    }

    // 获取父节点下所有的子节点
    private static List<ZdFxTreeVo> getChildNodes(List<ZdFxTreeVo> nodes, ZdFxTreeVo pnode) {
        List<ZdFxTreeVo> childNodes = new ArrayList<>();
        for (ZdFxTreeVo n : nodes) {
            if (pnode.getId().equals(n.getPid())) {
                childNodes.add(n);
            }
        }
        return childNodes;
    }

    // 判断是否为根节点
    public static boolean  rootNode(List<ZdFxTreeVo> nodes, ZdFxTreeVo node) {
        boolean isRootNode = true;
        for (ZdFxTreeVo n : nodes) {
            if (node.getPid().equals(n.getId())) {
                isRootNode = false;
                break;
            }
        }
        return isRootNode;
    }

    // 获取集合中所有的根节点
    private static List<ZdFxTreeVo> getRootNodes(List<ZdFxTreeVo> nodes) {
        List<ZdFxTreeVo> rootNodes = new ArrayList<>();
        for (ZdFxTreeVo n : nodes) {
            if (rootNode(nodes, n)) {
                rootNodes.add(n);
            }
        }
        return rootNodes;
    }


}
