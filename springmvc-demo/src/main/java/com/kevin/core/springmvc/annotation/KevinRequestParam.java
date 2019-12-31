package com.kevin.core.springmvc.annotation;

import java.lang.annotation.*;

/**
 * @Author kevin
 * @Description 接口绑定参数
 * @Date Created on 2019/12/31 9:27
 * @see org.springframework.web.bind.annotation.RequestParam
 */

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface KevinRequestParam {

    String value() default "";
}
