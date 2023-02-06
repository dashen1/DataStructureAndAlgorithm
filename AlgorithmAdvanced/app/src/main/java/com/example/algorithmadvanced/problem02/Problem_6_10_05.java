package com.example.algorithmadvanced.problem02;

public class Problem_6_10_05 {
  public static void main(String[] args) {
    /**
     * 吃香蕉 leetcode 875
     * 可可喜欢吃香蕉，这里有N堆香蕉，第i堆中有piles[i]根香蕉。警卫已经离开，将在H小时后回来
     * 可可可以决定她吃香蕉的速度K(单位：根.小时)。每个小时，她将会选择一堆香蕉，从中吃掉K根，
     * 如果这堆香蕉少于K根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉
     * 可可可以慢慢吃，但仍想在警卫回来前吃掉所有的香蕉
     * 返回它可以在 H 小时内吃掉所有香蕉的最小速度 K (K为整数)
     * 输入：piles = [3,6,7,11], H = 8
     * 输出：4
     *
     * 输入：piles = [30,11,23,4,20], H = 5
     * 输出：30
     *
     * 思路：二分
     *
     */
    int[] piles = {30,11,23,4,20};
    System.out.println("time:"+getMinSpeed(piles,5));
  }

  public static int getMinSpeed(int[] piles, int h) {
    int L = 1;
    int R = 0;
    for (int pile : piles) {
      R = Math.max(R, pile);
    }
    int ans = 0;
    int M = 0;
    while (L <= R) {
      M = L + ((R - L) >> 1);
      if (hours(piles, M) <= h) {
        ans = M;
        R = M - 1;
      } else {
        L = M + 1;
      }
    }
    return ans;
  }

  // piles = [30,11,23,4,20], H = 5
  public static int hours(int[] piles, int speed) {
    int ans = 0;
    int offset = speed - 1;
    for (int pile : piles) {
      //offset偏移量的作用是保证有香蕉的时候向上取整，比如只有1根香蕉的时候，也需要1个小时
      ans += (pile + offset) / speed;
    }
    return ans;
  }

}
