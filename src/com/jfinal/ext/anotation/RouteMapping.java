package com.jfinal.ext.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述:配制Controller的访问路径 属性： 1 controllerKey 访问Controller的路径 2 viewPath default =
 * "" index()方法要跳转的页面，强烈要求在index()方法内指定，不在这指定。
 * 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE })
public @interface RouteMapping {
    /**
     * 访问Controller的路径
     */
    public String controllerKey();

    /**
     * Controller跳转到的页面路径。就是index()方法要跳转的页面。强烈要求在方法内指定，不在这指定
     */
    public String viewPath() default "";
}
