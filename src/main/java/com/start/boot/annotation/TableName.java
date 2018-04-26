package com.start.boot.annotation;

import java.lang.annotation.*;

/**
 * 实体对应的表名,打了这个注解。自动映射表名
 * Created by caomin on 2017/11/14.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TableName {
    String value();
}
