package com.example.generic_paradigm.generic;

import java.io.Serializable;
import java.util.List;

public class GenericMethod<T> {

    private T data;

    /**
     * 泛型的局限性 不能实例化类型变量
     * @param a
     * @param <T>
     * @return
     */
//    public GenericMethod() {
//        this.data = new T();
//    }

    // 泛型类里面的方法只影响类里面的普通方法

    // 定义了一个泛型方法 表明这是一个可以处理泛型类型的泛型方法
    // 泛型类和泛型方法的泛型可以不同，也可以相同，但只是名字可以相同，是全新类型的
    public <T> T genericMethod(T...a) {
        return a[0];
    }

    public <E> E genericMethod2(T...a) {
        return (E) a[0];
    }

    public static <T extends Comparable> T min(T a, T b) {
        if (a.compareTo(b) > 0)
            return a;
        else
            return b;
    }


    // 泛型的类型在new的时候才知道泛型的类型 对象创建的时候
    // 静态域或静态方法里面不能引用类型变量
   // private static T instance;


    // 类和接口混用的话，类必须只有一个，且必须放在前面
    public static <T extends Comparable & Serializable> T min2(T a, T b) {
        if (a.compareTo(b) > 0)
            return a;
        else
            return b;
    }

    public static void main(String[] args) {
        // 可以定义泛型数组 但是不能够new
        /**
         * 泛型类不能继承 Exception\Throwable
         * 通配符只适用于方法，不能在类中使用
         * 类型擦除
         * 使用extends 安全的访问数据，只能访问不能修改 限定了传入参数的上界
         */

        NormalGeneric<Apple> normalGeneric = new NormalGeneric();
        NormalGeneric<Fruit> normalGeneric2 = new NormalGeneric();
    }

    public void print(NormalGeneric<? super Apple> t){
        Fruit data = (Fruit)t.getData();
    }

    public void print2(NormalGeneric<? super Apple> t){
        Fruit data = (Fruit)t.getData();
    }

    // jdk 实现和编译期实现不太一样
    // jvm Signature 保存了泛型信息 多种数据类型执行相同的代码 不需要强制转换
//    public static void method(List<String> string) {
//
//    }

    public static void method(List<Integer> Integer) {

    }

}
