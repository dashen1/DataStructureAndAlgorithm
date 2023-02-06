package com.example.algorithmadvanced.problem02;

import java.util.Arrays;

public class Problem_6_8_02 {

  public static void main(String[] args) {
    /**
     * 给定一个数组arr，当拿走某个数a的时候，其他所有的数都+a
     * 请返回最终所有数都拿走的最大分数
     * 比如：[2,3,1]
     * 当拿走3时，获得3分，数组变成[5,4]
     * 当拿走5时，获得5分，数组变成[9]
     * 当拿走9时，获得9分，数组变成[]
     * 这是最大的拿取方式，返回总分17
     */
  }

  //贪心策略
  //注意，当拿走一个数a时，其他所有的数都+a,而拿走的数则作为返回值的一部分，也就是说当返回最大分数时，拿走的数其实相当于两次
  public static int pick(int[] arr) {
    Arrays.sort(arr);
    int ans = 0;
    for (int i = arr.length - 1; i >= 0; i--) {
      ans += (ans << 1) + arr[i];
    }
    return ans;
  }
}
