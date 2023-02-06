package com.example.generic_paradigm.generic;

import java.util.ArrayList;
import java.util.List;

public class NonGeneric {

    public int addInt(int x, int y) {
        return x+y;
    }

    public float addInt(float x, float y) {
        return x+y;
    }



    public static void main(String[] args) {
        NonGeneric nonGeneric = new NonGeneric();
        System.out.println(nonGeneric.addInt(1,2));
        List list = new ArrayList();
        list.add("mark");
        list.add("ok");
        //list.add(100);
        //泛型： 参数化类型 （即方法里面的参数进行参数化，不确定其类型，在调用的时候再确定其参数类型）
        for (int i = 0; i < list.size(); i++) {

        }
    }


}
