package com.spring;

import com.spring.Bean.BeanFactory;
import com.spring.Bean.ClassFactory;
import com.spring.Bean.Hander;
import com.spring.Helper.FieldHelper;
import com.spring.Helper.HanderHelper;
import com.spring.Util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author fantasy
 * @date 2018/9/28
 * @time 10:00
 */
public class DispacherServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DispacherServlet.class);

    @Override
    public void init() throws ServletException {
        logger.info("加载的所有beanClass");
        List<Class> beanClasses = ClassFactory.getBeanClasses();
        logger.info(beanClasses.toString());

        logger.info("加载的所有beanContainer");
        Map beanContainer = BeanFactory.getBeanCantainer();
        logger.info(beanContainer.toString());

        logger.info("初始化bean和autowired注入的field");
        FieldHelper.initField();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI().substring(req.getContextPath().length());
        logger.info("请求路径" + requestPath);

        logger.info("获取处理请求的handler");
        Hander hander = HanderHelper.getHander(requestPath);
        logger.info(hander.toString());

        logger.info("正在调用方法处理对应的请求......");

        Object result = null;
        Method method = hander.getRequestMethod();
        method.setAccessible(true);
        try {
            result = method.invoke(hander.getInstance(), null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 输出
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        String str = JsonUtil.toJSON(result);
        writer.print(str);
        writer.flush();
        writer.close();

    }
}
