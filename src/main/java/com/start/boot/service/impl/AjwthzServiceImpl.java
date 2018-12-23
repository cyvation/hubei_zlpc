package com.start.boot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.start.boot.dao.ajpc.AjwthzMapper;
import com.start.boot.pojo.vo.AjpcwtxVo;
import com.start.boot.service.AjwthzService;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class AjwthzServiceImpl implements AjwthzService {
    @Autowired
    private AjwthzMapper ajwthzMapper;

    @Override
    public List<AjpcwtxVo> getAjwthzList(Map query) throws Exception {
        List<AjpcwtxVo> list = null;
        try {

            String endDate = (String) query.get("endDate");
            if (!StringUtils.isEmpty(endDate)){
                endDate = DateTime.parse(endDate).plusDays(1).toString("yyyy-MM-dd");
                query.put("endDate",endDate);
            }

            String pcendDate = (String) query.get("pcendDate");
            if (!StringUtils.isEmpty(pcendDate)){
                pcendDate = DateTime.parse(pcendDate).plusDays(1).toString("yyyy-MM-dd");
                query.put("pcendDate",pcendDate);
            }

           // query.put("date", "".equals(query.get("date")) ? "" : (query.get("date") + "").split(","));
            query.put("dwbm", "".equals(query.get("dwbm")) ? "" : (query.get("dwbm") + "").split(","));
            query.put("pcflbm", "".equals(query.get("pcflbm")) ? "" : (query.get("pcflbm") + "").split(","));
//            query.put("ywtx", "".equals(query.get("ywtx")) ? "" : (query.get("ywtx") + ""));
            //问题案件总数
            list = ajwthzMapper.getAjwthzList(query);
            int wtajzs = list.stream().mapToInt(AjpcwtxVo::getWts).sum();
            Double all=Double.valueOf(wtajzs);
            //算比例
            if(wtajzs>0) {
                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setBfzb(decimalFormat.format((list.get(i).getWts() / all) * 100) + "%");
                }
            }
        } catch (Exception e) {
            throw e;
        }
        return list;
    }



    @Override
    public String getDwAjwthzBarData(Map query) throws Exception {
        try {
            query.put("date", "".equals(query.get("date")) ? "" : (query.get("date") + "").split(","));
            query.put("dwbm", "".equals(query.get("dwbm")) ? "" : (query.get("dwbm") + "").split(","));
            query.put("pcflbm", "".equals(query.get("pcflbm")) ? "" : (query.get("pcflbm") + "").split(","));
            query.put("ywtx", "".equals(query.get("ywtx")) ? "" : (query.get("ywtx") + "").split(","));
            return  initBarAjwthzData(ajwthzMapper.getBarAjwthzList(query));
        } catch (Exception e) {
            throw e;
        }
    }

    //评查项分类父名称是否存在
    private int getIndexByPcxflfmc(String pcxflfmc, List<List<Object>> lists) {
        if (lists.size() < 0) {
            return -1;
        }
        List<Object> list = null;
        for (int i = 0; i < lists.size(); i++) {
            list = lists.get(i);
            if (list.get(0).toString().equals(pcxflfmc)) {
                return i;
            }
        }
        return -1;
    }

    private String initBarAjwthzData(List<AjpcwtxVo> list) throws Exception{
        JSONObject jsonObj = new JSONObject();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        List<List<Object>> source = new ArrayList<List<Object>>();
        resultMap.put("source", source);
        jsonObj.put("dataset", resultMap);
        List<Object> pcxCounts = null;
        try {
            AjpcwtxVo ajpcwtx = null;
            String pcxflfmc = "";
            Integer index;
            for (int i = 0; i < list.size(); i++) {
                ajpcwtx = list.get(i);
                pcxflfmc = ajpcwtx.getPcxflfmc();
                index = getIndexByPcxflfmc(pcxflfmc, source);
                if (index == -1) {
                    pcxCounts = new ArrayList<Object>();
                    source.add(pcxCounts);
                    index = source.indexOf(pcxCounts);
                    pcxCounts.add(new Object());
                    pcxCounts.add(0);
                    pcxCounts.add(0);
                    pcxCounts.add(new String());
                    pcxCounts.add(new String());
                    pcxCounts.set(0, pcxflfmc);
                }
                pcxCounts = source.get(index);
                if (ajpcwtx.getPcxflmc().equals("错误")) {
                    pcxCounts.set(1, ajpcwtx.getWts());
                    pcxCounts.set(3, ajpcwtx.getPcxflbm());
                } else if (ajpcwtx.getPcxflmc().equals("瑕疵")) {
                    pcxCounts.set(2, ajpcwtx.getWts());
                    pcxCounts.set(4, ajpcwtx.getPcxflbm());
                }

            }
            List<Object> productList = new ArrayList<Object>();
            productList.add("product");
            productList.add("错误");
            productList.add("瑕疵");
            source.add(0, productList);
        } catch (Exception e) {
            throw e;
        }
        return jsonObj.toJSONString();
    }
    @Override
    public Map getAjhzjbxx(Map map) {
        map.put("date", "".equals(map.get("date")) ? "" : (map.get("date") + "").split(","));
        map.put("dwbm", "".equals(map.get("dwbm")) ? "" : (map.get("dwbm") + "").split(","));
        map.put("pcflbm", "".equals(map.get("pcflbm")) ? "" : (map.get("pcflbm") + "").split(","));
        map.put("ywtx", "".equals(map.get("ywtx")) ? "" : (map.get("ywtx") + "").split(","));
        int pagenum = (Integer.parseInt(map.get("page") + "") - 1) * Integer.parseInt(map.get("row") + "");
        int row = Integer.parseInt(map.get("row") + "") + pagenum;
        map.put("page", pagenum);
        map.put("row", row);
        Map dataMap = new HashMap();
        List<Map> list = ajwthzMapper.getAjhzjbxx(map);
        Map count = ajwthzMapper.getAjhzjbxxCount(map);
        dataMap.put("total", count.get("NUM"));
        dataMap.put("rows", list);
        return dataMap;
    }
}
