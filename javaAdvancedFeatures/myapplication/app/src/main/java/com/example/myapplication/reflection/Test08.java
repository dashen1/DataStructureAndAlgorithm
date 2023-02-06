package com.example.myapplication.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

//获得类的信息
public class Test08 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class c1 = Class.forName("com.example.myapplication.reflection.User");

        User user = new User();
        c1 = user.getClass();
        System.out.println(c1.getName());
        System.out.println(c1.getSimpleName());
        //Field[] fields = c1.getFields(); 找到public属性
        Field[] fields = c1.getDeclaredFields();//找到全部属性
        for (Field field : fields) {
            System.out.println(field);
        }

        Constructor[] constructors = c1.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.println("构造器："+constructor);
        }
    }
}
