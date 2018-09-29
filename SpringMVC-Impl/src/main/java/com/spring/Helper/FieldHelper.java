package com.spring.Helper;

import com.spring.Annotation.Autowired;
import com.spring.Bean.BeanFactory;
import com.spring.Bean.ClassFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author fantasy
 * @date 2018/9/28
 * @time 14:20
 */
public class FieldHelper {

    public static void initField() {
        Map beanContainer = BeanFactory.getBeanCantainer();
        beanContainer.forEach((className, instance) -> {
            Field[] fields = instance.getClass().getDeclaredFields();
            for (Field filed : fields) {
                if (filed.isAnnotationPresent(Autowired.class)) {
                    String filedClassName = filed.getType().getName();
                    Object fieldInstance = beanContainer.get(filedClassName);

                    try {
                        filed.setAccessible(true);
                        filed.set(instance, fieldInstance);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
