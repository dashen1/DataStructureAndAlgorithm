package com.example.generic_paradigm.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.Arrays;

public class TestProtoBuf {

    public static byte[] serialize() {

        StudentSerializable.Course.Builder courseBuilder = StudentSerializable.Course.newBuilder().setName("语文").setScore(66.5f);

        StudentSerializable.Student.Builder builder = StudentSerializable.Student.newBuilder().setName("老王").setAge(17).setSex("男").addCourses(courseBuilder);

        StudentSerializable.Student student = builder.build();
        return student.toByteArray();
    }

    public static StudentSerializable.Student deserialize(byte[] bs) {
        try {
            return StudentSerializable.Student.parseFrom(bs);
        } catch (InvalidProtocolBufferException e) {

        }
        return null;

    }

    public static void main(String[] args) {
        //TODO:
        //序列化
        byte[] bs = serialize();
        System.out.println(Arrays.toString(bs));

        //反序列化
        StudentSerializable.Student student = deserialize(bs);
        System.out.println(student);
    }
}
