package com.example.advanced_ui.proxy.static_proxy;

import com.example.advanced_ui.proxy.IManToolsFactory;

public class Mark implements IManToolsFactory {

    private IManToolsFactory factory;

    public Mark(IManToolsFactory factory) {
        this.factory = factory;
    }

    @Override
    public void saleTools(String size) {
        factory.saleTools(size);
    }
}
