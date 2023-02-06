package com.example.advanced_ui.proxy;

import com.example.advanced_ui.proxy.dynamic_proxy.MarkCompany;
import com.example.advanced_ui.proxy.static_proxy.Mark;
import com.example.advanced_ui.proxy.static_proxy.WomanProxy;

import java.lang.reflect.Method;

public class ClientProxy {
    public static void main(String[] args) {

        System.out.println("静态代理：");
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        IManToolsFactory factory = new ManFactoryImp();
        Mark mark = new Mark(factory);
        mark.saleTools("D");

        WomanFactoryImp womanFactoryImp = new WomanFactoryImp();
        WomanProxy womanProxy = new WomanProxy(womanFactoryImp);
        womanProxy.saleTools(12.5f);

        System.out.println("========================");
        System.out.println("动态代理：");

        ManFactoryImp factoryImp = new ManFactoryImp();
        MarkCompany markCompany = new MarkCompany();
        markCompany.setFactory(factoryImp);

        IManToolsFactory employee1 = (IManToolsFactory) markCompany.getProxyInstance();
        employee1.saleTools("C");
        System.out.println("动态代理的实际类："+employee1.getClass().getName());
        System.out.println("woman dynamic");
        WomanFactoryImp womanFactoryImp1 = new WomanFactoryImp();
        markCompany.setFactory(womanFactoryImp1);
        IWomanToolsFactory womanToolsFactory = (IWomanToolsFactory) markCompany.getProxyInstance();
        womanToolsFactory.saleTools(55.4f);

        System.out.println("动态代理类的所有方法：");
        Method[] methods = womanToolsFactory.getClass().getMethods();
        for (Method method:methods){
            System.out.println(method.getName());
        }


    }
}
