package com.example.generic_paradigm.json;

import com.google.gson.Gson;

public class JsonClient {

    /**
     * Json（JavaScript Object Notation） 数据标记，存储，传输 轻量级、独立于语言，平台
     * 用处：网络请求数据
     *
     * json 解析：jackson,fastjson,gson
     *
     */

    static class GsonBean{
        private int i;
        private String str;
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
        System.out.println(gson.toJson(1));
        System.out.println(gson.toJson("hello"));
        int[] values = {1,2,3};
        System.out.println(gson.toJson(values));


    }

}
