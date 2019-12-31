package com.kevin.core.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @Author kevin
 * @Description 控制器注解
 * @Date Created on 2019/12/31 9:25
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface KevinController {

    String value() default "";

}
