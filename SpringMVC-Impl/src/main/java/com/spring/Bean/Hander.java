package com.spring.Bean;

import java.lang.reflect.Method;

/**
 * @author fantasy
 * @date 2018/9/28
 * @time 11:57
 */
public class Hander {

    private Object instance;

    private Method requestMethod;

    public Object getInstance() {
        return instance;
    }

    public void setInstance(Object instance) {
        this.instance = instance;
    }

    public Method getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(Method requestMethod) {
        this.requestMethod = requestMethod;
    }

    @Override
    public String toString() {
        return "Hander{" +
                "instance=" + instance +
                ", requestMethod=" + requestMethod +
                '}';
    }
}
