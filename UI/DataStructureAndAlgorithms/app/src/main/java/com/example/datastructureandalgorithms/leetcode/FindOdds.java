package com.example.datastructureandalgorithms.leetcode;

public class FindOdds {

    public static void main(String[] args) {

        int[] arr = {3, 1, 4, 4, 5, 5, 5, 5};
        findOdds(arr);

    }

    public static void findOdds(int[] arr) {
        int one = 0;// one = a^b
        for (int i = 0; i < arr.length; i++) {
            one ^= arr[i];
        }
        // one !=0 说明 a 和 b必定有一位不同
        int rightOne = one & (~one + 1);
        int onlyOne = 0;
        for (int j = 0; j < arr.length; j++) {
            if ((arr[j] & rightOne) == 0) {// (arr[j] & rightOne) == 1是错误的
                onlyOne ^= arr[j];
            }
        }
        System.out.println("onlyOne=" + " " + onlyOne);
        System.out.println("onlyOne=" + " " + (onlyOne ^ one));
    }

    public static void queue8(int[][] arr, int x, int y) {
        if (x < 0 || x > arr.length || y < 0 || y > arr.length) {
            return;
        }

        boolean isNext = true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if(arr[i][j] == 1){
                    if(x==i||y==j||Math.abs(x-i) ==Math.abs(y-j)){
                        isNext=false;
                        break;
                    }
                }
            }
        }
        if (isNext){
            arr[x][y]=1;
        }
    }
}
