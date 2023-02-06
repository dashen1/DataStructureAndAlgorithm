package com.example.algorithmadvanced.problem02;

import java.util.Arrays;

public class Problem_6_10_01 {
  public static void main(String[] args) {
    /**
     * 比如{5,3,1,4}
     * 全部数字对是：（5,3）、（5,1）、（5,4）、（3,1）、（3,4）、（1,4）
     * 数字对的差值绝对值：2、4、1、2、1、3
     * 差值绝对值排序后：1、1、2、2、3、4
     * 给定一个数组arr，和一个正数K
     * 返回arr中所有数字对差值的绝对值，第K小的是多少
     * arr = {5,3,1,4}, k = 4
     * 返回 2
     */
  }

  //时间复杂度为 O(N*logN) 才行
  //二分+双指针不回退
  public static int kthABS2(int[] arr, int k) {
    int n = arr.length;
    //数组中只有一个数或者第K小小于1或者第k小大于全部数字对
    if (n < 2 || k < 1 || k > ((n * (n - 1)) >> 1)) {
      return -1;
    }
    Arrays.sort(arr);
    int left = 0;
    //数字对的差值一定在 |最大值-最小值| 的范围上
    //只要找到对应最靠近 K就ok
    int right = arr[n - 1] - arr[0];
    int mid = 0;
    //初值为-1 梦幻时刻
    //假设原数组为[3,3,3,3,3,3,3,3..] 有1000个
    //那么差值数组为[0,0,0,0,0,0] 差值为0的为1000个，那么rightest会一直不变
    //
    int rightest = -1;
    while (left <= right) {
      mid = (left + right) / 2;
      if (valid(arr, mid, k)) {
        rightest = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return rightest + 1;
  }

  //假设arr数组中的所有数字对，差值绝对值<=limit的个数为x
  //如果 x < K,达标，返回true
  //如果 x >= K,不达标，返回false
  //{1,3,4,5}
  //limit -> 差值
  private static boolean valid(int[] arr, int limit, int k) {
    int x = 0;
    for (int l = 0, r = 1; l < arr.length; r = Math.max(r, ++l)) {
      while (r < arr.length && arr[r] - arr[l] <= limit) {
        r++;
      }
      x += r - l - 1;
    }
    return x < k;
  }

  //暴力解，生成所有数字对差
  public static int kthABS1(int[] arr, int k) {
    int n = arr.length;
    int m = ((n - 1) * n) >> 1;
    if (m == 0 || k < 1 || k > m) {
      return -1;
    }
    int[] abs = new int[m];
    int size = 0;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        abs[size++] = Math.abs(arr[i] - arr[j]);
      }
    }
    Arrays.sort(abs);
    return abs[k - 1];
  }
}
