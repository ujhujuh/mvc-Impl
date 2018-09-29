package com.spring;

import com.spring.Bean.BeanFactory;
import com.spring.Bean.ClassFactory;
import com.spring.Bean.Hander;
import com.spring.Helper.FieldHelper;
import com.spring.Helper.HanderHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * @author fantasy
 * @date 2018/9/29
 * @time 10:51
 */
public class TestBean {

    private static final Logger logger = LoggerFactory.getLogger(TestBean.class);

    public static void main(String[] args) {
        // 加载的所有beanClass
        List<Class> beanClasses = ClassFactory.getBeanClasses();
        logger.info(beanClasses.toString());

        // 加载的所有beanContainer
        Map beanContainer = BeanFactory.getBeanCantainer();
        logger.info(beanContainer.toString());

        // 初始化bean和autowired注入的field
        FieldHelper.initField();

        // 加载的所有requestMapping和controller中method的对应关系
        Map<String, Hander> handerMap = HanderHelper.getHanderMap();
        logger.info(handerMap.toString());

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
