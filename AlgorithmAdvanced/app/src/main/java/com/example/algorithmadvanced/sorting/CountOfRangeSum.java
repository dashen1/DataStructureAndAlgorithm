package com.example.algorithmadvanced.sorting;

import java.util.Arrays;

public class CountOfRangeSum {

  public static void main(String[] args) {

    /**
     * 题目描述：
     * 给定一个数组arr,两个整数lower和upper，
     * 返回arr中有多少个子数组的累加和在[lower,upper]范围上
     */

    long[] arr = {-3, 1, 2, 4, 0, -1, 5};
    int count = comparator(arr, 1, 4);
    System.out.println("符合要求数量："+count);
    long[] prefixArr = getPrefixSumOfArray(arr);
    System.out.println("前缀数组和：" + Arrays.toString(prefixArr));
  }

  public static int comparator(long[] arr, int lower, int upper) {
    //{-3,1,2,4,0,-1,5}
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      int sum = 0;
      for (int j = arr.length - 1 - i; j < arr.length; j++) {
        sum += arr[j];
      }
      if (sum >= lower && sum <= upper) {
        count++;
      }
    }
    return count;
  }

  private static int[] generateRandomArray(int maxSize, int maxValue) {
    int arrSize = (int) (Math.random() * maxSize);
    int[] arr = new int[arrSize];
    for (int i = 0; i < arrSize; i++) {
      arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * maxValue);
    }
    return arr;
  }

  public static int[] copyArray(int[] arr) {
    int[] newArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      newArr[i] = arr[i];
    }
    return newArr;
  }

  public static long[] getPrefixSumOfArray(long[] arr) {
    long[] newArr = new long[arr.length];
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j <= i; j++) {
        newArr[i] += arr[j];
      }
    }
    return newArr;
  }

  public static int countOfRangeSum1(long[] sum, int lower, int upper) {
    if (sum == null || sum.length == 0) {
      return 0;
    }
    return process(sum, 0, sum.length - 1, lower, upper);
  }

  //sum -> 前缀和数组
  public static int process(long[] sum, int L, int R, int lower, int upper) {
    if (L == R) {
      return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
    }
    int M = L + ((R - L) >> 1);
    return process(sum, L, M, lower, upper) + process(sum, M + 1, R, lower, upper) + merge(sum, L, M, R, lower, upper);
  }

  public static int merge(long[] arr, int L, int M, int R, int lower, int upper) {
    int ans = 0;
    int windowL = L;
    int windowR = L;
    for (int i = M + 1; i <= R; i++) {
      long min = arr[i] - upper;
      long max = arr[i] - lower;
      while (windowR <= M && arr[windowR] <= max) {
        windowR++;
      }
      while (windowL <= M && arr[windowL] < min) {
        windowL++;
      }
      //ans+=Math.max(0,windowR-windowL);也ok
      ans += windowR - windowL;
    }

    long[] help = new long[R - L + 1];
    int i = 0;
    int p1 = L;
    int p2 = M + 1;
    while (p1 <= M && p2 <= R) {
      help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }

    while (p1 <= M) {
      help[i++] = arr[p1++];
    }
    while (p2 <= R) {
      help[i++] = arr[p2++];
    }

    for (int j = 0; j < help.length; j++) {
      arr[L + i] = help[i];
    }

    return ans;
  }
}
