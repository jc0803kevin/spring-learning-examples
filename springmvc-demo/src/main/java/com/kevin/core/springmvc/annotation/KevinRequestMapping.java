package com.kevin.core.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @Author kevin
 * @Description 接口地址
 * @Date Created on 2019/12/31 9:26
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface KevinRequestMapping {

    String value() default "";
}
