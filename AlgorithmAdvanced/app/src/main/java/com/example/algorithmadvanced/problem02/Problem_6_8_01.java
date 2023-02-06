package com.example.algorithmadvanced.problem02;

import java.util.HashMap;

public class Problem_6_8_01 {

  /**
   * 前缀01串切成等比例的最大部分数
   * 把一个01字符串切成多个部分，要求每一部分的0和1的比例一样，同时要求尽可能多的划分
   * 比如：01010101
   * 01 01 01 01 这是一种切法，0和1的比例为1:1
   * 0101 0101 也是一种切法，0和1的比例为1:1
   * 两种切法都符合要求，但是那么尽可能多的划分为第一种切法，部分数为4
   */

  public static void main(String[] args) {
    int[] arr = {0, 1, 0, 1, 0, 1, 1, 0, 0};
    int[] ans = split(arr);
    for (int i = 0; i < ans.length; i++) {
      System.out.print(ans[i] + " ");
    }
  }

  //假设在0~99上0和1的比例是 3:7，0~89上0和1的比例也是3:7，那么90~99上0和1的比例也必定是3:7,
  //所以此时0~99上能分为两个部分数
  public static int[] split(int[] arr) {
    HashMap<Integer, HashMap<Integer, Integer>> pre = new HashMap<>();

    int n = arr.length;
    int[] ans = new int[n];
    int zero = 0;
    int one = 0;
    for (int i = 0; i < n; i++) {
      if (arr[i] == 0) {
        zero++;
      } else {
        one++;
      }
      //arr[]上全是0或者全是1
      if (zero == 0 || one == 0) {
        ans[i] = i + 1;
      } else {
        int gcd = gcd(zero, one);
        int a = zero / gcd;
        int b = one / gcd;
        //a / b 比例，之前有多少前缀拥有
        // {1,{3,6}}
        // 分子为1，分母为3，的前缀部分数有6个
        if (!pre.containsKey(a)) {
          pre.put(a, new HashMap<>());
        }
        if (!pre.get(a).containsKey(b)) {
          pre.get(a).put(b, 1);
        } else {
          pre.get(a).put(b, pre.get(a).get(b) + 1);
        }
        ans[i] = pre.get(a).get(b);
      }
    }
    return ans;
  }

  /**
   * 求两数的最小公约数
   * @param m nums of zero
   * @param n nums of one
   * @return
   */
  public static int gcd(int m, int n) {
    return n == 0 ? m : gcd(n, m % n);
  }
}
