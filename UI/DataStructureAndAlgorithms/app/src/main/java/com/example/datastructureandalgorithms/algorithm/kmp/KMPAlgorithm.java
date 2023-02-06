package com.example.datastructureandalgorithms.algorithm.kmp;

import java.util.Arrays;

public class KMPAlgorithm {

    public static void main(String[] args) {

        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";

        int[] next = kmpNext(str2);
        int i = kmpSearch(str1, str2, next);
        System.out.println("next=" + Arrays.toString(next));
        System.out.println("i=" + i);

    }

    //kmp搜索算法

    /**
     * @param str1 源字符串
     * @param str2 子串
     * @param next 部分匹配表
     * @return -1表示没有找到，否则返回第一个匹配的位置
     */
    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {

            //需要处理str1.charAt(i) != str2.charAt(j)的情况
            //kmp算法核心
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }
        }
        return -1;
    }

    public static int[] kmpNext(String dest) {
        int[] next = new int[dest.length()];
        next[0] = 0;
        int len=0;
        for (int i = 1; i < dest.length(); i++) {

            while (len > 0 && dest.charAt(i) != dest.charAt(len)) {
                len = next[len - 1];
            }

            if (dest.charAt(i) == dest.charAt(len)) {//第一个最后一个相等
                len++;
            }
            next[i] = len;
        }
        return next;
    }
}

