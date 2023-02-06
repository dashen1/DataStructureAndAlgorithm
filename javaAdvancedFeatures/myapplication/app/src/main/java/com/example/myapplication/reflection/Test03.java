package com.example.myapplication.reflection;

public class Test03 {
    public static void main(String[] args) throws ClassNotFoundException {
        Person person = new Student();
        System.out.println("I am: "+person.name);

        Class c1 = person.getClass();
        System.out.println(c1.hashCode());

        Class c2 = Class.forName("com.example.myapplication.reflection.Student");
        System.out.println(c2.hashCode());

        Class c3 = Student.class;
        System.out.println(c3.hashCode());

        Class c4 = Integer.TYPE;
        System.out.println(c4);

        Class c5 = c1.getSuperclass();
        System.out.println(c5);
    }
}

class Person{
    public String name;

    public int age;
}

class Student extends Person{
    public Student() {
        this.name = "学生";
    }
}

class Teacher extends Person{
    public Teacher() {
        this.name = "老师";
    }
}
