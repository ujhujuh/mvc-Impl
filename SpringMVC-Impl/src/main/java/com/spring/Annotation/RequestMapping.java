package com.spring.Annotation;

import com.spring.Util.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fantasy
 * @date 2018/9/28
 * @time 11:10
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value() default "";
    String method() default Constants.HTTP_REQUEST_GET;
}
