package com.spring.Helper;

import com.spring.Annotation.Controller;
import com.spring.Annotation.RequestMapping;
import com.spring.Bean.BeanFactory;
import com.spring.Bean.ClassFactory;
import com.spring.Bean.Hander;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fantasy
 * @date 2018/9/28
 * @time 11:48
 */
public class HanderHelper {

    private final static Map<String, Hander> handerMap = new HashMap();

    static {
        Map beanContainer = BeanFactory.getBeanCantainer();
        List<Class> beanClasses = ClassFactory.getBeanClasses();
        beanClasses.forEach(c -> {
            if (c.isAnnotationPresent(Controller.class)) {
                Object instance = beanContainer.get(c.getName());
                setRequestRelation(instance);
            }
        });
    }

    // 映射路径与controller中method关联
    private static void setRequestRelation(Object instance) {
        Method[] methods = instance.getClass().getDeclaredMethods();
        Arrays
                .stream(methods)
                .filter(m -> m.isAnnotationPresent(RequestMapping.class))
                .forEach(m -> {
                    String requestPath = m.getAnnotation(RequestMapping.class).value();
                    Hander hander = new Hander();
                    hander.setRequestMethod(m);
                    hander.setInstance(instance);
                    handerMap.put(requestPath, hander);
                });
    }

    public static Hander getHander(String requestPath) {
        if (!handerMap.containsKey(requestPath)) {
            throw new RuntimeException("没有找到请求路径对应的请求处理");
        }
        return handerMap.get(requestPath);
    }

    public static Map<String, Hander> getHanderMap() {
        return handerMap;
    }
}
