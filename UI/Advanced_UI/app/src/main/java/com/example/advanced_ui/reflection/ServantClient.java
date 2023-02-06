package com.example.advanced_ui.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServantClient {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

        /**
         * 类$对象
         * 首先看有没有把这个类加载到了内存中，如果没有就先加载到内存，再new
         */
        Servant servant = new Servant();//如果不重载构造函数，就会默认调用无参的构造方法，如果重载了构造方法，就不会再调用无参的构造方法

        Class class1 = Servant.class;
        Class class2 = servant.getClass();
        Class class3 = Class.forName("com.example.advanced_ui.reflection.Servant");

        /**
         * 获得对象的方式：3种
         * 1.通过new
         * 2.先获取从class，在通过newInstance（）方法获得
         * 3.跳跃构造器的newInstance
         */

        Servant servant1 = (Servant) class1.newInstance();// 和直接new没有区别
        servant1.service("ok");

        String classStr = "com.example.advanced_ui.reflection.Person";

        Class<Person> classPerson = (Class<Person>) Class.forName(classStr);

        System.out.println("获取全部Constructor对象");
        Constructor<Person>[] constructors = (Constructor<Person>[]) classPerson.getConstructors();
        for (Constructor<Person> constructor : constructors) {
            System.out.println(constructor);
        }

        System.out.println("获取一个Constructor对象，需要参数列表：");
        Constructor<Person> cons2 = classPerson.getConstructor(String.class, int.class);
        System.out.println(cons2);
        Person person = cons2.newInstance("hello", 19);//因为构造器是带参数的，所以newInstance的时候也要带参数
        System.out.println("通过构造器获得对象："+person);
        System.out.println("=============================================");

        // 不能获取对应类里面的私有方法，可以获取父类的非私有方法
        System.out.println("获取对象的方法：");
        Method[] methods = classPerson.getMethods();
        for (Method method : methods) {
            System.out.println(" "+method.getName()+"()");
        }

        System.out.println("获取所有方法，包括私有方法，且只能获取当前类的方法");
        Method[] declaredMethods = classPerson.getDeclaredMethods();
        for (Method method:declaredMethods) {
            System.out.println(" "+method.getName()+" ()");
        }
        System.out.println("获取指定的方法，需要参数名称和参数列表，无参则不需要：");
        Method setName = classPerson.getDeclaredMethod("setName", String.class);
        Method setAge = classPerson.getDeclaredMethod("setAge", int.class);
        System.out.println(setName);
        System.out.println(" "+setName.getName()+"()");
        System.out.println(setAge);
        System.out.println(" "+setAge.getName()+"()");
        System.out.println("=================================");
        System.out.println("执行方法，第一个参数表示执行对象的哪个方法，剩下的参数是执行方法时需要传入的参数:");
        Object obj = classPerson.newInstance();
        setAge.invoke(obj, 19);

        Method privateMethod = classPerson.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);// 调用私有方法要设置
        privateMethod.invoke(obj);

        System.out.println("获取指定字段：");
        Field nameField = classPerson.getDeclaredField("name");
        Field ageField = classPerson.getDeclaredField("age");
        System.out.println("name: "+nameField.getName());
        System.out.println("age: "+ageField.getName());

        Person person1 = new Person("ABC", 12);
        System.out.println("获取指定字段的值：");
        Object val = nameField.get(person1);
        System.out.println("值为："+val);

        ageField.setAccessible(true);
        Object valAge = ageField.get(person1);
        System.out.println("年龄："+valAge);
    }
}
