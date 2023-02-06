package com.example.algorithmadvanced.problem02;

import java.util.Arrays;

public class Problem_6_9_05 {
  public static void main(String[] args) {
    /**
     * 给定一个正数数组arr，长度为n,正数x和y
     * 你的目标是让arr整体的累加和<=0
     * 你可以对数组中的数num执行以下两种操作中的一种，且每个数最多能执行一种操作
     * 1）可以选择让num变成0，承担x的代价
     * 2）可以选择让num变成-num，承担y的代价
     * 返回你达到目标的最小代价
     * 如：[3,7,2,5]
     * 1)无
     * 2）7 -> 0, 代价 x
     * 3）7 -> -7, 代价 y
     * 返回总代价最小的
     *
     * 思路：int f(i,sum) 直到sum<=0
     */
  }

  //i 下标
  //还剩下sum这么多累加和，需要去搞定

  public static int process1(int[] arr, int x, int y, int i, int sum) {
    if (sum <= 0) {
      return 0;
    }
    if (i == arr.length) {
      return Integer.MAX_VALUE;
    }
    //前面两个base case
    int p1 = process1(arr, x, y, i + 1, sum);
    //变0
    int p2 = Integer.MAX_VALUE;
    int next2 = process1(arr, x, y, i + 1, sum - arr[i]);
    if (next2 != Integer.MAX_VALUE) {
      p2 = x + next2;
    }
    //变负数
    int p3 = Integer.MAX_VALUE;
    int next3 = process1(arr, x, y, i + 1, sum - (arr[i] << 1));
    if (next3 != Integer.MAX_VALUE) {
      p3 = y + next3;
    }
    return Math.min(p1, Math.max(p2, p3));
  }

  /**
   * 优化：贪心策略
   */
  public static int minOpStep2(int[] arr, int x, int y) {
    //排序后不影响求解
    Arrays.sort(arr);
    int N = arr.length;
    //逆序 大 -> 小
    for (int l = 0, r = N - 1; l <= r; l++, r--) {
      int tmp = arr[l];
      arr[l] = arr[r];
      arr[r] = tmp;
    }
    //如果x的代价大于等于y,根本不需要考虑x代价的求解了
    if (x >= y) {
      int sum = 0;
      for (int num : arr) {
        sum += num;
      }
      int cost = 0;
      for (int i = 0; i < N && sum > 0; i++) {
        sum -= arr[i] << 1;
        cost += y;
      }
      return cost;
    } else {
      //当某一个数不再执行y操作时，后序不会再执行y操作，
      //当某一个数不再执行x操作，后序不会再执行x操作

      //利用后缀和数组加快速度
      for (int i = N - 2; i >= 0; i--) {
        arr[i] += arr[i + 1];
      }
      int benefit = 0;
      //注意，可以不二分，用指针不回退的方式 因为排序的时候时间复杂度已经是 O(N*logN)
      //执行y操作的数，有0个的时候
      //因为原始数组可以是 [8,6,5,4,2,0,0,0,0]
      int left = mostLeft(arr, 0, benefit);
      int cost = left * x;
      for (int i = 0; i < N - 1; i++) {
        //0...i 0到i个数都执行y操作
        benefit += arr[i] - arr[i + 1];//得到原数组值

        left = mostLeft(arr, i + 1, benefit);
        cost = Math.min(cost, (i + 1) * y + (left - i - 1) * x);
      }
      return cost;
    }
  }

  //二分法 在后缀和数组中从[l...N-1]位置上找到 <= v的最左位置
  public static int mostLeft(int[] arr, int l, int v) {
    int r = arr.length - 1;
    int m = 0;
    int ans = arr.length;
    while (l <= r) {
      m = (l + r) / 2;
      if (arr[m] <= v) {
        ans = m;
        r = m - 1;//因为arr是降序的注意
      } else {
        l = m + 1;
      }
    }
    return ans;
  }

  public static int minOpStep(int[] arr, int x, int y) {
    //排序后不影响求解
    Arrays.sort(arr);
    int N = arr.length;
    //逆序 大 -> 小
    for (int l = 0, r = N - 1; l <= r; l++, r--) {
      int tmp = arr[l];
      arr[l] = arr[r];
      arr[r] = tmp;
    }
    //如果x的代价大于等于y,根本不需要考虑x代价的求解了
    if (x >= y) {
      int sum = 0;
      for (int num : arr) {
        sum += num;
      }
      int cost = 0;
      for (int i = 0; i < N && sum > 0; i++) {
        sum -= arr[i] << 1;
        cost += y;
      }
      return cost;
    } else {//x < y
      //当某一个数不再执行y操作时，后序不会再执行y操作，
      //当某一个数不再执行x操作，后序不会再执行x操作
      for (int yLen = 0; yLen < N; yLen++) {
        for (int xLen = 0; xLen <= N - yLen; xLen++) {

        }
      }
    }
    return 0;
  }
}
