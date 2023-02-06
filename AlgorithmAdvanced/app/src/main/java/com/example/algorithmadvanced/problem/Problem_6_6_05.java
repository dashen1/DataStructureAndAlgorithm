package com.example.algorithmadvanced.problem;

import java.util.Arrays;

public class Problem_6_6_05 {
  public static void main(String[] args) {
    /**
     * 出卷子方法数
     * 模型：
     * 从左往右的尝试模型
     */
  }

  public static int ways1(int[] arr, int m) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    return process(arr, 0, m);
  }

  private static int process(int[] arr, int index, int m) {
    if (index == arr.length) {
      for (int i = 1; i < index; i++) {
        if (arr[i - 1] > arr[i] + m) {
          return 0;
        }
      }
      return 1;
    }
    int ans = 0;
    for (int i = index; i < arr.length; i++) {
      swap(arr, index, i);
      ans += process(arr, index + 1, m);
      swap(arr, index, i);
    }
    return ans;
  }

  private static void swap(int[] arr, int index, int i) {
    int tmp = arr[i];
    arr[i] = arr[index];
    arr[index] = tmp;
  }

  //    int pre =1;
//    for (int i = 1; i < arr.length; i++) {
//      int cur = arr[i];
//      int p1=pre;
//
//      int num = arr[0...i-1] 有多少数是 >=arr[i]-m;
//
//      int cur = (num+1)*pre;
//      pre = cur;
//    }
//    return pre;

  public static int ways2(int[] arr, int m) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    for (int num : arr) {
      max = Math.max(max, num);
      min = Math.min(min, num);
    }
    IndexTree itree = new IndexTree(max - min + 2);
    Arrays.sort(arr);
    int a = 0;
    int b = 0;
    int all = 0;
    itree.add(arr[0] - min + 1, 1);
    for (int i = 1; i < arr.length; i++) {
      a = arr[i] - min + 1;
      b = i - (a - m - 1 >= 1 ? itree.sum(a - m - 1) : 0);
      all = all * (b + 1);
      itree.add(a, 1);
    }
    return all;
  }


  private static class IndexTree {

    public IndexTree(int i) {
      /**
       *
       */
    }

    public void add(int i, int i1) {

    }

    public int sum(int i) {
      return 0;
    }
  }
}
