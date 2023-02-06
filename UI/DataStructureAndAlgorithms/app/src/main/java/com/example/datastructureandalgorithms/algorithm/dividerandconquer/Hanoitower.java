package com.example.datastructureandalgorithms.algorithm.dividerandconquer;

import java.util.ArrayList;

public class Hanoitower {
    public static void main(String[] args) {


    }

    /**
     * 汉诺塔问题可以使用分治思想
     * {
     *     1.将问题分解
     *     2.将分解后的问题解决
     *     3.将解决后的问题合并
     * }
     * 有 A，B，C
     * @param num
     * @param a
     * @param b
     * @param c
     */
    public static void hanoiTower(int num, char a, char b, char c) {
        //如果只有一个盘
        if (num == 1) {
            System.out.println("第1个盘从" + a + "->" + c);
        } else {
            //1.先把最上面的所有盘A-B,移动过程会使用到c
            hanoiTower(num - 1, a, c, b);
            //2.把B塔所有盘从A-C
            System.out.println("第" + num + "个盘从" + a + "->" + c);
            //3.把B塔的所有盘从B-C，移动过程使用到a塔
            hanoiTower(num - 1, b, a, c);
        }
    }
}
