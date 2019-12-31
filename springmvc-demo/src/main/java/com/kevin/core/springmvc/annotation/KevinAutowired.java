package com.kevin.core.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @Author kevin
 * @Description 自动注入注解
 * @Date Created on 2019/12/31 9:23
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface KevinAutowired {

    String value() default "";
}
