package com.start.boot.annotation;

import java.lang.annotation.*;

/**
 * @author caomin
 * @date 2018/3/22
 * @说明 excel导出工具类
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelProperty {

    /**
     * 相当于行标题，懂？
     * @return
     */
    String name();

    /**
     * 排序，例如有三个字段。则他们顺序依次为 0 1 2
     * @return
     */
    int order() default -1;



}
