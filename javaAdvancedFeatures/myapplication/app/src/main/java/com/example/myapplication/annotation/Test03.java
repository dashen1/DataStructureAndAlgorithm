package com.example.myapplication.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Test03 {

    @MyAnnotation2(age = 18)
    public void test(){}

    @MyAnnotation3("qingbei")
    public void test2(){

    }
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation2{
    //注解的参数：参数类型 + 参数名（）;
    String name() default "";
    int age() default 0;
    int id() default -1;
    String[] schools() default {"qinghua", "beida"};
}
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation3{
    String value();
}
