package com.start.boot.support.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.util.TypeUtils;
import com.start.boot.domain.Param_Pcy;

import java.util.List;

/**
 * 阿里巴巴 json 工具,性能最佳
 */
public class FastJsonUtils {
    /**
     * 对象转化为Json字符串
     *
     * @param object 对象
     * @return Json字符串
     */
    public static String toString(Object object) {
        TypeUtils.compatibleWithJavaBean=true;
        if (object instanceof Param_Pcy){
           return JSON.toJSONString(object,new PascalNameFilter());
        }
        return JSON.toJSONString(object);
    }

    /**
     * Json字符串转化为对象
     *
     * @param <T>   泛型
     * @param clazz Java类
     * @param json  Json字符串
     * @return 对象
     */
    public static <T> T toObject(Class<T> clazz, String json) {
        if (json == null) {
            return null;
        }
        json = json.trim();
        int indexOf = json.indexOf("{");
        int indexOf2 = json.indexOf("[");
        if (indexOf > 0) {
            if (indexOf2 > 0) {
                if (indexOf > indexOf2) {
                    json = json.substring(indexOf2);
                } else {
                    json = json.substring(indexOf);
                }
            } else if (indexOf2 < 0) {
                json = json.substring(indexOf);
            }
        } else if (indexOf < 0) {
            if (indexOf2 > 0) {
                json = json.substring(indexOf2);
            } else if (indexOf2 < 0) {
                return null;
            }
        }

        if (json.endsWith(";")) {
            json = json.substring(0, json.length() - 1);
        }

        return JSON.parseObject(json, clazz);
    }


    /**
     * Json字符串转化为List
     *
     * @param <T>   泛型
     * @param clazz Java类
     * @param json  Json字符串
     * @return 对象
     */
    public static <T> List<T> toList(Class<T> clazz, String json) {
        if (json == null) {
            return null;
        }
        json = json.trim();
        int indexOf = json.indexOf("{");
        int indexOf2 = json.indexOf("[");
        if (indexOf > 0) {
            if (indexOf2 > 0) {
                if (indexOf > indexOf2) {
                    json = json.substring(indexOf2);
                } else {
                    json = json.substring(indexOf);
                }
            } else if (indexOf2 < 0) {
                json = json.substring(indexOf);
            }
        } else if (indexOf < 0) {
            if (indexOf2 > 0) {
                json = json.substring(indexOf2);
            } else if (indexOf2 < 0) {
                return null;
            }
        }

        if (json.endsWith(";")) {
            json = json.substring(0, json.length() - 1);
        }

        return JSON.parseArray(json, clazz);
    }


}
