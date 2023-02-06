package com.example.algorithmadvanced.problem;

public class Problem_6_1_02 {

  public static void main(String[] args) {
    /**
     * 判断一个数是不是stepNum
     * 定义何为step num?
     * 比如680,680+68+6=754,680的step num叫754
     * 给定一个正数num，判断它是不是某个数的step num
     */
    System.out.println("is step num:"+isStepNum(455));
  }

  public static boolean isStepNum(int stepNum) {
    int L = 0;
    int R = stepNum;
    int M = 0;
    int cur = 0;
    while (L <= R) {
      M = L + ((R - L) >> 1);
      cur = stepNum(M);
      if (cur == stepNum) {
        System.out.println("step num:"+M);
        return true;
      } else if (cur < stepNum) {
        L = M + 1;
      } else {
        R = M - 1;
      }
    }
    return false;
  }

  private static int stepNum(int num) {
    int sum = 0;
    while (num != 0) {
      sum += num;
      num /= 10;
    }
    return sum;
  }
}
