package com.example.algorithmadvanced.sorting;

import java.util.Arrays;
import java.util.TreeMap;

public class Comparator {

  public static void main(String[] args) {

    Student student1 = new Student("A", 2, 20);
    Student student2 = new Student("B", 3, 21);
    Student student3 = new Student("C", 4, 22);
    Student student4 = new Student("D", 5, 23);
    Student student5 = new Student("E", 6, 24);
    Student[] students = {student1, student2, student3};
    System.out.println("第一条打印");
    //Arrays.sort(students, new IdAscendingComparator());
    Arrays.sort(students, new AgeOrderComparator());
    printStudent(students);

    //TreeMap<Student,String> treeMap = new TreeMap<>(new IdDescendingComparator());
//    TreeMap<Student,String> treeMap = new TreeMap<>((a,b)->b.age-a.age);
    TreeMap<Student, String> treeMap = new TreeMap<>();
    treeMap.put(student3,"我是学生1");
    treeMap.put(student2,"我是学生2");
    treeMap.put(student5,"我是学生3");
    treeMap.put(student1,"我是学生4");
    treeMap.put(student4,"我是学生5");
    for (Student s:treeMap.keySet()){
      System.out.println(s);
    }
  }

  public static class Student implements Comparable<Student> {
    public String name;
    public int id;
    public int age;

    public Student(String name, int id, int age) {
      this.name = name;
      this.id = id;
      this.age = age;
    }

    @Override
    public String toString() {
      return "Student{" +
              "name='" + name + '\'' +
              ", id=" + id +
              ", age=" + age +
              '}';
    }

    @Override
    public int compareTo(Student o) {
      return this.age - o.age;
    }
  }

  public static void printStudent(Student[] students){
    int i=0;
    for (Student student:students){
      System.out.println("打印第"+i+++"个学生信息："+student);
    }
  }

  public static class IdAscendingComparator implements java.util.Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
      return o2.id - o1.id;
    }
  }

  public static class IdDescendingComparator implements java.util.Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
      return o1.id - o2.id;
    }
  }

  public static class AgeOrderComparator implements java.util.Comparator<Student> {

    @Override
    public int compare(Student o1, Student o2) {
      return o1.age - o2.age;
    }
  }
}
