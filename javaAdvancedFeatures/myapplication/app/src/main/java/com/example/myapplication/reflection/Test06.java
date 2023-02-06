package com.example.myapplication.reflection;

//测试类什么时候初始化
public class Test06 {
    static {
        System.out.println("main 被加载");
    }
    public static void main(String[] args) {
//        Son son = new Son();
        System.out.println(Son.b);
        System.out.println(Son.c);
    }
}

class Father{
    static int b = 2;
    static  {
        System.out.println("父类被加载");
    }
}

class Son extends Father{
    static {
        System.out.println("子类被加载");
        m = 300;
    }
    static int m = 100;
    static final int c = 100;
}
