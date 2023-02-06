package com.example.myapplication.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Analysis
public class Test10 {

    public static void test01(){
        User user = new User();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            user.getName();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("普通方法执行："+(endTime-startTime) + "ms");
    }

//    public static void test02() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        User user = new User();
//        Class c1 = user.getClass();
//        Method getName = c1.getDeclaredMethod("getName", null);
//        getName.setAccessible(true);
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 1000000000; i++) {
//            getName.invoke(user,null);
//        }
//
//        long endTime = System.currentTimeMillis();
//        System.out.println("普通方法执行 :"+(endTime-startTime) +"ms");
//
//    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        test01();
        //test02();
    }

}
