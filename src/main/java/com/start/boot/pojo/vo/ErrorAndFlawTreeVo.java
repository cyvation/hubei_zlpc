package com.start.boot.pojo.vo;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorAndFlawTreeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id ; //单位编码

    private String pid; //父单位编码

    private String state;  //状态

    private List<ErrorAndFlawTreeVo> children = new ArrayList<ErrorAndFlawTreeVo>() ;

    private String name; //名称

    private Double pcajs=0.00;
    private Double cwajs=0.00;
    private Double cws=0.00;
    private String cwBl="0.00%";
    private String ajpjcws="0.00%";
    private String pjcws="0.00";
    private String zjcxBl="0.00%";
    private String ssrdBl="0.00%";
    private String flsyBl="0.00%";
    private String bacxBl= "0.00%";
    private String flwsBl="0.00%";
    private String sfBl= "0.00%";
    private String xtgfBl="0.00%";
    private String qtBl="0.00%";
    private String zcjdBl="0.00%";
    private String cxesgBl="0.00%";
    private String tbBl="0.00%";
    private String cxeswBl="0.00%";
    private String fljdBl="0.00%";
    private Integer zjcxHj=0;
    private Integer ssrdHj=0;
    private Integer flsyHj=0;
    private Integer bacxHj= 0;
    private Integer flwsHj=0;
    private Integer sfHj= 0;
    private Integer xtgfHj=0;
    private Integer qtHj=0;
    private Integer zcjdHj=0;
    private Integer cxesgHj=0;
    private Integer tbHj=0;
    private Integer cxeswHj=0;
    private Integer fljdHj=0;

    private Integer bjrNum = 0;
    private Integer bjajNum = 0;
    private Integer pcrNum = 0;
    private Integer pcajNum = 0;
    private String pcbl = "0.00%";
    private String bpcAvgNum = "0.00%";
    private String avgNum = "0.00%";
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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



    public List<ErrorAndFlawTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<ErrorAndFlawTreeVo> children) {
        this.children = children;
    }

    public List<ErrorAndFlawTreeVo> dataMapToBean(List<Map> list){
        List<ErrorAndFlawTreeVo> treeVoList = new ArrayList<ErrorAndFlawTreeVo>();
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        for (Map map : list) {
            ErrorAndFlawTreeVo treeVo = new ErrorAndFlawTreeVo();
            treeVo.setPcajs(Double.valueOf(map.get("PCALLCOUNT")+""));
            treeVo.setCwajs(Double.valueOf(map.get("errorAllCount")+""));
            treeVo.setCws(Double.valueOf(map.get("errorCount")+""));
            treeVo.setZjcxHj(Integer.valueOf(map.get("zjcxHj")+""));
            treeVo.setSsrdHj(Integer.valueOf(map.get("ssrdHj")+""));
            treeVo.setFlsyHj(Integer.valueOf(map.get("flsyHj")+""));
            treeVo.setBacxHj(Integer.valueOf(map.get("bacxHj")+""));
            treeVo.setFlwsHj(Integer.valueOf(map.get("flwsHj")+""));
            treeVo.setSfHj(Integer.valueOf(map.get("sfHj")+""));
            treeVo.setXtgfHj(Integer.valueOf(map.get("xtgfHj")+""));
            treeVo.setQtHj(Integer.valueOf(map.get("qtHj")+""));
            treeVo.setZcjdHj(Integer.valueOf(map.get("zcjdHj")+""));
            treeVo.setCxesgHj(Integer.valueOf(map.get("cxesgHj")+""));
            treeVo.setTbHj(Integer.valueOf(map.get("tbHj")+""));
            treeVo.setCxeswHj(Integer.valueOf(map.get("cxeswHj")+""));
            treeVo.setFljdHj(Integer.valueOf(map.get("fljdHj")+""));
            treeVo.setZjcxBl(map.get("zjcxBl")+"");
            treeVo.setSsrdBl(map.get("ssrdBl")+"");
            treeVo.setFlsyBl(map.get("flsyBl")+"");
            treeVo.setBacxBl(map.get("bacxBl")+"");
            treeVo.setFlwsBl(map.get("flwsBl")+"");
            treeVo.setSfBl(map.get("sfBl")+"");
            treeVo.setXtgfBl(map.get("xtgfBl")+"");
            treeVo.setQtBl(map.get("qtBl")+"");
            treeVo.setZcjdBl(map.get("zcjdBl")+"");
            treeVo.setCxesgBl(map.get("cxesgBl")+"");
            treeVo.setTbBl(map.get("tbBl")+"");
            treeVo.setCxeswBl(map.get("cxeswBl")+"");
            treeVo.setFljdBl(map.get("fljdBl")+"");
            Double errorBl=  treeVo.getPcajs()==0?0:(treeVo.getCwajs() / treeVo.getPcajs())*100;
            Double errorAvgCount=  treeVo.getPcajs()==0?0:(treeVo.getCws() / treeVo.getPcajs())*100;
            Double errorAvgNum=  treeVo.getCwajs()==0?0:(treeVo.getCws() / treeVo.getCwajs());
            treeVo.setCwBl(decimalFormat.format(errorBl) + "%");
            treeVo.setAjpjcws(decimalFormat.format(errorAvgCount) + "%");
            treeVo.setPjcws(decimalFormat.format(errorAvgNum) );
            treeVo.setId(map.get("id") + "");
            treeVo.setPid(map.get("pid") + "");
            treeVo.setName(map.get("name") + "");
            treeVoList.add(treeVo);
        }
        return treeVoList;
    }
    public List<ErrorAndFlawTreeVo> dataMapToBeanG(List<Map> list){
        List<ErrorAndFlawTreeVo> treeVoList = new ArrayList<ErrorAndFlawTreeVo>();
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        for (Map map : list) {
            ErrorAndFlawTreeVo treeVo = new ErrorAndFlawTreeVo();
            treeVo.setId(map.get("id") + "");
            treeVo.setPid(map.get("pid") + "");
            treeVo.setName(map.get("name") + "");
            treeVo.setBjrNum(Integer.valueOf(map.get("bjrNum")+""));
            treeVo.setBjajNum(Integer.valueOf(map.get("bjajNum")+""));
            treeVo.setPcrNum(Integer.valueOf(map.get("pcrNum")+""));
            treeVo.setPcajNum(Integer.valueOf(map.get("pcajNum")+""));
            treeVo.setPcbl(map.get("pcbl")+"");
            treeVo.setBpcAvgNum(map.get("bpcAvgNum")+"");
            treeVo.setAvgNum(map.get("avgNum")+"");
            treeVoList.add(treeVo);
        }
        return treeVoList;
    }
    public static List<ErrorAndFlawTreeVo> formatTree(List<ErrorAndFlawTreeVo> list,List<ErrorAndFlawTreeVo> lists) {
        ErrorAndFlawTreeVo root = new ErrorAndFlawTreeVo();
        List<ErrorAndFlawTreeVo> treelist = new ArrayList<ErrorAndFlawTreeVo>();//拼凑好的json格式的数据
        if (list != null && list.size() > 0) {
            for(int i= 0; i < list.size(); i++){
                //如果该节点没有父节点那么它就是根（root）节点
                if(list.get(i).getPid().equals("-1")){
                    root = list.get(i) ;
                    //获取该根节点的子节点
                    if(lists!=null)
                        getChildrenNodes(lists,root);
                    treelist.add(root) ;
                }
            }
        }
        return treelist ;
    }
    public static List<ErrorAndFlawTreeVo> formatTreeDq(List<ErrorAndFlawTreeVo> list) {
        ErrorAndFlawTreeVo root = new ErrorAndFlawTreeVo();
        List<ErrorAndFlawTreeVo> treelist = new ArrayList<ErrorAndFlawTreeVo>();//拼凑好的json格式的数据
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
   /* public List<ErrorAndFlawTreeVo> formatSonArea(List<ErrorAndFlawTreeVo> list,String areaId){
        List<ErrorAndFlawTreeVo> sonList = new ArrayList<ErrorAndFlawTreeVo>();
        for (ErrorAndFlawTreeVo map : list) {
            if(areaId.equals(map.getPid()){
                ErrorAndFlawTreeVo area = new ErrorAndFlawTreeVo();
                area.setChildren(formatSonArea(list,map.getId()));
                sonList.add(area);
            }
        }
        return sonList;
    }*/
    private static boolean isExistsParentNode(List<ErrorAndFlawTreeVo> list, String dwbm){
        boolean isExists = false;
        for (int i = 0; i < list.size(); i++) {
            if(dwbm.equals(list.get(i).getId())){
                isExists = true;
                break;
            }
        }
        return isExists;
    }

    private static void getChildrenNodes(List<ErrorAndFlawTreeVo> nodes, ErrorAndFlawTreeVo root) {
        for (ErrorAndFlawTreeVo treeJson : nodes) {
            //在根节点中下寻找它的子节点
            if(treeJson.getPid().equals(root.getId())){//如果找到root的子结点
                //在父节点下添加子节点
                root.getChildren().add(treeJson);
                //寻找子节点的子节点
                getChildrenNodes(nodes,treeJson);
            }
        }
    }

    public Double getPcajs() {
        return pcajs;
    }

    public void setPcajs(Double pcajs) {
        this.pcajs = pcajs;
    }

    public Double getCwajs() {
        return cwajs;
    }

    public void setCwajs(Double cwajs) {
        this.cwajs = cwajs;
    }

    public Double getCws() {
        return cws;
    }

    public void setCws(Double cws) {
        this.cws = cws;
    }

    public String getCwBl() {
        return cwBl;
    }

    public void setCwBl(String cwBl) {
        this.cwBl = cwBl;
    }

    public String getAjpjcws() {
        return ajpjcws;
    }

    public void setAjpjcws(String ajpjcws) {
        this.ajpjcws = ajpjcws;
    }

    public String getPjcws() {
        return pjcws;
    }

    public void setPjcws(String pjcws) {
        this.pjcws = pjcws;
    }


    public Integer getZjcxHj() {
        return zjcxHj;
    }

    public void setZjcxHj(Integer zjcxHj) {
        this.zjcxHj = zjcxHj;
    }

    public Integer getSsrdHj() {
        return ssrdHj;
    }

    public void setSsrdHj(Integer ssrdHj) {
        this.ssrdHj = ssrdHj;
    }

    public Integer getFlsyHj() {
        return flsyHj;
    }

    public void setFlsyHj(Integer flsyHj) {
        this.flsyHj = flsyHj;
    }

    public Integer getBacxHj() {
        return bacxHj;
    }

    public void setBacxHj(Integer bacxHj) {
        this.bacxHj = bacxHj;
    }

    public Integer getFlwsHj() {
        return flwsHj;
    }

    public void setFlwsHj(Integer flwsHj) {
        this.flwsHj = flwsHj;
    }

    public Integer getSfHj() {
        return sfHj;
    }

    public void setSfHj(Integer sfHj) {
        this.sfHj = sfHj;
    }

    public Integer getXtgfHj() {
        return xtgfHj;
    }

    public void setXtgfHj(Integer xtgfHj) {
        this.xtgfHj = xtgfHj;
    }

    public Integer getQtHj() {
        return qtHj;
    }

    public void setQtHj(Integer qtHj) {
        this.qtHj = qtHj;
    }

    public Integer getZcjdHj() {
        return zcjdHj;
    }

    public void setZcjdHj(Integer zcjdHj) {
        this.zcjdHj = zcjdHj;
    }

    public Integer getCxesgHj() {
        return cxesgHj;
    }

    public void setCxesgHj(Integer cxesgHj) {
        this.cxesgHj = cxesgHj;
    }

    public Integer getTbHj() {
        return tbHj;
    }

    public void setTbHj(Integer tbHj) {
        this.tbHj = tbHj;
    }

    public Integer getCxeswHj() {
        return cxeswHj;
    }

    public void setCxeswHj(Integer cxeswHj) {
        this.cxeswHj = cxeswHj;
    }

    public Integer getFljdHj() {
        return fljdHj;
    }

    public void setFljdHj(Integer fljdHj) {
        this.fljdHj = fljdHj;
    }

    public String getZjcxBl() {
        return zjcxBl;
    }

    public void setZjcxBl(String zjcxBl) {
        this.zjcxBl = zjcxBl;
    }

    public String getSsrdBl() {
        return ssrdBl;
    }

    public void setSsrdBl(String ssrdBl) {
        this.ssrdBl = ssrdBl;
    }

    public String getFlsyBl() {
        return flsyBl;
    }

    public void setFlsyBl(String flsyBl) {
        this.flsyBl = flsyBl;
    }

    public String getBacxBl() {
        return bacxBl;
    }

    public void setBacxBl(String bacxBl) {
        this.bacxBl = bacxBl;
    }

    public String getFlwsBl() {
        return flwsBl;
    }

    public void setFlwsBl(String flwsBl) {
        this.flwsBl = flwsBl;
    }

    public String getSfBl() {
        return sfBl;
    }

    public void setSfBl(String sfBl) {
        this.sfBl = sfBl;
    }

    public String getXtgfBl() {
        return xtgfBl;
    }

    public void setXtgfBl(String xtgfBl) {
        this.xtgfBl = xtgfBl;
    }

    public String getQtBl() {
        return qtBl;
    }

    public void setQtBl(String qtBl) {
        this.qtBl = qtBl;
    }

    public String getZcjdBl() {
        return zcjdBl;
    }

    public void setZcjdBl(String zcjdBl) {
        this.zcjdBl = zcjdBl;
    }

    public String getCxesgBl() {
        return cxesgBl;
    }

    public void setCxesgBl(String cxesgBl) {
        this.cxesgBl = cxesgBl;
    }

    public String getTbBl() {
        return tbBl;
    }

    public void setTbBl(String tbBl) {
        this.tbBl = tbBl;
    }

    public String getCxeswBl() {
        return cxeswBl;
    }

    public void setCxeswBl(String cxeswBl) {
        this.cxeswBl = cxeswBl;
    }

    public String getFljdBl() {
        return fljdBl;
    }

    public void setFljdBl(String fljdBl) {
        this.fljdBl = fljdBl;
    }

    public Integer getBjrNum() {
        return bjrNum;
    }

    public void setBjrNum(Integer bjrNum) {
        this.bjrNum = bjrNum;
    }

    public Integer getBjajNum() {
        return bjajNum;
    }

    public void setBjajNum(Integer bjajNum) {
        this.bjajNum = bjajNum;
    }

    public Integer getPcrNum() {
        return pcrNum;
    }

    public void setPcrNum(Integer pcrNum) {
        this.pcrNum = pcrNum;
    }

    public Integer getPcajNum() {
        return pcajNum;
    }

    public void setPcajNum(Integer pcajNum) {
        this.pcajNum = pcajNum;
    }

    public String getPcbl() {
        return pcbl;
    }

    public void setPcbl(String pcbl) {
        this.pcbl = pcbl;
    }

    public String getBpcAvgNum() {
        return bpcAvgNum;
    }

    public void setBpcAvgNum(String bpcAvgNum) {
        this.bpcAvgNum = bpcAvgNum;
    }

    public String getAvgNum() {
        return avgNum;
    }

    public void setAvgNum(String avgNum) {
        this.avgNum = avgNum;
    }

    private Double pcAllnum = 0.0;private Double errorAllNum = 0.0;private Double errorNum = 0.0;
    private Integer zjcx40000 = 0;private Integer zjcx40007 = 0;private Integer zjcx40010 = 0;
    private Integer zjcx40066 = 0;private Integer zjcx40067 = 0;private Integer zjcx40068 = 0;
    private Integer zjcx40069 = 0;private Integer zjcx40071 = 0;private Integer zjcx40074 = 0;
    private Integer zjcx40081 = 0;private Integer zjcx40136 = 0;private Integer zjcx40137 = 0;
    private Integer zjcx40138 = 0;private Integer zjcx40139 = 0;private Integer zjcx40140 = 0;
    private Integer zjcx40141 = 0;private Integer zjcx40214 = 0;private Integer zjcx40215 = 0;
    private Integer zjcx40216 = 0;private Integer zjcx40229 = 0;private Integer zjcx40289 = 0;
    private Integer zjcx40290 = 0;private Integer zjcx40291 = 0;private Integer ssrd40000 = 0;
    private Integer ssrd40005 = 0;private Integer ssrd40025 = 0;private Integer ssrd40026 = 0;
    private Integer ssrd40029 = 0;private Integer ssrd40030 = 0;private Integer ssrd40031 = 0;
    private Integer ssrd40032 = 0;private Integer ssrd40033 = 0;private Integer ssrd40075 = 0;
    private Integer ssrd40076 = 0;private Integer ssrd40088 = 0;private Integer ssrd40089 = 0;
    private Integer ssrd40090 = 0;private Integer ssrd40131 = 0;private Integer ssrd40170 = 0;
    private Integer ssrd40207 = 0;private Integer ssrd40210 = 0;private Integer ssrd40224 = 0;
    private Integer ssrd40227 = 0;private Integer ssrd40274 = 0;private Integer ssrd40276 = 0;
    private Integer ssrd40277 = 0;private Integer ssrd40278 = 0;private Integer ssrd40280 = 0;
    private Integer flsy40000 = 0;private Integer flsy40208 = 0;private Integer flsy40258 = 0;
    private Integer flsy40259 = 0;private Integer flsy40260 = 0;private Integer flsy40261 = 0;
    private Integer flsy40262 = 0;private Integer flsy40263 = 0;private Integer flsy40264 = 0;
    private Integer flsy40265 = 0;private Integer flsy40266 = 0;private Integer bacx40000 = 0;
    private Integer bacx40001 = 0;private Integer bacx40008 = 0;private Integer bacx40042 = 0;
    private Integer bacx40059 = 0;private Integer bacx40062 = 0;private Integer bacx40070 = 0;
    private Integer bacx40082 = 0;private Integer bacx40102 = 0;private Integer bacx40103 = 0;
    private Integer bacx40112 = 0;private Integer bacx40118 = 0;private Integer bacx40120 = 0;
    private Integer bacx40129 = 0;private Integer bacx40135 = 0;private Integer bacx40160 = 0;
    private Integer bacx40220 = 0;private Integer bacx40231 = 0;private Integer bacx40251 = 0;
    private Integer bacx40252 = 0;private Integer bacx40253 = 0;private Integer bacx40254 = 0;
    private Integer bacx40268 = 0;private Integer bacx40272 = 0;private Integer bacx40283 = 0;
    private Integer flws40037 = 0;private Integer flws40098 = 0;private Integer flws40155 = 0;
    private Integer sf40000 = 0;private Integer sf40163 = 0;private Integer zcjd40056 = 0;private Integer zcjd40099 = 0;
    private Integer zcjd40249 = 0;private Integer zcjd40255 = 0;private Integer tb40180 = 0;private Integer tb40212 = 0;private Integer tb40286 = 0;
    private Integer zjcx40072 = 0;private Integer zjcx40073 = 0;private Integer zjcx40116 = 0;private Integer zjcx40213 = 0;
    private Integer zjcx40235 = 0;private Integer zjcx40236 = 0;private Integer zjcx40237 = 0;private Integer zjcx40238 = 0;
    private Integer zjcx40239 = 0;private Integer zjcx40240 = 0;private Integer zjcx40241 = 0;private Integer zjcx40246 = 0;
    private Integer zjcx40288 = 0;private Integer zjcx40292 = 0;private Integer  ssrd40024 = 0;private Integer  ssrd40027 = 0;
    private Integer ssrd40028 = 0;private Integer ssrd40034 = 0;private Integer ssrd40035 = 0;private Integer ssrd40041 = 0;
    private Integer ssrd40091 = 0;private Integer ssrd40096 = 0;private Integer ssrd40134 = 0;private Integer ssrd40228 = 0;
    private Integer ssrd40275 = 0;private Integer ssrd40281 = 0;private Integer ssrd40282 = 0;private Integer flsy40125 = 0;
    private Integer flsy40126 = 0;private Integer flsy40127 = 0;private Integer flsy40128 = 0;private Integer flsy40209 = 0;
    private Integer flsy40267 = 0;private Integer flsy40270 = 0;private Integer flsy40271 = 0;private Integer flsy40284 = 0;
    private Integer bacx40003 = 0;private Integer bacx40004 = 0;private Integer bacx40006 = 0;private Integer bacx40009 = 0;
    private Integer bacx40011 = 0;private Integer bacx40012 = 0;private Integer bacx40013 = 0;private Integer bacx40014 = 0;
    private Integer bacx40015 = 0;private Integer bacx40016 = 0;private Integer bacx40017 = 0;private Integer bacx40019 = 0;
    private Integer bacx40020 = 0;private Integer bacx40021 = 0;private Integer bacx40022 = 0;private Integer bacx40023 = 0;
    private Integer bacx40036 = 0;private Integer bacx40045 = 0;private Integer bacx40046 = 0;private Integer bacx40047 = 0;
    private Integer bacx40051 = 0;private Integer bacx40052 = 0;private Integer bacx40055 = 0;private Integer bacx40058 = 0;
    private Integer bacx40060 = 0;private Integer bacx40061 = 0;private Integer bacx40064 = 0;private Integer bacx40065 = 0;
    private Integer bacx40077 = 0;private Integer bacx40079 = 0;private Integer bacx40080 = 0;private Integer bacx40087 = 0;
    private Integer bacx40093 = 0;private Integer bacx40094 = 0;private Integer bacx40101 = 0;private Integer bacx40104 = 0;
    private Integer bacx40107 = 0;private Integer bacx40111 = 0;private Integer bacx40114 = 0;private Integer bacx40119 = 0;
    private Integer bacx40121 = 0;private Integer bacx40122 = 0;private Integer bacx40123 = 0;private Integer bacx40124 = 0;
    private Integer bacx40130 = 0;private Integer bacx40142 = 0;private Integer bacx40143 = 0;private Integer bacx40144 = 0;
    private Integer bacx40145 = 0;private Integer bacx40146 = 0;private Integer bacx40147 = 0;private Integer bacx40148 = 0;
    private Integer bacx40149 = 0;private Integer bacx40150 = 0;private Integer bacx40151 = 0;private Integer bacx40152 = 0;
    private Integer bacx40153 = 0;private Integer bacx40159 = 0;private Integer bacx40161 = 0;private Integer bacx40164 = 0;
    private Integer bacx40165 = 0;private Integer bacx40166 = 0;private Integer bacx40167 = 0;private Integer bacx40168 = 0;
    private Integer bacx40169 = 0;private Integer bacx40171 = 0;private Integer bacx40174 = 0;private Integer bacx40175 = 0;
    private Integer bacx40176 = 0;private Integer bacx40179 = 0;private Integer bacx40181 = 0;private Integer bacx40182 = 0;
    private Integer bacx40183 = 0;private Integer bacx40185 = 0;private Integer bacx40188 = 0;private Integer bacx40190 = 0;
    private Integer bacx40191 = 0;private Integer bacx40192 = 0;private Integer bacx40193 = 0;private Integer bacx40194 = 0;
    private Integer bacx40195 = 0;private Integer bacx40197 = 0;private Integer bacx40199 = 0;private Integer bacx40204 = 0;
    private Integer bacx40205 = 0;private Integer bacx40217 = 0;private Integer bacx40218 = 0;private Integer bacx40219 = 0;
    private Integer bacx40221 = 0;private Integer bacx40222 = 0;private Integer bacx40223 = 0;private Integer bacx40225 = 0;
    private Integer bacx40226 = 0;private Integer bacx40230 = 0;private Integer bacx40233 = 0;private Integer bacx40234 = 0;
    private Integer bacx40244 = 0;private Integer bacx40245 = 0;private Integer bacx40247 = 0;private Integer bacx40248 = 0;
    private Integer bacx40250 = 0;private Integer bacx40256 = 0;private Integer bacx40257 = 0;private Integer bacx40269 = 0;
    private Integer bacx40273 = 0;private Integer bacx40279 = 0;private Integer bacx40287 = 0;private Integer flws40000 = 0;
    private Integer flws40043 = 0;private Integer flws40044 = 0;private Integer flws40156 = 0;private Integer flws40157 = 0;
    private Integer flws40186 = 0;private Integer flws40189 = 0;private Integer flws40242 = 0;private Integer sf40038 = 0;
    private Integer sf40184 = 0;private Integer sf40198 = 0;private Integer xtgf40000 = 0;private Integer xtgf40018 = 0;
    private Integer xtgf40097 = 0;private Integer xtgf40110 = 0;private Integer xtgf40162 = 0;private Integer xtgf40177 = 0;
    private Integer xtgf40178 = 0;private Integer xtgf40196 = 0;private Integer qt40000 = 0;private Integer qt40049 = 0;
    private Integer qt40050 = 0;private Integer qt40154 = 0;private Integer zcjd40002 = 0;private Integer zcjd40039 = 0;
    private Integer zcjd40040 = 0;private Integer zcjd40053 = 0;private Integer zcjd40054 = 0;private Integer zcjd40057 = 0;
    private Integer zcjd40063 = 0;private Integer zcjd40078 = 0;private Integer zcjd40084 = 0;private Integer zcjd40085 = 0;
    private Integer zcjd40086 = 0;private Integer zcjd40100 = 0;private Integer zcjd40132 = 0;private Integer zcjd40133 = 0;
    private Integer zcjd40158 = 0;private Integer zcjd40173 = 0;private Integer cxesg40000 = 0;private Integer cxesg40105 = 0;
    private Integer cxesg40115 = 0;private Integer cxesg40172 = 0;private Integer cxesg40204 = 0;private Integer tb40000 = 0;
    private Integer tb40048 = 0;private Integer tb40083 = 0;private Integer tb40092 = 0;private Integer tb40095 = 0;
    private Integer tb40106 = 0;private Integer tb40108 = 0;private Integer tb40109 = 0;private Integer tb40113 = 0;
    private Integer tb40117 = 0;private Integer tb40187 = 0;private Integer tb40206 = 0;private Integer tb40211 = 0;
    private Integer tb40232 = 0;private Integer tb40243 = 0;private Integer tb40285 = 0;private Integer cxesw40000 = 0;
    private Integer cxesw40105 = 0;private Integer cxesw40115 = 0;private Integer cxesw40172 = 0;private Integer cxesw40204 = 0;
    private Integer fljd40000 = 0;private Integer fljd40200 = 0;private Integer fljd40201 = 0;private Integer fljd40202 = 0;private Integer fljd40203 = 0;

    public Double getPcAllnum() {
        return pcAllnum;
    }

    public void setPcAllnum(Double pcAllnum) {
        this.pcAllnum = pcAllnum;
    }

    public Double getErrorAllNum() {
        return errorAllNum;
    }

    public void setErrorAllNum(Double errorAllNum) {
        this.errorAllNum = errorAllNum;
    }

    public Double getErrorNum() {
        return errorNum;
    }

    public void setErrorNum(Double errorNum) {
        this.errorNum = errorNum;
    }

    public Integer getZjcx40000() {
        return zjcx40000;
    }

    public void setZjcx40000(Integer zjcx40000) {
        this.zjcx40000 = zjcx40000;
    }

    public Integer getZjcx40007() {
        return zjcx40007;
    }

    public void setZjcx40007(Integer zjcx40007) {
        this.zjcx40007 = zjcx40007;
    }

    public Integer getZjcx40010() {
        return zjcx40010;
    }

    public void setZjcx40010(Integer zjcx40010) {
        this.zjcx40010 = zjcx40010;
    }

    public Integer getZjcx40066() {
        return zjcx40066;
    }

    public void setZjcx40066(Integer zjcx40066) {
        this.zjcx40066 = zjcx40066;
    }

    public Integer getZjcx40067() {
        return zjcx40067;
    }

    public void setZjcx40067(Integer zjcx40067) {
        this.zjcx40067 = zjcx40067;
    }

    public Integer getZjcx40068() {
        return zjcx40068;
    }

    public void setZjcx40068(Integer zjcx40068) {
        this.zjcx40068 = zjcx40068;
    }

    public Integer getZjcx40069() {
        return zjcx40069;
    }

    public void setZjcx40069(Integer zjcx40069) {
        this.zjcx40069 = zjcx40069;
    }

    public Integer getZjcx40071() {
        return zjcx40071;
    }

    public void setZjcx40071(Integer zjcx40071) {
        this.zjcx40071 = zjcx40071;
    }

    public Integer getZjcx40074() {
        return zjcx40074;
    }

    public void setZjcx40074(Integer zjcx40074) {
        this.zjcx40074 = zjcx40074;
    }

    public Integer getZjcx40081() {
        return zjcx40081;
    }

    public void setZjcx40081(Integer zjcx40081) {
        this.zjcx40081 = zjcx40081;
    }

    public Integer getZjcx40136() {
        return zjcx40136;
    }

    public void setZjcx40136(Integer zjcx40136) {
        this.zjcx40136 = zjcx40136;
    }

    public Integer getZjcx40137() {
        return zjcx40137;
    }

    public void setZjcx40137(Integer zjcx40137) {
        this.zjcx40137 = zjcx40137;
    }

    public Integer getZjcx40138() {
        return zjcx40138;
    }

    public void setZjcx40138(Integer zjcx40138) {
        this.zjcx40138 = zjcx40138;
    }

    public Integer getZjcx40139() {
        return zjcx40139;
    }

    public void setZjcx40139(Integer zjcx40139) {
        this.zjcx40139 = zjcx40139;
    }

    public Integer getZjcx40140() {
        return zjcx40140;
    }

    public void setZjcx40140(Integer zjcx40140) {
        this.zjcx40140 = zjcx40140;
    }

    public Integer getZjcx40141() {
        return zjcx40141;
    }

    public void setZjcx40141(Integer zjcx40141) {
        this.zjcx40141 = zjcx40141;
    }

    public Integer getZjcx40214() {
        return zjcx40214;
    }

    public void setZjcx40214(Integer zjcx40214) {
        this.zjcx40214 = zjcx40214;
    }

    public Integer getZjcx40215() {
        return zjcx40215;
    }

    public void setZjcx40215(Integer zjcx40215) {
        this.zjcx40215 = zjcx40215;
    }

    public Integer getZjcx40216() {
        return zjcx40216;
    }

    public void setZjcx40216(Integer zjcx40216) {
        this.zjcx40216 = zjcx40216;
    }

    public Integer getZjcx40229() {
        return zjcx40229;
    }

    public void setZjcx40229(Integer zjcx40229) {
        this.zjcx40229 = zjcx40229;
    }

    public Integer getZjcx40289() {
        return zjcx40289;
    }

    public void setZjcx40289(Integer zjcx40289) {
        this.zjcx40289 = zjcx40289;
    }

    public Integer getZjcx40290() {
        return zjcx40290;
    }

    public void setZjcx40290(Integer zjcx40290) {
        this.zjcx40290 = zjcx40290;
    }

    public Integer getZjcx40291() {
        return zjcx40291;
    }

    public void setZjcx40291(Integer zjcx40291) {
        this.zjcx40291 = zjcx40291;
    }

    public Integer getSsrd40000() {
        return ssrd40000;
    }

    public void setSsrd40000(Integer ssrd40000) {
        this.ssrd40000 = ssrd40000;
    }

    public Integer getSsrd40005() {
        return ssrd40005;
    }

    public void setSsrd40005(Integer ssrd40005) {
        this.ssrd40005 = ssrd40005;
    }

    public Integer getSsrd40025() {
        return ssrd40025;
    }

    public void setSsrd40025(Integer ssrd40025) {
        this.ssrd40025 = ssrd40025;
    }

    public Integer getSsrd40026() {
        return ssrd40026;
    }

    public void setSsrd40026(Integer ssrd40026) {
        this.ssrd40026 = ssrd40026;
    }

    public Integer getSsrd40029() {
        return ssrd40029;
    }

    public void setSsrd40029(Integer ssrd40029) {
        this.ssrd40029 = ssrd40029;
    }

    public Integer getSsrd40030() {
        return ssrd40030;
    }

    public void setSsrd40030(Integer ssrd40030) {
        this.ssrd40030 = ssrd40030;
    }

    public Integer getSsrd40031() {
        return ssrd40031;
    }

    public void setSsrd40031(Integer ssrd40031) {
        this.ssrd40031 = ssrd40031;
    }

    public Integer getSsrd40032() {
        return ssrd40032;
    }

    public void setSsrd40032(Integer ssrd40032) {
        this.ssrd40032 = ssrd40032;
    }

    public Integer getSsrd40033() {
        return ssrd40033;
    }

    public void setSsrd40033(Integer ssrd40033) {
        this.ssrd40033 = ssrd40033;
    }

    public Integer getSsrd40075() {
        return ssrd40075;
    }

    public void setSsrd40075(Integer ssrd40075) {
        this.ssrd40075 = ssrd40075;
    }

    public Integer getSsrd40076() {
        return ssrd40076;
    }

    public void setSsrd40076(Integer ssrd40076) {
        this.ssrd40076 = ssrd40076;
    }

    public Integer getSsrd40088() {
        return ssrd40088;
    }

    public void setSsrd40088(Integer ssrd40088) {
        this.ssrd40088 = ssrd40088;
    }

    public Integer getSsrd40089() {
        return ssrd40089;
    }

    public void setSsrd40089(Integer ssrd40089) {
        this.ssrd40089 = ssrd40089;
    }

    public Integer getSsrd40090() {
        return ssrd40090;
    }

    public void setSsrd40090(Integer ssrd40090) {
        this.ssrd40090 = ssrd40090;
    }

    public Integer getSsrd40131() {
        return ssrd40131;
    }

    public void setSsrd40131(Integer ssrd40131) {
        this.ssrd40131 = ssrd40131;
    }

    public Integer getSsrd40170() {
        return ssrd40170;
    }

    public void setSsrd40170(Integer ssrd40170) {
        this.ssrd40170 = ssrd40170;
    }

    public Integer getSsrd40207() {
        return ssrd40207;
    }

    public void setSsrd40207(Integer ssrd40207) {
        this.ssrd40207 = ssrd40207;
    }

    public Integer getSsrd40210() {
        return ssrd40210;
    }

    public void setSsrd40210(Integer ssrd40210) {
        this.ssrd40210 = ssrd40210;
    }

    public Integer getSsrd40224() {
        return ssrd40224;
    }

    public void setSsrd40224(Integer ssrd40224) {
        this.ssrd40224 = ssrd40224;
    }

    public Integer getSsrd40227() {
        return ssrd40227;
    }

    public void setSsrd40227(Integer ssrd40227) {
        this.ssrd40227 = ssrd40227;
    }

    public Integer getSsrd40274() {
        return ssrd40274;
    }

    public void setSsrd40274(Integer ssrd40274) {
        this.ssrd40274 = ssrd40274;
    }

    public Integer getSsrd40276() {
        return ssrd40276;
    }

    public void setSsrd40276(Integer ssrd40276) {
        this.ssrd40276 = ssrd40276;
    }

    public Integer getSsrd40277() {
        return ssrd40277;
    }

    public void setSsrd40277(Integer ssrd40277) {
        this.ssrd40277 = ssrd40277;
    }

    public Integer getSsrd40278() {
        return ssrd40278;
    }

    public void setSsrd40278(Integer ssrd40278) {
        this.ssrd40278 = ssrd40278;
    }

    public Integer getSsrd40280() {
        return ssrd40280;
    }

    public void setSsrd40280(Integer ssrd40280) {
        this.ssrd40280 = ssrd40280;
    }

    public Integer getFlsy40000() {
        return flsy40000;
    }

    public void setFlsy40000(Integer flsy40000) {
        this.flsy40000 = flsy40000;
    }

    public Integer getFlsy40208() {
        return flsy40208;
    }

    public void setFlsy40208(Integer flsy40208) {
        this.flsy40208 = flsy40208;
    }

    public Integer getFlsy40258() {
        return flsy40258;
    }

    public void setFlsy40258(Integer flsy40258) {
        this.flsy40258 = flsy40258;
    }

    public Integer getFlsy40259() {
        return flsy40259;
    }

    public void setFlsy40259(Integer flsy40259) {
        this.flsy40259 = flsy40259;
    }

    public Integer getFlsy40260() {
        return flsy40260;
    }

    public void setFlsy40260(Integer flsy40260) {
        this.flsy40260 = flsy40260;
    }

    public Integer getFlsy40261() {
        return flsy40261;
    }

    public void setFlsy40261(Integer flsy40261) {
        this.flsy40261 = flsy40261;
    }

    public Integer getFlsy40262() {
        return flsy40262;
    }

    public void setFlsy40262(Integer flsy40262) {
        this.flsy40262 = flsy40262;
    }

    public Integer getFlsy40263() {
        return flsy40263;
    }

    public void setFlsy40263(Integer flsy40263) {
        this.flsy40263 = flsy40263;
    }

    public Integer getFlsy40264() {
        return flsy40264;
    }

    public void setFlsy40264(Integer flsy40264) {
        this.flsy40264 = flsy40264;
    }

    public Integer getFlsy40265() {
        return flsy40265;
    }

    public void setFlsy40265(Integer flsy40265) {
        this.flsy40265 = flsy40265;
    }

    public Integer getFlsy40266() {
        return flsy40266;
    }

    public void setFlsy40266(Integer flsy40266) {
        this.flsy40266 = flsy40266;
    }

    public Integer getBacx40000() {
        return bacx40000;
    }

    public void setBacx40000(Integer bacx40000) {
        this.bacx40000 = bacx40000;
    }

    public Integer getBacx40001() {
        return bacx40001;
    }

    public void setBacx40001(Integer bacx40001) {
        this.bacx40001 = bacx40001;
    }

    public Integer getBacx40008() {
        return bacx40008;
    }

    public void setBacx40008(Integer bacx40008) {
        this.bacx40008 = bacx40008;
    }

    public Integer getBacx40042() {
        return bacx40042;
    }

    public void setBacx40042(Integer bacx40042) {
        this.bacx40042 = bacx40042;
    }

    public Integer getBacx40059() {
        return bacx40059;
    }

    public void setBacx40059(Integer bacx40059) {
        this.bacx40059 = bacx40059;
    }

    public Integer getBacx40062() {
        return bacx40062;
    }

    public void setBacx40062(Integer bacx40062) {
        this.bacx40062 = bacx40062;
    }

    public Integer getBacx40070() {
        return bacx40070;
    }

    public void setBacx40070(Integer bacx40070) {
        this.bacx40070 = bacx40070;
    }

    public Integer getBacx40082() {
        return bacx40082;
    }

    public void setBacx40082(Integer bacx40082) {
        this.bacx40082 = bacx40082;
    }

    public Integer getBacx40102() {
        return bacx40102;
    }

    public void setBacx40102(Integer bacx40102) {
        this.bacx40102 = bacx40102;
    }

    public Integer getBacx40103() {
        return bacx40103;
    }

    public void setBacx40103(Integer bacx40103) {
        this.bacx40103 = bacx40103;
    }

    public Integer getBacx40112() {
        return bacx40112;
    }

    public void setBacx40112(Integer bacx40112) {
        this.bacx40112 = bacx40112;
    }

    public Integer getBacx40118() {
        return bacx40118;
    }

    public void setBacx40118(Integer bacx40118) {
        this.bacx40118 = bacx40118;
    }

    public Integer getBacx40120() {
        return bacx40120;
    }

    public void setBacx40120(Integer bacx40120) {
        this.bacx40120 = bacx40120;
    }

    public Integer getBacx40129() {
        return bacx40129;
    }

    public void setBacx40129(Integer bacx40129) {
        this.bacx40129 = bacx40129;
    }

    public Integer getBacx40135() {
        return bacx40135;
    }

    public void setBacx40135(Integer bacx40135) {
        this.bacx40135 = bacx40135;
    }

    public Integer getBacx40160() {
        return bacx40160;
    }

    public void setBacx40160(Integer bacx40160) {
        this.bacx40160 = bacx40160;
    }

    public Integer getBacx40220() {
        return bacx40220;
    }

    public void setBacx40220(Integer bacx40220) {
        this.bacx40220 = bacx40220;
    }

    public Integer getBacx40231() {
        return bacx40231;
    }

    public void setBacx40231(Integer bacx40231) {
        this.bacx40231 = bacx40231;
    }

    public Integer getBacx40251() {
        return bacx40251;
    }

    public void setBacx40251(Integer bacx40251) {
        this.bacx40251 = bacx40251;
    }

    public Integer getBacx40252() {
        return bacx40252;
    }

    public void setBacx40252(Integer bacx40252) {
        this.bacx40252 = bacx40252;
    }

    public Integer getBacx40253() {
        return bacx40253;
    }

    public void setBacx40253(Integer bacx40253) {
        this.bacx40253 = bacx40253;
    }

    public Integer getBacx40254() {
        return bacx40254;
    }

    public void setBacx40254(Integer bacx40254) {
        this.bacx40254 = bacx40254;
    }

    public Integer getBacx40268() {
        return bacx40268;
    }

    public void setBacx40268(Integer bacx40268) {
        this.bacx40268 = bacx40268;
    }

    public Integer getBacx40272() {
        return bacx40272;
    }

    public void setBacx40272(Integer bacx40272) {
        this.bacx40272 = bacx40272;
    }

    public Integer getBacx40283() {
        return bacx40283;
    }

    public void setBacx40283(Integer bacx40283) {
        this.bacx40283 = bacx40283;
    }

    public Integer getFlws40037() {
        return flws40037;
    }

    public void setFlws40037(Integer flws40037) {
        this.flws40037 = flws40037;
    }

    public Integer getFlws40098() {
        return flws40098;
    }

    public void setFlws40098(Integer flws40098) {
        this.flws40098 = flws40098;
    }

    public Integer getFlws40155() {
        return flws40155;
    }

    public void setFlws40155(Integer flws40155) {
        this.flws40155 = flws40155;
    }

    public Integer getSf40000() {
        return sf40000;
    }

    public void setSf40000(Integer sf40000) {
        this.sf40000 = sf40000;
    }

    public Integer getSf40163() {
        return sf40163;
    }

    public void setSf40163(Integer sf40163) {
        this.sf40163 = sf40163;
    }

    public Integer getZcjd40056() {
        return zcjd40056;
    }

    public void setZcjd40056(Integer zcjd40056) {
        this.zcjd40056 = zcjd40056;
    }

    public Integer getZcjd40099() {
        return zcjd40099;
    }

    public void setZcjd40099(Integer zcjd40099) {
        this.zcjd40099 = zcjd40099;
    }

    public Integer getZcjd40249() {
        return zcjd40249;
    }

    public void setZcjd40249(Integer zcjd40249) {
        this.zcjd40249 = zcjd40249;
    }

    public Integer getZcjd40255() {
        return zcjd40255;
    }

    public void setZcjd40255(Integer zcjd40255) {
        this.zcjd40255 = zcjd40255;
    }

    public Integer getTb40180() {
        return tb40180;
    }

    public void setTb40180(Integer tb40180) {
        this.tb40180 = tb40180;
    }

    public Integer getTb40212() {
        return tb40212;
    }

    public void setTb40212(Integer tb40212) {
        this.tb40212 = tb40212;
    }

    public Integer getTb40286() {
        return tb40286;
    }

    public void setTb40286(Integer tb40286) {
        this.tb40286 = tb40286;
    }

    public Integer getZjcx40072() {
        return zjcx40072;
    }

    public void setZjcx40072(Integer zjcx40072) {
        this.zjcx40072 = zjcx40072;
    }

    public Integer getZjcx40073() {
        return zjcx40073;
    }

    public void setZjcx40073(Integer zjcx40073) {
        this.zjcx40073 = zjcx40073;
    }

    public Integer getZjcx40116() {
        return zjcx40116;
    }

    public void setZjcx40116(Integer zjcx40116) {
        this.zjcx40116 = zjcx40116;
    }

    public Integer getZjcx40213() {
        return zjcx40213;
    }

    public void setZjcx40213(Integer zjcx40213) {
        this.zjcx40213 = zjcx40213;
    }

    public Integer getZjcx40235() {
        return zjcx40235;
    }

    public void setZjcx40235(Integer zjcx40235) {
        this.zjcx40235 = zjcx40235;
    }

    public Integer getZjcx40236() {
        return zjcx40236;
    }

    public void setZjcx40236(Integer zjcx40236) {
        this.zjcx40236 = zjcx40236;
    }

    public Integer getZjcx40237() {
        return zjcx40237;
    }

    public void setZjcx40237(Integer zjcx40237) {
        this.zjcx40237 = zjcx40237;
    }

    public Integer getZjcx40238() {
        return zjcx40238;
    }

    public void setZjcx40238(Integer zjcx40238) {
        this.zjcx40238 = zjcx40238;
    }

    public Integer getZjcx40239() {
        return zjcx40239;
    }

    public void setZjcx40239(Integer zjcx40239) {
        this.zjcx40239 = zjcx40239;
    }

    public Integer getZjcx40240() {
        return zjcx40240;
    }

    public void setZjcx40240(Integer zjcx40240) {
        this.zjcx40240 = zjcx40240;
    }

    public Integer getZjcx40241() {
        return zjcx40241;
    }

    public void setZjcx40241(Integer zjcx40241) {
        this.zjcx40241 = zjcx40241;
    }

    public Integer getZjcx40246() {
        return zjcx40246;
    }

    public void setZjcx40246(Integer zjcx40246) {
        this.zjcx40246 = zjcx40246;
    }

    public Integer getZjcx40288() {
        return zjcx40288;
    }

    public void setZjcx40288(Integer zjcx40288) {
        this.zjcx40288 = zjcx40288;
    }

    public Integer getZjcx40292() {
        return zjcx40292;
    }

    public void setZjcx40292(Integer zjcx40292) {
        this.zjcx40292 = zjcx40292;
    }

    public Integer getSsrd40024() {
        return ssrd40024;
    }

    public void setSsrd40024(Integer ssrd40024) {
        this.ssrd40024 = ssrd40024;
    }

    public Integer getSsrd40027() {
        return ssrd40027;
    }

    public void setSsrd40027(Integer ssrd40027) {
        this.ssrd40027 = ssrd40027;
    }

    public Integer getSsrd40028() {
        return ssrd40028;
    }

    public void setSsrd40028(Integer ssrd40028) {
        this.ssrd40028 = ssrd40028;
    }

    public Integer getSsrd40034() {
        return ssrd40034;
    }

    public void setSsrd40034(Integer ssrd40034) {
        this.ssrd40034 = ssrd40034;
    }

    public Integer getSsrd40035() {
        return ssrd40035;
    }

    public void setSsrd40035(Integer ssrd40035) {
        this.ssrd40035 = ssrd40035;
    }

    public Integer getSsrd40041() {
        return ssrd40041;
    }

    public void setSsrd40041(Integer ssrd40041) {
        this.ssrd40041 = ssrd40041;
    }

    public Integer getSsrd40091() {
        return ssrd40091;
    }

    public void setSsrd40091(Integer ssrd40091) {
        this.ssrd40091 = ssrd40091;
    }

    public Integer getSsrd40096() {
        return ssrd40096;
    }

    public void setSsrd40096(Integer ssrd40096) {
        this.ssrd40096 = ssrd40096;
    }

    public Integer getSsrd40134() {
        return ssrd40134;
    }

    public void setSsrd40134(Integer ssrd40134) {
        this.ssrd40134 = ssrd40134;
    }

    public Integer getSsrd40228() {
        return ssrd40228;
    }

    public void setSsrd40228(Integer ssrd40228) {
        this.ssrd40228 = ssrd40228;
    }

    public Integer getSsrd40275() {
        return ssrd40275;
    }

    public void setSsrd40275(Integer ssrd40275) {
        this.ssrd40275 = ssrd40275;
    }

    public Integer getSsrd40281() {
        return ssrd40281;
    }

    public void setSsrd40281(Integer ssrd40281) {
        this.ssrd40281 = ssrd40281;
    }

    public Integer getSsrd40282() {
        return ssrd40282;
    }

    public void setSsrd40282(Integer ssrd40282) {
        this.ssrd40282 = ssrd40282;
    }

    public Integer getFlsy40125() {
        return flsy40125;
    }

    public void setFlsy40125(Integer flsy40125) {
        this.flsy40125 = flsy40125;
    }

    public Integer getFlsy40126() {
        return flsy40126;
    }

    public void setFlsy40126(Integer flsy40126) {
        this.flsy40126 = flsy40126;
    }

    public Integer getFlsy40127() {
        return flsy40127;
    }

    public void setFlsy40127(Integer flsy40127) {
        this.flsy40127 = flsy40127;
    }

    public Integer getFlsy40128() {
        return flsy40128;
    }

    public void setFlsy40128(Integer flsy40128) {
        this.flsy40128 = flsy40128;
    }

    public Integer getFlsy40209() {
        return flsy40209;
    }

    public void setFlsy40209(Integer flsy40209) {
        this.flsy40209 = flsy40209;
    }

    public Integer getFlsy40267() {
        return flsy40267;
    }

    public void setFlsy40267(Integer flsy40267) {
        this.flsy40267 = flsy40267;
    }

    public Integer getFlsy40270() {
        return flsy40270;
    }

    public void setFlsy40270(Integer flsy40270) {
        this.flsy40270 = flsy40270;
    }

    public Integer getFlsy40271() {
        return flsy40271;
    }

    public void setFlsy40271(Integer flsy40271) {
        this.flsy40271 = flsy40271;
    }

    public Integer getFlsy40284() {
        return flsy40284;
    }

    public void setFlsy40284(Integer flsy40284) {
        this.flsy40284 = flsy40284;
    }

    public Integer getBacx40003() {
        return bacx40003;
    }

    public void setBacx40003(Integer bacx40003) {
        this.bacx40003 = bacx40003;
    }

    public Integer getBacx40004() {
        return bacx40004;
    }

    public void setBacx40004(Integer bacx40004) {
        this.bacx40004 = bacx40004;
    }

    public Integer getBacx40006() {
        return bacx40006;
    }

    public void setBacx40006(Integer bacx40006) {
        this.bacx40006 = bacx40006;
    }

    public Integer getBacx40009() {
        return bacx40009;
    }

    public void setBacx40009(Integer bacx40009) {
        this.bacx40009 = bacx40009;
    }

    public Integer getBacx40011() {
        return bacx40011;
    }

    public void setBacx40011(Integer bacx40011) {
        this.bacx40011 = bacx40011;
    }

    public Integer getBacx40012() {
        return bacx40012;
    }

    public void setBacx40012(Integer bacx40012) {
        this.bacx40012 = bacx40012;
    }

    public Integer getBacx40013() {
        return bacx40013;
    }

    public void setBacx40013(Integer bacx40013) {
        this.bacx40013 = bacx40013;
    }

    public Integer getBacx40014() {
        return bacx40014;
    }

    public void setBacx40014(Integer bacx40014) {
        this.bacx40014 = bacx40014;
    }

    public Integer getBacx40015() {
        return bacx40015;
    }

    public void setBacx40015(Integer bacx40015) {
        this.bacx40015 = bacx40015;
    }

    public Integer getBacx40016() {
        return bacx40016;
    }

    public void setBacx40016(Integer bacx40016) {
        this.bacx40016 = bacx40016;
    }

    public Integer getBacx40017() {
        return bacx40017;
    }

    public void setBacx40017(Integer bacx40017) {
        this.bacx40017 = bacx40017;
    }

    public Integer getBacx40019() {
        return bacx40019;
    }

    public void setBacx40019(Integer bacx40019) {
        this.bacx40019 = bacx40019;
    }

    public Integer getBacx40020() {
        return bacx40020;
    }

    public void setBacx40020(Integer bacx40020) {
        this.bacx40020 = bacx40020;
    }

    public Integer getBacx40021() {
        return bacx40021;
    }

    public void setBacx40021(Integer bacx40021) {
        this.bacx40021 = bacx40021;
    }

    public Integer getBacx40022() {
        return bacx40022;
    }

    public void setBacx40022(Integer bacx40022) {
        this.bacx40022 = bacx40022;
    }

    public Integer getBacx40023() {
        return bacx40023;
    }

    public void setBacx40023(Integer bacx40023) {
        this.bacx40023 = bacx40023;
    }

    public Integer getBacx40036() {
        return bacx40036;
    }

    public void setBacx40036(Integer bacx40036) {
        this.bacx40036 = bacx40036;
    }

    public Integer getBacx40045() {
        return bacx40045;
    }

    public void setBacx40045(Integer bacx40045) {
        this.bacx40045 = bacx40045;
    }

    public Integer getBacx40046() {
        return bacx40046;
    }

    public void setBacx40046(Integer bacx40046) {
        this.bacx40046 = bacx40046;
    }

    public Integer getBacx40047() {
        return bacx40047;
    }

    public void setBacx40047(Integer bacx40047) {
        this.bacx40047 = bacx40047;
    }

    public Integer getBacx40051() {
        return bacx40051;
    }

    public void setBacx40051(Integer bacx40051) {
        this.bacx40051 = bacx40051;
    }

    public Integer getBacx40052() {
        return bacx40052;
    }

    public void setBacx40052(Integer bacx40052) {
        this.bacx40052 = bacx40052;
    }

    public Integer getBacx40055() {
        return bacx40055;
    }

    public void setBacx40055(Integer bacx40055) {
        this.bacx40055 = bacx40055;
    }

    public Integer getBacx40058() {
        return bacx40058;
    }

    public void setBacx40058(Integer bacx40058) {
        this.bacx40058 = bacx40058;
    }

    public Integer getBacx40060() {
        return bacx40060;
    }

    public void setBacx40060(Integer bacx40060) {
        this.bacx40060 = bacx40060;
    }

    public Integer getBacx40061() {
        return bacx40061;
    }

    public void setBacx40061(Integer bacx40061) {
        this.bacx40061 = bacx40061;
    }

    public Integer getBacx40064() {
        return bacx40064;
    }

    public void setBacx40064(Integer bacx40064) {
        this.bacx40064 = bacx40064;
    }

    public Integer getBacx40065() {
        return bacx40065;
    }

    public void setBacx40065(Integer bacx40065) {
        this.bacx40065 = bacx40065;
    }

    public Integer getBacx40077() {
        return bacx40077;
    }

    public void setBacx40077(Integer bacx40077) {
        this.bacx40077 = bacx40077;
    }

    public Integer getBacx40079() {
        return bacx40079;
    }

    public void setBacx40079(Integer bacx40079) {
        this.bacx40079 = bacx40079;
    }

    public Integer getBacx40080() {
        return bacx40080;
    }

    public void setBacx40080(Integer bacx40080) {
        this.bacx40080 = bacx40080;
    }

    public Integer getBacx40087() {
        return bacx40087;
    }

    public void setBacx40087(Integer bacx40087) {
        this.bacx40087 = bacx40087;
    }

    public Integer getBacx40093() {
        return bacx40093;
    }

    public void setBacx40093(Integer bacx40093) {
        this.bacx40093 = bacx40093;
    }

    public Integer getBacx40094() {
        return bacx40094;
    }

    public void setBacx40094(Integer bacx40094) {
        this.bacx40094 = bacx40094;
    }

    public Integer getBacx40101() {
        return bacx40101;
    }

    public void setBacx40101(Integer bacx40101) {
        this.bacx40101 = bacx40101;
    }

    public Integer getBacx40104() {
        return bacx40104;
    }

    public void setBacx40104(Integer bacx40104) {
        this.bacx40104 = bacx40104;
    }

    public Integer getBacx40107() {
        return bacx40107;
    }

    public void setBacx40107(Integer bacx40107) {
        this.bacx40107 = bacx40107;
    }

    public Integer getBacx40111() {
        return bacx40111;
    }

    public void setBacx40111(Integer bacx40111) {
        this.bacx40111 = bacx40111;
    }

    public Integer getBacx40114() {
        return bacx40114;
    }

    public void setBacx40114(Integer bacx40114) {
        this.bacx40114 = bacx40114;
    }

    public Integer getBacx40119() {
        return bacx40119;
    }

    public void setBacx40119(Integer bacx40119) {
        this.bacx40119 = bacx40119;
    }

    public Integer getBacx40121() {
        return bacx40121;
    }

    public void setBacx40121(Integer bacx40121) {
        this.bacx40121 = bacx40121;
    }

    public Integer getBacx40122() {
        return bacx40122;
    }

    public void setBacx40122(Integer bacx40122) {
        this.bacx40122 = bacx40122;
    }

    public Integer getBacx40123() {
        return bacx40123;
    }

    public void setBacx40123(Integer bacx40123) {
        this.bacx40123 = bacx40123;
    }

    public Integer getBacx40124() {
        return bacx40124;
    }

    public void setBacx40124(Integer bacx40124) {
        this.bacx40124 = bacx40124;
    }

    public Integer getBacx40130() {
        return bacx40130;
    }

    public void setBacx40130(Integer bacx40130) {
        this.bacx40130 = bacx40130;
    }

    public Integer getBacx40142() {
        return bacx40142;
    }

    public void setBacx40142(Integer bacx40142) {
        this.bacx40142 = bacx40142;
    }

    public Integer getBacx40143() {
        return bacx40143;
    }

    public void setBacx40143(Integer bacx40143) {
        this.bacx40143 = bacx40143;
    }

    public Integer getBacx40144() {
        return bacx40144;
    }

    public void setBacx40144(Integer bacx40144) {
        this.bacx40144 = bacx40144;
    }

    public Integer getBacx40145() {
        return bacx40145;
    }

    public void setBacx40145(Integer bacx40145) {
        this.bacx40145 = bacx40145;
    }

    public Integer getBacx40146() {
        return bacx40146;
    }

    public void setBacx40146(Integer bacx40146) {
        this.bacx40146 = bacx40146;
    }

    public Integer getBacx40147() {
        return bacx40147;
    }

    public void setBacx40147(Integer bacx40147) {
        this.bacx40147 = bacx40147;
    }

    public Integer getBacx40148() {
        return bacx40148;
    }

    public void setBacx40148(Integer bacx40148) {
        this.bacx40148 = bacx40148;
    }

    public Integer getBacx40149() {
        return bacx40149;
    }

    public void setBacx40149(Integer bacx40149) {
        this.bacx40149 = bacx40149;
    }

    public Integer getBacx40150() {
        return bacx40150;
    }

    public void setBacx40150(Integer bacx40150) {
        this.bacx40150 = bacx40150;
    }

    public Integer getBacx40151() {
        return bacx40151;
    }

    public void setBacx40151(Integer bacx40151) {
        this.bacx40151 = bacx40151;
    }

    public Integer getBacx40152() {
        return bacx40152;
    }

    public void setBacx40152(Integer bacx40152) {
        this.bacx40152 = bacx40152;
    }

    public Integer getBacx40153() {
        return bacx40153;
    }

    public void setBacx40153(Integer bacx40153) {
        this.bacx40153 = bacx40153;
    }

    public Integer getBacx40159() {
        return bacx40159;
    }

    public void setBacx40159(Integer bacx40159) {
        this.bacx40159 = bacx40159;
    }

    public Integer getBacx40161() {
        return bacx40161;
    }

    public void setBacx40161(Integer bacx40161) {
        this.bacx40161 = bacx40161;
    }

    public Integer getBacx40164() {
        return bacx40164;
    }

    public void setBacx40164(Integer bacx40164) {
        this.bacx40164 = bacx40164;
    }

    public Integer getBacx40165() {
        return bacx40165;
    }

    public void setBacx40165(Integer bacx40165) {
        this.bacx40165 = bacx40165;
    }

    public Integer getBacx40166() {
        return bacx40166;
    }

    public void setBacx40166(Integer bacx40166) {
        this.bacx40166 = bacx40166;
    }

    public Integer getBacx40167() {
        return bacx40167;
    }

    public void setBacx40167(Integer bacx40167) {
        this.bacx40167 = bacx40167;
    }

    public Integer getBacx40168() {
        return bacx40168;
    }

    public void setBacx40168(Integer bacx40168) {
        this.bacx40168 = bacx40168;
    }

    public Integer getBacx40169() {
        return bacx40169;
    }

    public void setBacx40169(Integer bacx40169) {
        this.bacx40169 = bacx40169;
    }

    public Integer getBacx40171() {
        return bacx40171;
    }

    public void setBacx40171(Integer bacx40171) {
        this.bacx40171 = bacx40171;
    }

    public Integer getBacx40174() {
        return bacx40174;
    }

    public void setBacx40174(Integer bacx40174) {
        this.bacx40174 = bacx40174;
    }

    public Integer getBacx40175() {
        return bacx40175;
    }

    public void setBacx40175(Integer bacx40175) {
        this.bacx40175 = bacx40175;
    }

    public Integer getBacx40176() {
        return bacx40176;
    }

    public void setBacx40176(Integer bacx40176) {
        this.bacx40176 = bacx40176;
    }

    public Integer getBacx40179() {
        return bacx40179;
    }

    public void setBacx40179(Integer bacx40179) {
        this.bacx40179 = bacx40179;
    }

    public Integer getBacx40181() {
        return bacx40181;
    }

    public void setBacx40181(Integer bacx40181) {
        this.bacx40181 = bacx40181;
    }

    public Integer getBacx40182() {
        return bacx40182;
    }

    public void setBacx40182(Integer bacx40182) {
        this.bacx40182 = bacx40182;
    }

    public Integer getBacx40183() {
        return bacx40183;
    }

    public void setBacx40183(Integer bacx40183) {
        this.bacx40183 = bacx40183;
    }

    public Integer getBacx40185() {
        return bacx40185;
    }

    public void setBacx40185(Integer bacx40185) {
        this.bacx40185 = bacx40185;
    }

    public Integer getBacx40188() {
        return bacx40188;
    }

    public void setBacx40188(Integer bacx40188) {
        this.bacx40188 = bacx40188;
    }

    public Integer getBacx40190() {
        return bacx40190;
    }

    public void setBacx40190(Integer bacx40190) {
        this.bacx40190 = bacx40190;
    }

    public Integer getBacx40191() {
        return bacx40191;
    }

    public void setBacx40191(Integer bacx40191) {
        this.bacx40191 = bacx40191;
    }

    public Integer getBacx40192() {
        return bacx40192;
    }

    public void setBacx40192(Integer bacx40192) {
        this.bacx40192 = bacx40192;
    }

    public Integer getBacx40193() {
        return bacx40193;
    }

    public void setBacx40193(Integer bacx40193) {
        this.bacx40193 = bacx40193;
    }

    public Integer getBacx40194() {
        return bacx40194;
    }

    public void setBacx40194(Integer bacx40194) {
        this.bacx40194 = bacx40194;
    }

    public Integer getBacx40195() {
        return bacx40195;
    }

    public void setBacx40195(Integer bacx40195) {
        this.bacx40195 = bacx40195;
    }

    public Integer getBacx40197() {
        return bacx40197;
    }

    public void setBacx40197(Integer bacx40197) {
        this.bacx40197 = bacx40197;
    }

    public Integer getBacx40199() {
        return bacx40199;
    }

    public void setBacx40199(Integer bacx40199) {
        this.bacx40199 = bacx40199;
    }

    public Integer getBacx40204() {
        return bacx40204;
    }

    public void setBacx40204(Integer bacx40204) {
        this.bacx40204 = bacx40204;
    }

    public Integer getBacx40205() {
        return bacx40205;
    }

    public void setBacx40205(Integer bacx40205) {
        this.bacx40205 = bacx40205;
    }

    public Integer getBacx40217() {
        return bacx40217;
    }

    public void setBacx40217(Integer bacx40217) {
        this.bacx40217 = bacx40217;
    }

    public Integer getBacx40218() {
        return bacx40218;
    }

    public void setBacx40218(Integer bacx40218) {
        this.bacx40218 = bacx40218;
    }

    public Integer getBacx40219() {
        return bacx40219;
    }

    public void setBacx40219(Integer bacx40219) {
        this.bacx40219 = bacx40219;
    }

    public Integer getBacx40221() {
        return bacx40221;
    }

    public void setBacx40221(Integer bacx40221) {
        this.bacx40221 = bacx40221;
    }

    public Integer getBacx40222() {
        return bacx40222;
    }

    public void setBacx40222(Integer bacx40222) {
        this.bacx40222 = bacx40222;
    }

    public Integer getBacx40223() {
        return bacx40223;
    }

    public void setBacx40223(Integer bacx40223) {
        this.bacx40223 = bacx40223;
    }

    public Integer getBacx40225() {
        return bacx40225;
    }

    public void setBacx40225(Integer bacx40225) {
        this.bacx40225 = bacx40225;
    }

    public Integer getBacx40226() {
        return bacx40226;
    }

    public void setBacx40226(Integer bacx40226) {
        this.bacx40226 = bacx40226;
    }

    public Integer getBacx40230() {
        return bacx40230;
    }

    public void setBacx40230(Integer bacx40230) {
        this.bacx40230 = bacx40230;
    }

    public Integer getBacx40233() {
        return bacx40233;
    }

    public void setBacx40233(Integer bacx40233) {
        this.bacx40233 = bacx40233;
    }

    public Integer getBacx40234() {
        return bacx40234;
    }

    public void setBacx40234(Integer bacx40234) {
        this.bacx40234 = bacx40234;
    }

    public Integer getBacx40244() {
        return bacx40244;
    }

    public void setBacx40244(Integer bacx40244) {
        this.bacx40244 = bacx40244;
    }

    public Integer getBacx40245() {
        return bacx40245;
    }

    public void setBacx40245(Integer bacx40245) {
        this.bacx40245 = bacx40245;
    }

    public Integer getBacx40247() {
        return bacx40247;
    }

    public void setBacx40247(Integer bacx40247) {
        this.bacx40247 = bacx40247;
    }

    public Integer getBacx40248() {
        return bacx40248;
    }

    public void setBacx40248(Integer bacx40248) {
        this.bacx40248 = bacx40248;
    }

    public Integer getBacx40250() {
        return bacx40250;
    }

    public void setBacx40250(Integer bacx40250) {
        this.bacx40250 = bacx40250;
    }

    public Integer getBacx40256() {
        return bacx40256;
    }

    public void setBacx40256(Integer bacx40256) {
        this.bacx40256 = bacx40256;
    }

    public Integer getBacx40257() {
        return bacx40257;
    }

    public void setBacx40257(Integer bacx40257) {
        this.bacx40257 = bacx40257;
    }

    public Integer getBacx40269() {
        return bacx40269;
    }

    public void setBacx40269(Integer bacx40269) {
        this.bacx40269 = bacx40269;
    }

    public Integer getBacx40273() {
        return bacx40273;
    }

    public void setBacx40273(Integer bacx40273) {
        this.bacx40273 = bacx40273;
    }

    public Integer getBacx40279() {
        return bacx40279;
    }

    public void setBacx40279(Integer bacx40279) {
        this.bacx40279 = bacx40279;
    }

    public Integer getBacx40287() {
        return bacx40287;
    }

    public void setBacx40287(Integer bacx40287) {
        this.bacx40287 = bacx40287;
    }

    public Integer getFlws40000() {
        return flws40000;
    }

    public void setFlws40000(Integer flws40000) {
        this.flws40000 = flws40000;
    }

    public Integer getFlws40043() {
        return flws40043;
    }

    public void setFlws40043(Integer flws40043) {
        this.flws40043 = flws40043;
    }

    public Integer getFlws40044() {
        return flws40044;
    }

    public void setFlws40044(Integer flws40044) {
        this.flws40044 = flws40044;
    }

    public Integer getFlws40156() {
        return flws40156;
    }

    public void setFlws40156(Integer flws40156) {
        this.flws40156 = flws40156;
    }

    public Integer getFlws40157() {
        return flws40157;
    }

    public void setFlws40157(Integer flws40157) {
        this.flws40157 = flws40157;
    }

    public Integer getFlws40186() {
        return flws40186;
    }

    public void setFlws40186(Integer flws40186) {
        this.flws40186 = flws40186;
    }

    public Integer getFlws40189() {
        return flws40189;
    }

    public void setFlws40189(Integer flws40189) {
        this.flws40189 = flws40189;
    }

    public Integer getFlws40242() {
        return flws40242;
    }

    public void setFlws40242(Integer flws40242) {
        this.flws40242 = flws40242;
    }

    public Integer getSf40038() {
        return sf40038;
    }

    public void setSf40038(Integer sf40038) {
        this.sf40038 = sf40038;
    }

    public Integer getSf40184() {
        return sf40184;
    }

    public void setSf40184(Integer sf40184) {
        this.sf40184 = sf40184;
    }

    public Integer getSf40198() {
        return sf40198;
    }

    public void setSf40198(Integer sf40198) {
        this.sf40198 = sf40198;
    }

    public Integer getXtgf40000() {
        return xtgf40000;
    }

    public void setXtgf40000(Integer xtgf40000) {
        this.xtgf40000 = xtgf40000;
    }

    public Integer getXtgf40018() {
        return xtgf40018;
    }

    public void setXtgf40018(Integer xtgf40018) {
        this.xtgf40018 = xtgf40018;
    }

    public Integer getXtgf40097() {
        return xtgf40097;
    }

    public void setXtgf40097(Integer xtgf40097) {
        this.xtgf40097 = xtgf40097;
    }

    public Integer getXtgf40110() {
        return xtgf40110;
    }

    public void setXtgf40110(Integer xtgf40110) {
        this.xtgf40110 = xtgf40110;
    }

    public Integer getXtgf40162() {
        return xtgf40162;
    }

    public void setXtgf40162(Integer xtgf40162) {
        this.xtgf40162 = xtgf40162;
    }

    public Integer getXtgf40177() {
        return xtgf40177;
    }

    public void setXtgf40177(Integer xtgf40177) {
        this.xtgf40177 = xtgf40177;
    }

    public Integer getXtgf40178() {
        return xtgf40178;
    }

    public void setXtgf40178(Integer xtgf40178) {
        this.xtgf40178 = xtgf40178;
    }

    public Integer getXtgf40196() {
        return xtgf40196;
    }

    public void setXtgf40196(Integer xtgf40196) {
        this.xtgf40196 = xtgf40196;
    }

    public Integer getQt40000() {
        return qt40000;
    }

    public void setQt40000(Integer qt40000) {
        this.qt40000 = qt40000;
    }

    public Integer getQt40049() {
        return qt40049;
    }

    public void setQt40049(Integer qt40049) {
        this.qt40049 = qt40049;
    }

    public Integer getQt40050() {
        return qt40050;
    }

    public void setQt40050(Integer qt40050) {
        this.qt40050 = qt40050;
    }

    public Integer getQt40154() {
        return qt40154;
    }

    public void setQt40154(Integer qt40154) {
        this.qt40154 = qt40154;
    }

    public Integer getZcjd40002() {
        return zcjd40002;
    }

    public void setZcjd40002(Integer zcjd40002) {
        this.zcjd40002 = zcjd40002;
    }

    public Integer getZcjd40039() {
        return zcjd40039;
    }

    public void setZcjd40039(Integer zcjd40039) {
        this.zcjd40039 = zcjd40039;
    }

    public Integer getZcjd40040() {
        return zcjd40040;
    }

    public void setZcjd40040(Integer zcjd40040) {
        this.zcjd40040 = zcjd40040;
    }

    public Integer getZcjd40053() {
        return zcjd40053;
    }

    public void setZcjd40053(Integer zcjd40053) {
        this.zcjd40053 = zcjd40053;
    }

    public Integer getZcjd40054() {
        return zcjd40054;
    }

    public void setZcjd40054(Integer zcjd40054) {
        this.zcjd40054 = zcjd40054;
    }

    public Integer getZcjd40057() {
        return zcjd40057;
    }

    public void setZcjd40057(Integer zcjd40057) {
        this.zcjd40057 = zcjd40057;
    }

    public Integer getZcjd40063() {
        return zcjd40063;
    }

    public void setZcjd40063(Integer zcjd40063) {
        this.zcjd40063 = zcjd40063;
    }

    public Integer getZcjd40078() {
        return zcjd40078;
    }

    public void setZcjd40078(Integer zcjd40078) {
        this.zcjd40078 = zcjd40078;
    }

    public Integer getZcjd40084() {
        return zcjd40084;
    }

    public void setZcjd40084(Integer zcjd40084) {
        this.zcjd40084 = zcjd40084;
    }

    public Integer getZcjd40085() {
        return zcjd40085;
    }

    public void setZcjd40085(Integer zcjd40085) {
        this.zcjd40085 = zcjd40085;
    }

    public Integer getZcjd40086() {
        return zcjd40086;
    }

    public void setZcjd40086(Integer zcjd40086) {
        this.zcjd40086 = zcjd40086;
    }

    public Integer getZcjd40100() {
        return zcjd40100;
    }

    public void setZcjd40100(Integer zcjd40100) {
        this.zcjd40100 = zcjd40100;
    }

    public Integer getZcjd40132() {
        return zcjd40132;
    }

    public void setZcjd40132(Integer zcjd40132) {
        this.zcjd40132 = zcjd40132;
    }

    public Integer getZcjd40133() {
        return zcjd40133;
    }

    public void setZcjd40133(Integer zcjd40133) {
        this.zcjd40133 = zcjd40133;
    }

    public Integer getZcjd40158() {
        return zcjd40158;
    }

    public void setZcjd40158(Integer zcjd40158) {
        this.zcjd40158 = zcjd40158;
    }

    public Integer getZcjd40173() {
        return zcjd40173;
    }

    public void setZcjd40173(Integer zcjd40173) {
        this.zcjd40173 = zcjd40173;
    }

    public Integer getCxesg40000() {
        return cxesg40000;
    }

    public void setCxesg40000(Integer cxesg40000) {
        this.cxesg40000 = cxesg40000;
    }

    public Integer getCxesg40105() {
        return cxesg40105;
    }

    public void setCxesg40105(Integer cxesg40105) {
        this.cxesg40105 = cxesg40105;
    }

    public Integer getCxesg40115() {
        return cxesg40115;
    }

    public void setCxesg40115(Integer cxesg40115) {
        this.cxesg40115 = cxesg40115;
    }

    public Integer getCxesg40172() {
        return cxesg40172;
    }

    public void setCxesg40172(Integer cxesg40172) {
        this.cxesg40172 = cxesg40172;
    }

    public Integer getCxesg40204() {
        return cxesg40204;
    }

    public void setCxesg40204(Integer cxesg40204) {
        this.cxesg40204 = cxesg40204;
    }

    public Integer getTb40000() {
        return tb40000;
    }

    public void setTb40000(Integer tb40000) {
        this.tb40000 = tb40000;
    }

    public Integer getTb40048() {
        return tb40048;
    }

    public void setTb40048(Integer tb40048) {
        this.tb40048 = tb40048;
    }

    public Integer getTb40083() {
        return tb40083;
    }

    public void setTb40083(Integer tb40083) {
        this.tb40083 = tb40083;
    }

    public Integer getTb40092() {
        return tb40092;
    }

    public void setTb40092(Integer tb40092) {
        this.tb40092 = tb40092;
    }

    public Integer getTb40095() {
        return tb40095;
    }

    public void setTb40095(Integer tb40095) {
        this.tb40095 = tb40095;
    }

    public Integer getTb40106() {
        return tb40106;
    }

    public void setTb40106(Integer tb40106) {
        this.tb40106 = tb40106;
    }

    public Integer getTb40108() {
        return tb40108;
    }

    public void setTb40108(Integer tb40108) {
        this.tb40108 = tb40108;
    }

    public Integer getTb40109() {
        return tb40109;
    }

    public void setTb40109(Integer tb40109) {
        this.tb40109 = tb40109;
    }

    public Integer getTb40113() {
        return tb40113;
    }

    public void setTb40113(Integer tb40113) {
        this.tb40113 = tb40113;
    }

    public Integer getTb40117() {
        return tb40117;
    }

    public void setTb40117(Integer tb40117) {
        this.tb40117 = tb40117;
    }

    public Integer getTb40187() {
        return tb40187;
    }

    public void setTb40187(Integer tb40187) {
        this.tb40187 = tb40187;
    }

    public Integer getTb40206() {
        return tb40206;
    }

    public void setTb40206(Integer tb40206) {
        this.tb40206 = tb40206;
    }

    public Integer getTb40211() {
        return tb40211;
    }

    public void setTb40211(Integer tb40211) {
        this.tb40211 = tb40211;
    }

    public Integer getTb40232() {
        return tb40232;
    }

    public void setTb40232(Integer tb40232) {
        this.tb40232 = tb40232;
    }

    public Integer getTb40243() {
        return tb40243;
    }

    public void setTb40243(Integer tb40243) {
        this.tb40243 = tb40243;
    }

    public Integer getTb40285() {
        return tb40285;
    }

    public void setTb40285(Integer tb40285) {
        this.tb40285 = tb40285;
    }

    public Integer getCxesw40000() {
        return cxesw40000;
    }

    public void setCxesw40000(Integer cxesw40000) {
        this.cxesw40000 = cxesw40000;
    }

    public Integer getCxesw40105() {
        return cxesw40105;
    }

    public void setCxesw40105(Integer cxesw40105) {
        this.cxesw40105 = cxesw40105;
    }

    public Integer getCxesw40115() {
        return cxesw40115;
    }

    public void setCxesw40115(Integer cxesw40115) {
        this.cxesw40115 = cxesw40115;
    }

    public Integer getCxesw40172() {
        return cxesw40172;
    }

    public void setCxesw40172(Integer cxesw40172) {
        this.cxesw40172 = cxesw40172;
    }

    public Integer getCxesw40204() {
        return cxesw40204;
    }

    public void setCxesw40204(Integer cxesw40204) {
        this.cxesw40204 = cxesw40204;
    }

    public Integer getFljd40000() {
        return fljd40000;
    }

    public void setFljd40000(Integer fljd40000) {
        this.fljd40000 = fljd40000;
    }

    public Integer getFljd40200() {
        return fljd40200;
    }

    public void setFljd40200(Integer fljd40200) {
        this.fljd40200 = fljd40200;
    }

    public Integer getFljd40201() {
        return fljd40201;
    }

    public void setFljd40201(Integer fljd40201) {
        this.fljd40201 = fljd40201;
    }

    public Integer getFljd40202() {
        return fljd40202;
    }

    public void setFljd40202(Integer fljd40202) {
        this.fljd40202 = fljd40202;
    }

    public Integer getFljd40203() {
        return fljd40203;
    }

    public void setFljd40203(Integer fljd40203) {
        this.fljd40203 = fljd40203;
    }
}


