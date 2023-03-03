package com.example.algorithmadvanced.demo;

public class Random {

    public static void main(String[] args) {

        for (int i = 0; i < 1000000; i++) {
            int question = getRandomInRange(49);
            if (question>49){
                System.out.println("question:"+question);
            }
        }
    }

    public static int getRandomInRange(int range){
        return (int) (1+Math.random()*(range));
    }
}
