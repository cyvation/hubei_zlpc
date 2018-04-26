package com.start.boot.pojo.vo;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author caomin
 * @date 2018/3/19
 * @说明 excel导出map数据
 */
public class ExcelMapDataVo extends ExcelBaeeVo {

    private List<Map>dataMap;

    private List<String> keys;

    public List<Map> getDataMap() {
        if (CollectionUtils.isEmpty(dataMap))
            dataMap=new ArrayList<>();
        return dataMap;
    }

    public void setDataMap(List<Map> dataMap) {
        this.dataMap = dataMap;
    }

    public List<String> getKeys() {
        if (CollectionUtils.isEmpty(keys))
            keys=new ArrayList<>();
        return keys;
    }

    public void setKeys(List<String> keys) {
        this.keys = keys;
    }
}
