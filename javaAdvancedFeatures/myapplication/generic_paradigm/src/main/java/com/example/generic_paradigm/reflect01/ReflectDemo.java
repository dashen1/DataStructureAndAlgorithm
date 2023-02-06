package com.example.generic_paradigm.reflect01;

import java.lang.reflect.Constructor;

public class ReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException {


        Class servantClass = Servant.class;
        Class class2 = servantClass.getClass();
        String clazz = "com.example.generic_paradigm.reflect01.Servant";
        Class aClass = Class.forName(clazz);
        Constructor[] constructors = aClass.getConstructors();

        // 第一种
        Class servantClass1 = Servant.class;

        //第二种
        Class<?> aClass1 = Class.forName("类路径");

    }
}
