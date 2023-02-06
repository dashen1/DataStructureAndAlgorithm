package com.example.algorithmadvanced.problem;

import android.os.Parcelable;

public class Problem_6_2_05 {

  /**
   * 一维接雨水问题
   * <p>
   * 水[i]=max{min{左max,右max} - arr[i], 0}
   * <p>
   * 左右max,谁小先结算谁的max
   */

  public static int trap(int[] arr) {
    if (arr == null || arr.length < 2) {
      return 0;
    }

    int N = arr.length;
    int L = 1;
    int leftMax = arr[0];
    int R = N - 2;
    int rightMax = arr[N - 1];
    int water = 0;
    while (L <= R) {
      if (leftMax <= rightMax) {
        water += Math.max(0, leftMax - arr[L]);
        leftMax = Math.max(leftMax, arr[L++]);
      } else {
        water += Math.max(0, rightMax - arr[R]);
        rightMax = Math.max(rightMax, arr[R--]);
      }
    }
    return water;
  }

}
