package com.example.myapplication.reflection;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

// 反射操作注解
public class Test12 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException {
        Class c1 = Class.forName("com.example.myapplication.reflection.Student2");
        //通过反射获得注解
        Annotation[] annotations = c1.getAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation);
        }

        Table table = (Table) c1.getAnnotation(Table.class);
        String value = table.value();
        System.out.println(value);

        // 获得类指定的注解
        Field name = c1.getDeclaredField("name");
        FieldStudent annotation = name.getAnnotation(FieldStudent.class);
        System.out.println(annotation.columnName());
        System.out.println(annotation.type());
        System.out.println(annotation.length());

    }
}

@Table("db_student")
class Student2{
    @FieldStudent(columnName = "db_id", type = "int", length = 10)
    private int id;
    @FieldStudent(columnName = "db_age", type = "int", length = 10)
    private int age;
    @FieldStudent(columnName = "db_name", type = "varchar", length = 3)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student2{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table{
    String value();
}


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface FieldStudent{
    String columnName();
    String type();
    int length();
}