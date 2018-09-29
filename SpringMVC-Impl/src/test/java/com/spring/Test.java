package com.spring;

import com.spring.Bean.BeanFactory;
import com.spring.Bean.ClassFactory;
import com.spring.Bean.Hander;
import com.spring.Helper.FieldHelper;
import com.spring.Helper.HanderHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class Test {
    public static void main( String[] args ) {

        // 加载的所有beanClass
        List<Class> beanClasses = ClassFactory.getBeanClasses();
        System.out.println(beanClasses);

        // 加载的所有beanContainer
        Map beanContainer = BeanFactory.getBeanCantainer();
        System.out.println(beanContainer);

        // 初始化bean和autowired注入的field
        FieldHelper.initField();

        // 加载的所有requestMapping和controller中method的对应关系
        Map<String, Hander> handerMap = HanderHelper.getHanderMap();
        System.out.println(handerMap);

        String requestPath = "/getUser";
        if (handerMap.containsKey(requestPath)) {
            Hander hander = handerMap.get(requestPath);
            try {
                hander.getRequestMethod().invoke(hander.getInstance(), null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
}
