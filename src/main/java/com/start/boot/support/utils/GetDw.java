package com.start.boot.support.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by lei on 2017/10/29.
 */
@SuppressWarnings("all")
public class GetDw {

    /**
     * 找到本单位的父级单位
     * @param list 所有单位
     * @param currentDwbm 当前单位
     * @return
     */
    public static String topBM(List<Map> list, String currentDwbm) {

        String topBM = "";
        //遍历集合，找到本单位的父单位
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> resultMap = list.get(i);
            for (String key : resultMap.keySet()) {
                if (key.equals("BM") && resultMap.get(key).equals(currentDwbm)) {
                    topBM = resultMap.get("FBM");
                    break;
                }
            }
        }

        return topBM;
    }
}
