package com.example.datastructureandalgorithms.dynamicplan;

public class Knapsack {

    public static void main(String[] args) {

    }

    public static int maxValue(int[] w, int[] v, int bag) {
        if (w == null || v == null || w.length != v.length || w.length == 0) {
            return 0;
        }
        //尝试
        return process(w, v, 0, bag);
    }

    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        /**
         *
         */
        if (index == w.length) {
            return 0;
        }
        //还有货物
        //bag还有空间
        //不要当前的货
        int p1 = process(w, v, index + 1, rest);
        //要当前货
        int p2 = 0;
        int next = process(w, v, index, rest - w[index]);
        if (next != -1) {
            p2 = v[index] + next;
        }
        return Math.max(p1, p2);
    }

//    public static int dp(int[] w,int[] v,int ){
//
//    }
}
