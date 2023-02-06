package com.example.generic_paradigm.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 可以重写writeObject/readObject 然后自己实现
 *
 * transient 修饰的字段可以不序列化，但是有时候通过重写可以重写writeObject方法可以将该字段进行序列化
 *
 *
 */

public class Course implements Serializable {


    private static final long serialVersionUID = 4156232226071151522L;
    private String name;
    private float score;

    public Course() {
        System.out.println("Course empty Constructor");
    }

    public Course(String name, float score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Course course = new Course("数学", 98.3f);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        oos.writeObject(course);
        byte[] bs = out.toByteArray();
        oos.close();
        System.out.println("=========反序列化========");
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bs));
        Course course1 = (Course) ois.readObject();
        System.out.println("course:"+course1);

        /**
         * Serializable的坑
         * 1、多引用的问题
         * 解决： oos2.writeUnshared(course2);
         * 2、子类实现了序列化，但是父类没有实现序列化
         * 解决：父类也实现Serializable
         * 3、序列化的时候有三个字段，但是反序列化的时候有四个字段，反序列化后会有一个字段是没有值的，默认值
         *
         * 4、单利模式的序列化问题/反射问题
         * 避免序列化的时候实例多个对象 添加flag标志
         *
         * Serializable 缺点
         * 1、因为它大量使用反射，会创建大量的临时变量，产生大量的内存碎片，效率低
         */

        Course course2 = new Course("英语", 77.4f);
        ByteArrayOutputStream bos2 = new ByteArrayOutputStream();
        ObjectOutputStream oos2 = new ObjectOutputStream(bos2);

        oos2.writeObject(course2);
        course2.setScore(23.4f);
        oos2.reset();
        oos2.writeUnshared(course2);
        oos2.writeObject(course2);
        byte[] bs2 = bos2.toByteArray();
        oos.close();

        ObjectInputStream ois2 = new ObjectInputStream(new ByteArrayInputStream(bs2));
        Course course3  = (Course) ois2.readObject();
        Course course4  = (Course) ois2.readObject();
        System.out.println("course3.score:"+course3.score);
        System.out.println("course3.score:"+course4.score);

    }

    // 静态内部类实现单例
    private static class SingleHandler {
        private static Single sinle = new Single();
    }

    public static Single getInstance() {
        return SingleHandler.sinle;
    }
}

class Single {
    // 饿汉式单例


    public Single() {
    }

    private static Single single = new Single();

    public static Single getInstance() {
        return single;
    }
}

class Single2 {
    public Single2() {
    }

    private static Single2 single2 = null;

    private static Single2 getInstance() {
        if (single2 == null) {
            synchronized (Single.class) {
                if (single2 == null) {
                    single2 = new Single2();
                }
            }
        }
        return single2;
    }
}


class EnumSingle{
    public EnumSingle() {
    }

    public enum SingleEnum{;
        private Single single;
        SingleEnum() {
            single = new Single();
        }

        public Single getSingle(){
            return single;
        }
    }
}