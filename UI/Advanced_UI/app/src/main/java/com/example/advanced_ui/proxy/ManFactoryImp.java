package com.example.advanced_ui.proxy;

public class ManFactoryImp implements IManToolsFactory{

    @Override
    public void saleTools(String size) {
        System.out.println("按需求定制size为："+size+"的model");
    }
}
