package com.kevin.core.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @Author kevin
 * @Description 自动注入注解
 * @Date Created on 2019/12/31 9:23
 * @see org.springframework.beans.factory.annotation.Autowired
 */

@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KevinAutowired {

    String value() default "";
}
