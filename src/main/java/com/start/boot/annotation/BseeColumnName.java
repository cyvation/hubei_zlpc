package com.start.boot.annotation;

import java.lang.annotation.*;

/**
 * 实体属性对应表中列的名称
 * 属性与列名称不对应时使用
 * 如：数据库列名是person_mc,java对象是mc 使用此注解。
 * 在java mc这个字段上打 @BseeColumnName（value="person_mc"）
 * Created by caomin on 2017/11/14.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BseeColumnName {
    String value();
}
