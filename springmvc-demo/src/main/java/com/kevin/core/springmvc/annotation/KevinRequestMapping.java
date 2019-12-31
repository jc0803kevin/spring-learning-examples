package com.kevin.core.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @Author kevin
 * @Description 接口地址
 * @Date Created on 2019/12/31 9:26
 * @see org.springframework.web.bind.annotation.RequestMapping
 */

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface KevinRequestMapping {

    String value() default "";
}
