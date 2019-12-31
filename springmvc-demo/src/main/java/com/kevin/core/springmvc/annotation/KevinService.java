package com.kevin.core.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/12/31 9:38
 * @see org.springframework.stereotype.Service
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KevinService {

    String value() default "";

}
