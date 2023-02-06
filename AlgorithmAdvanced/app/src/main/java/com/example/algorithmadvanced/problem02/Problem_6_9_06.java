package com.example.algorithmadvanced.problem02;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.HashMap;

public class Problem_6_9_06 {
  public static void main(String[] args) {
    /**
     * K个不同正数的子数组
     * [3,1,1,2,2]
     *
     * 思路：双窗口滑动
     */
  }

  //nums数组 因为题目规定，nums中的数字，不会超过nums的长度
  //所以用数组代替了hashmap做词频统计
  public static int subArraysWithKDistinct1(int[] nums, int k) {
    int n = nums.length;
    //k-1种数的窗口词频统计
    int[] lessCounts = new int[n + 1];
    //K种数的窗口词频统计
    int[] equalCounts = new int[n + 1];
    int lessLeft = 0;
    int equalLeft = 0;
    int lessKinds = 0;
    int equalKinds = 0;
    int ans = 0;
    for (int r = 0; r < n; r++) {
      //当前刚来到r位置
      if (lessCounts[nums[r]] == 0) {
        lessKinds++;
      }
      if (equalCounts[nums[r]] == 0) {
        equalKinds++;
      }
      //词频增加
      lessCounts[nums[r]]++;
      equalCounts[nums[r]]++;
      while (lessKinds == k) {
        if (lessCounts[nums[lessLeft]] == 1) {
          lessKinds--;
        }
        lessCounts[nums[lessLeft++]]--;
      }
      while (equalKinds > k) {
        if (equalCounts[nums[equalLeft]] == 1) {
          equalKinds--;
        }
        equalCounts[nums[equalLeft++]]--;
      }
      ans += lessLeft - equalKinds;
    }
    return ans;
  }

  //第二种解法
  @RequiresApi(api = Build.VERSION_CODES.N)
  public static int subArraysWithDistinct2(int[] arr, int k) {
    return numsMostK(arr, k) - numsMostK(arr, k - 1);
  }

  //todo 代码没理解
  @RequiresApi(api = Build.VERSION_CODES.N)
  private static int numsMostK(int[] arr, int k) {
    int i = 0;
    int res = 0;
    HashMap<Integer, Integer> count = new HashMap<>();
    for (int j = 0; j < arr.length; j++) {
      if (count.getOrDefault(arr[j], 0) == 0) {
        k--;
      }
      count.put(arr[j], count.getOrDefault(arr[j], 0) + 1);
      while (k < 0) {
        count.put(arr[i], count.get(arr[i]) - 1);
        if (count.get(arr[i]) == 0) {
          k++;
        }
        i++;
      }
      res += j - i + 1;
    }
    return res;
  }
}
