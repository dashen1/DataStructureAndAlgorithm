package com.example.advanced_ui.proxy;

public class WomanFactoryImp implements IWomanToolsFactory{

    @Override
    public void saleTools(float length) {
        System.out.println("给woman定制了length为："+length+"的物品");
    }
}
