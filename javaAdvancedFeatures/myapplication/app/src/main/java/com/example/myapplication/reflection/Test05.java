package com.example.myapplication.reflection;

public class Test05 {
    public static void main(String[] args) {
        A a = new A();
    }
}

class A{
    static{
        System.out.println("A class code block initiation.");
    }
    static int m = 100;

    public A() {
        System.out.println("A class 无参构造初始化");
    }
}
