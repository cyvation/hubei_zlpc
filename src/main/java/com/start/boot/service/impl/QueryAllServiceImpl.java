package com.start.boot.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.start.boot.dao.ajpc.QueryAllMapper;
import com.start.boot.domain.*;
import com.start.boot.pojo.vo.QueryTableVo;
import com.start.boot.query.QueryTableAjJbxx;
import com.start.boot.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author caomin
 * @date 2018/1/28
 * @说明
 */
@Service
public class QueryAllServiceImpl implements QueryAllService {

    @Autowired
    QueryAllMapper queryallMapper;


    @Autowired
    XtDmFldmService xtDmFldmService;


    @Autowired
    XtPcLbService xtPcLbService;


    @Autowired
    XtZzjgDwbmService xtZzjgDwbmService;


    @Autowired
    XtZzjgBmbmService xtZzjgBmbmService;

    @Autowired
    XtZzjgRmbmService xtZzjgRmbmService;

    @Override
    public int getPcxFlCount(Map query) {
        return queryallMapper.getPcxFlCountOffline(query)+queryallMapper.getPcxFlCount(query);
    }

    @Override
    public int getSjCount(Map query) {
        return queryallMapper.getSjCount(query);
    }


    private volatile  int pcfxwtzs=0;
    @Override
    public List<Map> getAjjbxx(QueryTableAjJbxx query) {
        String zmc = query.getZmc();
        String pcxflmc = query.getPcxflmc();
        if(!StringUtils.isEmpty(pcxflmc)&& !StringUtils.isEmpty(zmc)) {
            List<Yx_Pc_PcxFl> pcxFlbmList = queryallMapper.getPcxFlbmList(zmc, pcxflmc);
            if (!CollectionUtils.isEmpty(pcxFlbmList)){
                List<String> collect = pcxFlbmList.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
                query.setPcxflbmList(collect);
            }
        }
        PageHelper.startPage(query.getPage(), query.getRows());
        List<Map> list= queryallMapper.getAjjbxx(query);
        list.addAll(queryallMapper.getAjjbxxOffline(query));
        return list;
    }
    @Override
    public ArrayList<QueryTableVo> getZlpcTableData(Map query)  {
        String dwbm = query.get("dwbm")+"";
        String sfhj=query.get("sfhj")+"";
        String pcflbm=query.get("pcflbm")+"";
        query.put("pcflbm",pcflbm.split(","));
        query.put("dwbm",dwbm.split(","));
        if (StringUtils.isEmpty(dwbm)){
            return null;
        }
        ArrayList<QueryTableVo> resultList = new ArrayList<>();
        pcfxwtzs=0;
        DecimalFormat decimalFormat=new DecimalFormat("0.00");
        List<XtZzjgDwbm> dwbmTreeList = sfhj.equals("Y")?queryallMapper.getDwbmTreeLists(dwbm):queryallMapper.getDwbmTreeList(dwbm.split(","));
        for(int d=0;d<dwbmTreeList.size();d++) {
            XtZzjgDwbm dw=dwbmTreeList.get(d);
            QueryTableVo temp = new QueryTableVo();
            temp.setName(dw.getDwmc());
            temp.setDwbm(dw.getDwbm());
            temp.setFdwbm(dw.getFdwbm());
            temp.setDwjb(dw.getDwjb());
            temp.setSfhj(dw.getSfhj());
            if(dw.getSfhj().equals("Y")){
                resultList.add(temp);
                continue;
            }
            query.put("dwbm", dw.getDwbm());
            getPcFlData(query, /*allXtPcLb,*/ temp);
            setPcjlData(query, temp/*, xtDmFldmBy*/);

            //=============事实认定
            setCommonData(query, temp);
            resultList.add(temp);
            query.put("pcflbm", pcflbm.split(","));
        }
        setZj(resultList);
        return resultList;
    }


    private void getPcFlData(Map query,/* List<XtPcLb> allXtPcLb,*/ QueryTableVo temp) {
        String[] pcflbm= (String[]) query.get("pcflbm");
        for(int i=0;i<pcflbm.length;i++){
            if("001".equals(pcflbm[i])){
                query.put("pcflbm",pcflbm[i].split(","));
                int pcxFlCount=getPcxFlCount(query);
                temp.setCgCount(pcxFlCount);
            }else if("007".equals(pcflbm[i])){
                query.put("pcflbm",pcflbm[i].split(","));
                int pcxFlCount=getPcxFlCount(query);
                temp.setSjpCount(pcxFlCount);
            }else if("008".equals(pcflbm[i])){
                query.put("pcflbm",pcflbm[i].split(","));
                int pcxFlCount=getPcxFlCount(query);
                temp.setZdpcCount(pcxFlCount);
            }else if("009".equals(pcflbm[i])){
                query.put("pcflbm",pcflbm[i].split(","));
                int pcxFlCount=getOfflinePcxFlCount(query);
                temp.setAjCount(pcxFlCount);
            }
        }
        query.put("pcflbm",pcflbm);
    }
    private void setPcjlData(Map query, QueryTableVo temp/*, List<XtDmFldm> xtDmFldmBy*/) {
        String[] pcflbm=  (String[]) query.get("pcflbm");
        String pcjl=query.get("pcjl")+"";
        int yzCount1=0;
        int hgCount1=0;
        int xcCount1=0;
        int bhgCount1=0;
        int yzCount2=0;
        int hgCount2=0;
        int xcCount2=0;
        int bhgCount2=0;
        for(int i=0;i<pcflbm.length;i++) {
            query.put("pcflbm",pcflbm[i].split(","));
            if("009".equals(pcflbm[i])){
                if(query.get("pcjl").equals("全部")||"".equals(query.get("pcjl"))){
                    query.put("pcjl","优质案件");
                    yzCount1 += getOfflinePcxFlCount(query);
                    query.put("pcjl","合格案件");
                    hgCount1 += getOfflinePcxFlCount(query);
                    query.put("pcjl","瑕疵案件");
                    xcCount1 += getOfflinePcxFlCount(query);
                    query.put("pcjl","不合格案件");
                    bhgCount1 += getOfflinePcxFlCount(query);
                }else {
                    if (query.get("pcjl").equals("优质案件")) {
                        yzCount1 += getOfflinePcxFlCount(query);
                    } else if (query.get("pcjl").equals("合格案件")) {
                        hgCount1 += getOfflinePcxFlCount(query);
                    } else if (query.get("pcjl").equals("瑕疵案件")) {
                        xcCount1 += getOfflinePcxFlCount(query);
                    } else if (query.get("pcjl").equals("不合格案件")) {
                        bhgCount1 += getOfflinePcxFlCount(query);
                    }
                }
            }else{
                if(query.get("pcjl").equals("全部")||"".equals(query.get("pcjl"))){
                    query.put("pcjl","优质案件");
                    yzCount2 += getPcxFlCount(query);
                    query.put("pcjl","合格案件");
                    hgCount2 += getPcxFlCount(query);
                    query.put("pcjl","瑕疵案件");
                    xcCount2 += getPcxFlCount(query);
                    query.put("pcjl","不合格案件");
                    bhgCount2 += getPcxFlCount(query);
                }else {
                    if (query.get("pcjl").equals("优质案件")) {
                        yzCount2 += getPcxFlCount(query);
                    } else if (query.get("pcjl").equals("合格案件")) {
                        hgCount2 += getPcxFlCount(query);
                    } else if (query.get("pcjl").equals("瑕疵案件")) {
                        xcCount2 += getPcxFlCount(query);
                    } else if (query.get("pcjl").equals("不合格案件")) {
                        bhgCount2 += getPcxFlCount(query);
                    }
                }
            }
            temp.setYzCount( yzCount1+yzCount2);
            temp.setHgCount(hgCount1+hgCount2);
            temp.setXcCount( xcCount1+xcCount2);
            temp.setBhgCount( bhgCount1+bhgCount2);
            query.put("pcjl",pcjl);
        }
        query.put("pcflbm",pcflbm);
    }
    /**
     * 设置合计数据
     * @param resultList
     */
    private void setZj(ArrayList<QueryTableVo> resultList) {
        String dwbm="";
        for(int i=0;i<resultList.size();i++) {
            QueryTableVo map = resultList.get(i);
            if (map.getSfhj().equals("Y")) {
                Integer sjCount=0, pcCount =0, zdpcCount = 0, cgCount = 0, sjpCount = 0, ajCount = 0, yzCount = 0 , hgCount = 0 , xcCount = 0, bhgCount = 0, ssrdXcCount = 0,flwsCwCount = 0,zcjdCwCount=0,zcjdXcCount=0,
                        ssrdCuCount = 0, zjcxXcCount = 0, zjcxCuCount = 0, zjcxfqCount = 0, flsyXcCount = 0, flsyCuCount = 0, flsyfqCount = 0, bacxXcCount = 0, bacxCuCount = 0, flwsXcCount = 0, baxgYbCount = 0,
                        sfzrzlsCwCount=0,sfzrzlsXcCount=0,xtgfyyXcCount=0,qtqkCwCount=0,qtqkXcCount=0;
                int count=0;
                dwbm = map.getDwbm() + "";
                for (int a = 0; a < resultList.size(); a++) {
                    QueryTableVo maps = resultList.get(a);
                    if(maps.getSfhj().equals("Y")){
                        continue;
                    }
                    if(maps.getDwbm().equals(dwbm)||maps.getFdwbm().equals(dwbm)){
                         count++;
                         zdpcCount+= maps.getZdpcCount();
                         cgCount+= maps.getCgCount();
                         sjpCount+= maps.getSjpCount();
                         ajCount+=maps.getAjCount();
                         yzCount+= maps.getYzCount();
                         hgCount+= maps.getHgCount();
                         xcCount+= maps.getXcCount();
                         bhgCount+= maps.getBhgCount();
                         ssrdXcCount+= maps.getSsrdXcCount();
                         ssrdCuCount+= maps.getSsrdCuCount();
                         zjcxXcCount+= maps.getZjcxXcCount();
                         zjcxCuCount+= maps.getZjcxCuCount();
                         zjcxfqCount+= maps.getZjcxfqCount();
                         flsyXcCount+= maps.getFlsyXcCount();
                         flsyCuCount+= maps.getFlsyCuCount();
                         flsyfqCount+= maps.getFlsyfqCount();
                         bacxXcCount+= maps.getBacxXcCount();
                         bacxCuCount+= maps.getBacxCuCount();
                         flwsXcCount+= maps.getFlwsXcCount();
                         flwsCwCount+= maps.getFlwsCwCount();
                         zcjdCwCount+= maps.getZcjdCwCount();
                         zcjdXcCount+= maps.getZcjdXcCount();
                         sfzrzlsCwCount+= maps.getSfzrzlsCwCount();
                         sfzrzlsXcCount+= maps.getSfzrzlsXcCount();
                         xtgfyyXcCount+= maps.getXtgfyyXcCount();
                         qtqkCwCount+= maps.getQtqkCwCount();
                         qtqkXcCount+= maps.getQtqkXcCount();
                    }
                }
                map.setZdpcCount(zdpcCount);
                map.setCgCount(cgCount);
                map.setSjCount(sjpCount);
                map.setAjCount(ajCount);
                map.setYzCount(yzCount);
                map.setHgCount(hgCount);
                map.setXcCount(xcCount);
                map.setBhgCount(bhgCount);
                map.setSsrdXcCount(ssrdXcCount);
                map.setSsrdCuCount(ssrdCuCount);
                map.setZjcxXcCount(zjcxXcCount);
                map.setZjcxCuCount(zjcxCuCount);
                map.setZjcxfqCount(zjcxfqCount);
                map.setFlsyXcCount(flsyXcCount);
                map.setFlsyCuCount(flsyCuCount);
                map.setFlsyfqCount(flsyfqCount);
                map.setBacxXcCount(bacxXcCount);
                map.setBacxCuCount(bacxCuCount);
                map.setFlwsXcCount(flwsXcCount);
                map.setFlwsCwCount(flwsCwCount);
                map.setZcjdCwCount(zcjdCwCount);
                map.setZcjdXcCount(zcjdXcCount);
                map.setSfzrzlsCwCount(sfzrzlsCwCount);
                map.setSfzrzlsXcCount(sfzrzlsXcCount);
                map.setXtgfyyXcCount(xtgfyyXcCount);
                map.setQtqkCwCount(qtqkCwCount);
                map.setQtqkXcCount(qtqkXcCount);
                map.setBaxgYbCount(baxgYbCount);
            }
        }

        //获取合计数据
        Integer sjCount=0, pcCount =0, zdpcCount = 0, cgCount = 0, sjpCount = 0, ajCount = 0, yzCount = 0 , hgCount = 0 , xcCount = 0, bhgCount = 0, ssrdXcCount = 0,flwsCwCount=0,zcjdCwCount=0,zcjdXcCount=0,
                ssrdCuCount = 0, zjcxXcCount = 0, zjcxCuCount = 0, zjcxfqCount = 0, flsyXcCount = 0, flsyCuCount = 0, flsyfqCount = 0, bacxXcCount = 0, bacxCuCount = 0, flwsXcCount = 0, baxgYbCount = 0,
                sfzrzlsCwCount=0,sfzrzlsXcCount=0,xtgfyyXcCount=0,qtqkCwCount=0,qtqkXcCount=0;
        BigDecimal bigDecimal1 = new BigDecimal(0.0);
        int num=0;
        for (int a = 0; a < resultList.size(); a++) {
            QueryTableVo maps = resultList.get(a);
            if(!maps.getSfhj().equals("Y")){
                num++;
                zdpcCount+= maps.getZdpcCount();
                cgCount+= maps.getCgCount();
                sjpCount+= maps.getSjpCount();
                ajCount+=maps.getAjCount();
                yzCount+= maps.getYzCount();
                hgCount+= maps.getHgCount();
                xcCount+= maps.getXcCount();
                bhgCount+= maps.getBhgCount();
                ssrdXcCount+= maps.getSsrdXcCount();
                ssrdCuCount+= maps.getSsrdCuCount();
                zjcxXcCount+= maps.getZjcxXcCount();
                zjcxCuCount+= maps.getZjcxCuCount();
                zjcxfqCount+= maps.getZjcxfqCount();
                flsyXcCount+= maps.getFlsyXcCount();
                flsyCuCount+= maps.getFlsyCuCount();
                flsyfqCount+= maps.getFlsyfqCount();
                bacxXcCount+= maps.getBacxXcCount();
                bacxCuCount+= maps.getBacxCuCount();
                flwsXcCount+= maps.getFlwsXcCount();
                flwsCwCount+= maps.getFlwsCwCount();
                zcjdCwCount+= maps.getZcjdCwCount();
                zcjdXcCount+= maps.getZcjdXcCount();
                sfzrzlsCwCount+= maps.getSfzrzlsCwCount();
                sfzrzlsXcCount+= maps.getSfzrzlsXcCount();
                xtgfyyXcCount+= maps.getXtgfyyXcCount();
                qtqkCwCount+= maps.getQtqkCwCount();
                qtqkXcCount+= maps.getQtqkXcCount();
            }
        }
        QueryTableVo t = new QueryTableVo();
        t.setZdpcCount(zdpcCount);
        t.setCgCount(cgCount);
        t.setSjCount(sjpCount);
        t.setAjCount(ajCount);
        t.setYzCount(yzCount);
        t.setHgCount(hgCount);
        t.setXcCount(xcCount);
        t.setBhgCount(bhgCount);
        t.setSsrdXcCount(ssrdXcCount);
        t.setSsrdCuCount(ssrdCuCount);
        t.setZjcxXcCount(zjcxXcCount);
        t.setZjcxCuCount(zjcxCuCount);
        t.setZjcxfqCount(zjcxfqCount);
        t.setFlsyXcCount(flsyXcCount);
        t.setFlsyCuCount(flsyCuCount);
        t.setFlsyfqCount(flsyfqCount);
        t.setBacxXcCount(bacxXcCount);
        t.setBacxCuCount(bacxCuCount);
        t.setFlwsXcCount(flwsXcCount);
        t.setFlwsCwCount(flwsCwCount);
        t.setZcjdCwCount(zcjdCwCount);
        t.setZcjdXcCount(zcjdXcCount);
        t.setSfzrzlsCwCount(sfzrzlsCwCount);
        t.setSfzrzlsXcCount(sfzrzlsXcCount);
        t.setXtgfyyXcCount(xtgfyyXcCount);
        t.setQtqkCwCount(qtqkCwCount);
        t.setQtqkXcCount(qtqkXcCount);
        t.setBaxgYbCount(baxgYbCount);
        t.setName("合计");
        resultList.add(0,t);
    }


    private void setCommonData(Map query, QueryTableVo temp) {
        //=============事实认定
        List<Yx_Pc_PcxFl> pcxFlbmList = queryallMapper.getPcxFlbmList("事实认定", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmList)){
            List<String> collect = pcxFlbmList.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount = getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setSsrdXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        List<Yx_Pc_PcxFl> pcxFlbmList1 = queryallMapper.getPcxFlbmList("事实认定", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmList1)){
            List<String> collect = pcxFlbmList1.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setSsrdCuCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============证据采信
        List<Yx_Pc_PcxFl> pcxFlbmList2 = queryallMapper.getPcxFlbmList("证据采信", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmList2)){
            List<String> collect = pcxFlbmList2.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setZjcxCuCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        List<Yx_Pc_PcxFl> pcxFlbmList3 = queryallMapper.getPcxFlbmList("证据采信", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmList3)){
            List<String> collect = pcxFlbmList3.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setZjcxXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        List<Yx_Pc_PcxFl> pcxFlbmList4 = queryallMapper.getPcxFlbmList("证据采信", "分歧");
        if (!CollectionUtils.isEmpty(pcxFlbmList4)){
            List<String> collect = pcxFlbmList4.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setZjcxfqCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }


        //=============法律适用

        List<Yx_Pc_PcxFl> pcxFlbmList5 = queryallMapper.getPcxFlbmList("法律适用", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmList5)){
            List<String> collect = pcxFlbmList5.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setFlsyCuCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        List<Yx_Pc_PcxFl> pcxFlbmList6 = queryallMapper.getPcxFlbmList("法律适用", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmList6)){
            List<String> collect = pcxFlbmList6.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setFlsyXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        List<Yx_Pc_PcxFl> pcxFlbmList7 = queryallMapper.getPcxFlbmList("法律适用", "分歧");
        if (!CollectionUtils.isEmpty(pcxFlbmList7)){
            List<String> collect = pcxFlbmList7.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setFlsyfqCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }


        //=============办案程序
        List<Yx_Pc_PcxFl> pcxFlbmList8 = queryallMapper.getPcxFlbmList("办案程序", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmList8)){
            List<String> collect = pcxFlbmList8.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setBacxXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        List<Yx_Pc_PcxFl> pcxFlbmList9 = queryallMapper.getPcxFlbmList("办案程序", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmList9)){
            List<String> collect = pcxFlbmList9.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setBacxCuCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        //=============法律文书

        List<Yx_Pc_PcxFl> pcxFlbmList19 = queryallMapper.getPcxFlbmList("法律文书", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmList19)){
            List<String> collect = pcxFlbmList19.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setFlwsXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        List<Yx_Pc_PcxFl> pcxFlbmListflwscw = queryallMapper.getPcxFlbmList("法律文书", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmListflwscw)){
            List<String> collect = pcxFlbmListflwscw.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setFlwsCwCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }


        //=============办案效果
      /*  List<Yx_Pc_PcxFl> pcxFlbmList92 = queryallMapper.getPcxFlbmList("办案效果", "一般");
        if (!CollectionUtils.isEmpty(pcxFlbmList92)){
            List<String> collect = pcxFlbmList92.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.put("baxgYbCount",pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }*/

        //=============系统规范应用
        List<Yx_Pc_PcxFl> pcxFlbmListXtgfyyXc = queryallMapper.getPcxFlbmList("系统规范应用", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmListXtgfyyXc)){
            List<String> collect = pcxFlbmListXtgfyyXc.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setXtgfyyXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============司法责任制落实  错误
        List<Yx_Pc_PcxFl> pcxFlbmListSfzrzlsCw = queryallMapper.getPcxFlbmList("司法责任制落实", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmListSfzrzlsCw)){
            List<String> collect = pcxFlbmListSfzrzlsCw.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setSfzrzlsCwCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============司法责任制落实  瑕疵
        List<Yx_Pc_PcxFl> pcxFlbmListSfzrzlsXc = queryallMapper.getPcxFlbmList("司法责任制落实", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmListSfzrzlsXc)){
            List<String> collect = pcxFlbmListSfzrzlsXc.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setSfzrzlsXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============其他情况  错误
        List<Yx_Pc_PcxFl> pcxFlbmListQtqkCw = queryallMapper.getPcxFlbmList("其他情况", "错误");
        if (!CollectionUtils.isEmpty(pcxFlbmListQtqkCw)){
            List<String> collect = pcxFlbmListQtqkCw.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setQtqkCwCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============其他情况  瑕疵
        List<Yx_Pc_PcxFl> pcxFlbmListQtqkXc = queryallMapper.getPcxFlbmList("其他情况", "瑕疵");
        if (!CollectionUtils.isEmpty(pcxFlbmListQtqkXc)){
            List<String> collect = pcxFlbmListQtqkXc.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setQtqkXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        //=============侦查监督  错误
        List<Yx_Pc_PcxFl> zcjdCwListQtqkCw = queryallMapper.getPcxFlbmList("侦查监督", "错误");
        if (!CollectionUtils.isEmpty(zcjdCwListQtqkCw)){
            List<String> collect = zcjdCwListQtqkCw.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setZcjdCwCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }

        //=============侦查监督  瑕疵
        List<Yx_Pc_PcxFl> zcjdXcListQtqkXc = queryallMapper.getPcxFlbmList("侦查监督", "瑕疵");
        if (!CollectionUtils.isEmpty(zcjdXcListQtqkXc)){
            List<String> collect = zcjdXcListQtqkXc.stream().filter(yx_Pc_PcxFl -> yx_Pc_PcxFl.getPcxflfbm() != null).map(yx_pc_pcxFl -> yx_pc_pcxFl.getPcxflbm()).distinct().collect(Collectors.toList());
            query.put("pcxflbmList",collect);
            int pcxFlCount =  getPcxFlCount(query)+getOfflinePcxFlCount(query);
            temp.setZcjdXcCount(pcxFlCount);
            pcfxwtzs+=pcxFlCount;
        }
        query.remove("pcxflbmList");
    }
    @Override
    public int getOfflinePcxFlCount(Map query) {
        return queryallMapper.getOfflinePcxFlCount(query);
    }

}
