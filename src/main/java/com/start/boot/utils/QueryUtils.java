package com.start.boot.utils;

import com.start.boot.annotation.BseeColumnName;
import com.start.boot.dao.ajpc.QueryUtilsMapper;
import com.start.boot.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 查询工具类
 * Created by caomin on 2017/11/14.
 */
@Component
public class QueryUtils<T>  {
    public QueryUtils(){}

    @Autowired
    QueryUtilsMapper queryMapper;

    /**
     * 根据查询条件返回map结果集
     * @param query
     * @return
     * @throws Exception
     */
    public  List<Map<String,Object>> getMap(Query query) throws Exception {
        if (query.getColumnNames()==null){
            query.build();
        }
        Assert.notNull(query.getTableName(),"表名不能为空！");
        Assert.notNull(query.getColumnNames(),"请指定要查询的实体类或要查询的列名");
        List<Map<String,Object>> results = queryMapper.query(query);
        return results;
    }

    /**
     * 根据查询条件，返回java对象结果集
     * @param query
     * @return
     * @throws Exception
     */
    public  List<T> getJavaBean(Query query) throws Exception {
        if (query.getColumnNames()==null||query.getColumnNames().equalsIgnoreCase("*")){
            query.build();
        }
        Assert.notNull(query.getTableName(),"表名不能为空！");
        Assert.notNull(query.getColumnNames(),"请指定要查询的实体类或要查询的列名");
        Class  aClass = query.getClazz();
        Assert.notNull(aClass,"class对象不能为空！");
        ArrayList<T> javaResult = new ArrayList<>();
        List<Map<String,Object>> results = queryMapper.query(query);
        if (results.isEmpty()){
            return null;
        }
        for (Map<String,Object> result : results) {
            T o = (T) aClass.newInstance();
            for (Map.Entry<String, Object> entry : result.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                try {
                    Field declaredField = aClass.getDeclaredField(key);
                    declaredField.setAccessible(true);
                    setProperty(o, value, declaredField);
                } catch (NoSuchFieldException e) {//没有找到这个属性直接报错。。。
                    try {
                        Field declaredField = aClass.getDeclaredField(key.toLowerCase());
                        declaredField.setAccessible(true);
                        setProperty(o, value, declaredField);
                    }catch (Exception e1){
                        Field[] declaredFields = aClass.getDeclaredFields();
                        for (Field field : declaredFields) {
                            field.setAccessible(true);
                            BseeColumnName annotation = field.getAnnotation(BseeColumnName.class);
                            if (annotation != null) {
                                if (annotation.value().equalsIgnoreCase(key)) {
                                    setProperty(o, value, field);
                                }
                            }
                        }
                    }
                }
            }
            javaResult.add(o);
        }
        return javaResult;
    }

    /**
     * 设置属性
     * @param o 对象
     * @param value 值
     * @param field 字段
     * @throws IllegalAccessException
     */
    private void setProperty(Object o, Object value, Field field) throws IllegalAccessException {
        if (value instanceof Short){
            field.set(o,value);
        }else if (value instanceof BigDecimal){
            BigDecimal bigDecimal= (BigDecimal) value;

            String simpleName = field.getType().getSimpleName();
            if (simpleName.equalsIgnoreCase("Integer")){
                int intValue = bigDecimal.intValue();
                field.set(o, intValue);
            }
            else if (simpleName.equalsIgnoreCase("short")){
                short i = bigDecimal.shortValue();
                field.set(o,i);
            }
        }
        else {
            field.set(o, value);
        }
    }
}
