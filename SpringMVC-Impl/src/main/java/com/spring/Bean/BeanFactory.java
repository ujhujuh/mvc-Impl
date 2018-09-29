package com.spring.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fantasy
 * @date 2018/9/28
 * @time 10:05
 */
public class BeanFactory {

    private final static Map beanContainer = new HashMap<>();

    static {
        List<Class> beanClasses = ClassFactory.getBeanClasses();
        beanClasses.forEach(c -> {
            try {
                beanContainer.put(c.getName(), c.newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }

    public static Map getBeanCantainer() {
        return beanContainer;
    }
}
