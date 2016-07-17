package com.jfinal.ext.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述： 在model类上映射对应表信息 tableName 表名; pkName 主键名
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface ModelMapping {
    /**
     * 表名
     */
    String tableName();

    /**
     * 主键名
     */
    String pkName();
}
