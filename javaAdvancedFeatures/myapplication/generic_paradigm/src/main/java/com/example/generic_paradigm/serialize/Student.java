package com.example.generic_paradigm.serialize;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {

    private String name;
    private String sex;
    private Integer age;
    private List<Course> list;

    public Student() {
        System.out.println("Student empty Constructor");
    }

    public Student(String name, String sex, Integer age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.list = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void addCourse(Course course){
        this.list.add(course);
    }

    public List<Course> getCourses() {
        return list;
    }

}
