package com.start.boot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.start.boot.annotation.ExcelProperty;
import com.start.boot.dao.ajpc.AjwthzMapper;
import com.start.boot.service.AjwthzService;
import jdk.nashorn.internal.runtime.ECMAException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.start.boot.pojo.vo.AjpcwtxVo;
import com.start.boot.query.QueryTable;


@Service
public class AjwthzServiceImpl implements AjwthzService {
    @Autowired
    private AjwthzMapper ajwthzMapper;

    @Override
    public List<AjpcwtxVo> getAjwthzList(QueryTable query) throws Exception {
        List<AjpcwtxVo> list = null;
        try {
            list = ajwthzMapper.getAjwthzList(query);
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public List<AjpcwtxVo> getOfflineAjwthzList(QueryTable query) throws Exception {
        List<AjpcwtxVo> list = null;
        try {
            list = ajwthzMapper.getOfflineAjwthzList(query);
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public String getDwAjwthzBarData(QueryTable query) throws Exception {
        try {
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
    public String getDwOfflineAjwthzBarData(QueryTable query) throws Exception {
        try {
            return  initBarAjwthzData(ajwthzMapper.getOfflineBarAjwthzList(query));
        } catch (Exception e) {
            throw e;
        }
    }
}
