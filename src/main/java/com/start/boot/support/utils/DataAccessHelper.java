package com.start.boot.support.utils;

import java.util.List;
import java.util.Map;

public class DataAccessHelper {

    public static String getString(Map map, String key){

        return (String)map.get(key);
    }

    public static Map getMap(Map map, String key){

        return (Map)map.get(key);
    }

    public static List<Map> getListMap(Map map, String key){

        return (List<Map>)map.get(key);
    }

    public static Integer getInteger(Map map, String key){

        return (Integer)map.get(key);
    }
    
}
