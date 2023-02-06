package com.example.datastructureandalgorithms.theleftgod.queue8;

public class NQueue {

    public static int num1(int n) {
        if (n < 1) {
            return 0;
        }
        int[] record = new int[n];
        return process1(0, record, n);
    }

    private static int process1(int i, int[] record, int n) {
        //i是下标 i == n 说明下标越界了，也就说明 n-1 前的所有下标都正确了，所以是一种解法
        if (i == n) {
            return 1;
        }
        int res = 0;
        for (int j = 0; j < n; j++) {
            if (isValid(record, i, j)) {
                record[i] = j;
                res += process1(i + 1, record, n);
            }
        }
        return res;
    }

    private static boolean isValid(int[] record, int i, int j) {
        for (int k = 0; k < i; k++) {
            if (j == record[k] || Math.abs(i - k) == Math.abs(j - record[k])) {
                return false;
            }
        }
        return true;
    }

    //
    public static int num2(int n) {
        if (n < 1 || n > 32) {
            return 0;
        }

        int limit = n == 32 ? -1 : (1 << n) - 1;
        return process2(limit, 0, 0, 0);
    }

    private static int process2(int limit, int colLimit, int leftDiaLimit, int rightDiaLimit) {

        /**
         * 假如是 8皇后问题
         * 1、因为int是32位，取最低8位为1，就是开始列能够填的位置
         * 2、
         */
        if (colLimit == limit) {
            return 1;
        }
        int pos = 0;
        int mostRightOne = 0;
        pos = limit & (~(colLimit | leftDiaLimit | rightDiaLimit));// 所有候选皇后的位置 1为可选
        int res = 0;
        while (pos != 0) {
            mostRightOne = pos & (~pos + 1);
            pos = pos - mostRightOne;
            res += process2(limit, colLimit | mostRightOne,
                    (leftDiaLimit | mostRightOne) << 1,
                    (rightDiaLimit | mostRightOne) >>> 1);//无符号右移
        }
        return res;
    }
}
