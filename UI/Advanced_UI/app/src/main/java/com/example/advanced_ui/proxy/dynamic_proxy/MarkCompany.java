package com.example.advanced_ui.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MarkCompany implements InvocationHandler {
    private Object factory;

    public Object getFactory() {
        return factory;
    }

    public void setFactory(Object factory) {
        this.factory = factory;
    }

    public Object getProxyInstance() {
        // ClassLoader loader,
        // Class<?>[] interfaces,
        // InvocationHandler h
        return Proxy.newProxyInstance(factory.getClass().getClassLoader(),
                factory.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // method就是我们当前调用的方法
        Object result = method.invoke(factory, args);
        return result;
    }
}
