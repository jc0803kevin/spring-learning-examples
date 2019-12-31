package com.kevin.core.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @Author kevin
 * @Description 接口绑定参数
 * @Date Created on 2019/12/31 9:27
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface KevinRequestParam {

    String value() default "";
}
