package com.example.advanced_ui.proxy.static_proxy;

import com.example.advanced_ui.proxy.IManToolsFactory;
import com.example.advanced_ui.proxy.IWomanToolsFactory;

public class WomanProxy implements IWomanToolsFactory {

    private IWomanToolsFactory factory;

    public WomanProxy(IWomanToolsFactory factory) {
        this.factory = factory;
    }

    @Override
    public void saleTools(float length) {
        factory.saleTools(length);
    }
}
