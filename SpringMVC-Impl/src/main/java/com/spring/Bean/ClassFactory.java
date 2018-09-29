package com.spring.Bean;

import com.spring.Annotation.Controller;
import com.spring.Annotation.Service;
import com.spring.Util.Constants;
import com.spring.Util.PropsUtil;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author fantasy
 * @date 2018/9/28
 * @time 10:10
 */
public class ClassFactory {

    private final static List<Class> allClasses;
    private final static List<Class> beanClasses;

    // 根据传入的basePackage加载所有该package下的class
    static {
        Properties properties = PropsUtil.loadProperty("config.properties");
        String basePackage = properties.getProperty(Constants.APP_BASE_PACKAGE);
        allClasses = loadAllClasses(basePackage);
        beanClasses = allClasses
                .stream()
                .filter(c -> c.isAnnotationPresent(Controller.class) || c.isAnnotationPresent(Service.class))
                .collect(Collectors.toList());
    }

    public static List<Class> getBeanClasses() {
        return beanClasses;
    }

    private static List<Class> loadAllClasses(String basePackage) {
        List<Class> classes = new ArrayList<>();
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader()
                    .getResources(basePackage.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    // getResource("").toURI().getPath()， 将url转uri，解决空格和中文问题
                    String packagePath = url.toURI().getPath();
                    addClass(classes, packagePath, basePackage);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return classes;
    }

    private static void addClass(List<Class> classes, String packagePath, String pacakgeName) throws ClassNotFoundException {
        File[] files = new File(packagePath).listFiles(f -> (f.isFile() && f.getName().endsWith(".class")) || f.isDirectory());

        if (files == null) {
            throw new RuntimeException("找不到basePackages");
        }
        for (File file : files) {
            if (file.isFile()) {
                String className = pacakgeName + "." + file.getName().substring(0, file.getName().lastIndexOf("."));
                classes.add(Class.forName(className));
            } else {
                String path = packagePath + "/" + file.getName();
                String name = pacakgeName + "." + file.getName();
                addClass(classes, path, name);
            }
        }
    }

}
