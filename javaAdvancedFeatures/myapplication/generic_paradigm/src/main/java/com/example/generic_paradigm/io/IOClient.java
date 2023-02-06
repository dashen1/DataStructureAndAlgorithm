package com.example.generic_paradigm.io;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class IOClient {

    public static void main(String[] args) throws IOException {
        new ObjectOutputStream(
        new BufferedOutputStream(
        new FileOutputStream(new File("a.txt"))
        ));

        File file = new File("a.txt");
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        Person person = new Person("张三", 18);
        oos.writeObject(person);
        oos.flush();
        oos.close();

        DataOutputStream dos = new DataOutputStream(bos);

        // 一个汉字3个字节 一个字符2个字节
        // OutputStreamWriter() 建立字节和字符之间联系的类
        // FileOutPutStream() 建立流和文件之间的桥梁

        RandomAccessFile raf = new RandomAccessFile("a.txt", "r");
        raf.writeUTF("汉字");
    }
}

class Person implements Serializable {

    private static final long serialVersionUID = 8673619679111514543L;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
