package com.example.algorithmadvanced.search;

import java.util.Arrays;

public class BinarySearch {

  public static void main(String[] args) {
    int[] arr = {1, 2, 5, 5, 11, 76};
    System.out.println(findBinary(arr, 0));

    System.out.println("k:" + mostLeftNoLessNumIndex(arr, 6));

    System.out.println("====================");

    int testTimes = 100000;
    int maxLen = 20;
    int maxValue = 200;
    for (int i = 0; i < testTimes; i++) {
      int[] arr2 = randomArray(maxLen, maxValue);
      int ans = onMinIndex(arr2);
      if(!check(arr2, ans)){
        System.out.println(Arrays.toString(arr2));
        System.out.println("ans:"+ans);
        break;
      }
    }
  }

  //返回其中一个局部最小数
  //arr整体无序 arr相邻的数不相等
  public static int onMinIndex(int[] arr) {
    if (arr == null || arr.length == 0) {
      return -1;
    }
    int N = arr.length;
    if (N == 1) {
      return 0;
    }
    if (arr[0] < arr[1]) {
      return 0;
    }
    if (arr[N - 1] < arr[N - 2]) {
      return N - 1;
    }
    int L = 0;
    int R = N - 1;
    while (L < R - 1) {
      int mid = (L + R) / 2;
      if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {
        return mid;
      } else {//不同时小
        //1 左>mid mid>右
        //1 左<mid mid<右
        //1 左<mid mid>右
        if (arr[mid] > arr[mid - 1]) {
          R = mid - 1;
        } else {
          L = mid + 1;
        }
      }
    }
    return arr[L] < arr[R] ? L : R;
  }


  public static int[] randomArray(int maxLen, int maxValue) {
    int len = (int) (Math.random() * maxLen);
    int[] arr = new int[len];
    if (len > 0) {
      arr[0] = (int) (Math.random() * maxValue);
      for (int i = 1; i < len; i++) {
        do {
          arr[i] = (int) (Math.random() * maxValue);
        } while (arr[i] == arr[i - 1]);
      }
    }
    return arr;
  }

  public static boolean check(int[] arr, int minIndex) {
    if (arr.length == 0) {
      return minIndex == -1;
    }
    int left = minIndex - 1;
    int right = minIndex + 1;
    boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
    boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;
    return leftBigger && rightBigger;
  }

  public static int mostLeftNoLessNumIndex(int[] arr, int num) {
    if (arr == null || arr.length == 0) {
      return -1;
    }

    int L = 0;
    int R = arr.length - 1;
    int ans = -1;
    //L < R-1 保证有三个及三个数以上
    while (L <= R) {
      int mid = (L + R) / 2;
      if (arr[mid] >= num) {
        ans = mid;
        R = mid - 1;
      } else {
        L = mid + 1;
      }
    }
    return ans;
  }

  public static boolean findBinary(int[] arr, int num) {
    if (arr == null || arr.length == 0) {
      return false;
    }
    int L = 0;
    int R = arr.length - 1;
    while (L <= R) {
      int mid = (R + L) / 2;
      if (arr[mid] == num) {
        return true;
      } else if (arr[mid] > num) {
        R = mid - 1;
      } else {
        L = mid + 1;
      }
    }
    return false;
  }
}
