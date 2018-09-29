package com.spring.Util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author fantasy
 * @date 2018/9/29
 * @time 11:39
 */
public class JsonUtil {

    /**
     * 将对象转化为json
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJSON(T obj) {
        String json = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            json = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 将json转化为对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJSON(String json, Class<T> clazz) {
        T pojo = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            pojo = mapper.readValue(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pojo;
    }
}
