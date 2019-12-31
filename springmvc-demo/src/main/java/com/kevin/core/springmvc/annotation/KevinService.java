package com.kevin.core.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @Author kevin
 * @Description
 * @Date Created on 2019/12/31 9:38
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface KevinService {

    String value() default "";

}
