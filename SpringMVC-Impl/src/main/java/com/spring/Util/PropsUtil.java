package com.spring.Util;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author fantasy
 * @date 2018/9/28
 * @time 19:18
 */
public class PropsUtil {

    public static Properties loadProperty(String fileName) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new RuntimeException("没有找到" + fileName);
        }
        Properties property = new Properties();
        try {
            property.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return property;
    }

}
