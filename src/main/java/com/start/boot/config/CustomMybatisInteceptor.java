/*
package com.start.boot.config;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.*;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

*/
/**
 * mybatis拦截器
 *
 * @caomin
 * @create 2017-12-13 17:40
 **//*

@Intercepts({@Signature(
        type= ResultSetHandler.class,
        method = "handleResultSets",
        args = {Statement.class})})
public class CustomMybatisInteceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        // 获取到当前的Statement
        Statement stmt =  (Statement) args[0];
        // 通过Statement获得当前结果集
        ResultSet resultSet = stmt.getResultSet();
        if(resultSet != null && resultSet.next()) {
           */
/* String cursorName = resultSet.getCursorName();
            System.out.println(cursorName);*//*

        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
*/
