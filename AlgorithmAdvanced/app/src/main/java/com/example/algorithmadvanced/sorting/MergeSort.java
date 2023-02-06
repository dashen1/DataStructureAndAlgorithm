package com.example.algorithmadvanced.sorting;

import java.util.Arrays;

public class MergeSort {

  public static void main(String[] args) {

    /**
     * merge改写问题
     *
     */

    int maxSize = 20;
    int maxValue = 200;
    int testTimes = 100000;
    System.out.println("测试开始==");
    for (int i = 0; i < testTimes; i++) {
      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr2 = copyArray(arr1);
      int ans1 = smallSum(arr1);
      int ans2 = comparator(arr2);
      if (ans1 != ans2) {
        System.out.println("出错了！");
        System.out.println("ans1:" + ans1);
        System.out.println("ans2:" + ans2);
        break;
      }
    }
    System.out.println("测试结束！");

  }

  public static int[] copyArray(int[] arr) {
    int[] newArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      newArr[i] = arr[i];
    }
    return newArr;
  }

  public static int comparator(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }
    int res = 0;
    for (int i = 1; i < arr.length; i++) {
      for (int j = 0; j < i; j++) {
        res += arr[j] < arr[i] ? arr[j] : 0;
      }
    }
    return res;
  }

  //对数器 随机数
  public static int[] generateRandomArray(int maxSize, int maxValue) {
    int arrSize = (int) (Math.random() * maxSize);
    int[] arr = new int[arrSize];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * maxValue);
    }
    return arr;
  }

  //题目2：求一个数组中一共有多少对逆序对


  //题目1
  public static int smallSum(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }
    return processSmall(arr, 0, arr.length - 1);
  }

  private static int processSmall(int[] arr, int l, int r) {
    if (l == r) {
      return 0;
    }
    int mid = l + ((r - l) >> 1);
    return processSmall(arr, l, mid)
            + processSmall(arr, mid + 1, r)
            + mergeSmall(arr, l, mid, r);
  }

  private static int mergeSmall(int[] arr, int L, int mid, int r) {
    int[] help = new int[r - L + 1];
    int i = 0;
    int p1 = L;
    int p2 = mid + 1;
    int res = 0;
    while (p1 <= mid && p2 <= r) {
      res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
      help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
    }

    while (p1 <= mid) {
      help[i++] = arr[p1++];
    }

    while (p2 <= r) {
      help[i++] = arr[p2++];
    }

    for (int j = 0; j < help.length; j++) {
      arr[L + j] = help[j];
    }
    return res;
  }

  public static int countRangeSUm(int[] nums, int lower, int upper) {
    if (nums == null || nums.length == 0) {
      return 0;
    }

    int[] sum = new int[nums.length];
    sum[0] = nums[0];
    for (int i = 0; i < nums.length; i++) {
      sum[i] = sum[i - 1] + nums[i];
    }
    return 0;
  }

  //arr[L..R]已经不传进来了，只传进来sum（前缀和数组）
  //在原始的arr【L..R]中，有多少个子数组累加和在[lower,upper]上
  public static int count(int[] sum, int L, int R, int lower, int upper) {
    if (L == R) {
      if (sum[L] >= lower && sum[L] <= upper) {
        return 1;
      } else {
        return 0;
      }
    }
    //范围上不止一个位置
    int mid = (L + R) / 2;
    int leftPart = count(sum, L, mid, lower, upper);
    int rightPart = count(sum, mid + 1, R, lower, upper);
    int merge = merge(sum, L, mid, R, lower, upper);
    return leftPart + rightPart + merge;
  }

  private static int merge(int[] sum, int L, int mid, int R, int lower, int upper) {
    //不merge

    return 0;
  }

  public static void mergeSort2(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }

    int N = arr.length;
    //步长
    int mergeSize = 1;
    while (mergeSize < N) {
      int L = 0;
      while (L < N) {
        int M = L + mergeSize - 1;
        if (M > N) {
          break;
        }
        int R = Math.min(M + mergeSize, N - 1);
        // L ... M M+1...R
        merge(arr, L, M, R);
        L = R + 1;
      }
      //防止溢出
      if (mergeSize > N / 2) {
        break;
      }
      mergeSize <<= 1;
    }
  }

  public static void mergeSort1(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    process(arr, 0, arr.length - 1);
  }

  public static void process(int[] arr, int L, int R) {
    if (L == R) {
      return;
    }
    int mid = L + ((R - L) >> 1);
    process(arr, L, mid);
    process(arr, mid + 1, R);
    merge(arr, L, mid, R);
  }

  public static void merge(int[] arr, int L, int M, int R) {
    int[] help = new int[R - L + 1];
    int i = 0;
    int p1 = L;
    int p2 = M + 1;
    while (p1 <= M && p2 <= R) {
      help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }

    //要么p1越界 要么p2越界
    while (p1 <= M) {
      help[i++] = arr[p1++];
    }

    while (p2 <= R) {
      help[i++] = arr[p2++];
    }

    for (int j = 0; j < help.length; j++) {
      arr[L + j] = help[j];
    }
  }
}
