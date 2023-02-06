package com.example.algorithmadvanced.problem;

import android.util.LruCache;

import java.util.Map;

public class Problem_6_2_08 {

  public static void main(String[] args) {

    /**
     * 相邻K个石子才能合并最终合并成一堆的最小代价
     * 相邻K个数合成一个数
     *
     * 思路：搞出一个函数 int f(L,R,k) 返回最小代价
     */

  }

  //arr[L...R]一定要整出P份，合并的最小代价，返回
  public static int f(int[] arr, int K, int L, int R, int P) {
    //从L到R根本不可能弄出P份
    if (arr == null || arr.length == 0) {
      return 0;
    }
    //从L到R根本有可能弄出P份
    if (P == 1) {
      //L == R ? 0 : f(arr, L, R, K, K) + (最后一次合并的代价);
      //f(arr, L, R, K, K) 在arr[L...R]整出K份，最后将这K份合成一份
      // L == R ? 0 如果数组中只有一个数，而且又只是要一份，所以不需要分就符合要求了，所以代价为0
      //return L == R ? 0 : f(arr, K, L, R, K) + (最后一次合并的代价);
      return 0;
    }
    int ans = Integer.MAX_VALUE;
    for (int i = 0; i < R; i++) {
      //递归遍历[0...0]范围上整出一份，[1...R]整出p-1份，
      //递归遍历[0...1]范围上整出一份，[2...R]整出p-1份，
      //递归遍历[0...2]范围上整出一份，[3...R]整出p-1份，
      //...
      int left = f(arr, K, L, i, 1);
      if (left == Integer.MAX_VALUE) {
        continue;
      }
      int right = f(arr, K, i + 1, R, P - 1);
      if (right == Integer.MAX_VALUE) {
        continue;
      }
      int all = left + right;
      ans = Math.min(ans, all);
    }
    return ans;
  }

  //int[] presum 前缀累加和数组，求i...j得累加和，就是O(1)了
  public static int process1(int L, int R, int P, int[] arr, int K, int[] presum) {
    if (L == R) {
      return P == 1 ? 0 : -1;
    }
    if (P == 1) {
      int next = process1(L, R, K, arr, K, presum);
      if (next == -1) {
        return -1;
      } else {
        //presum[R + 1] - presum[L] ???不是很理解
        //因为presum是前缀和 presum[R+1]就表示arr[0...R]数组中所有数的和
        return next + presum[R + 1] - presum[L];
      }
    } else {
      int ans = Integer.MAX_VALUE;
      for (int mid = L; mid < R; mid += K - 1) {
        int next1 = process1(L, mid, 1, arr, K, presum);
        int next2 = process1(mid + 1, R, P - 1, arr, K, presum);
        if (next1 != -1 && next2 != -1) {
          ans = Math.min(ans, next1 + next2);
        }
      }
      return ans;
    }
  }

  public static int mergeStine2(int[] stones, int K) {
    int N = stones.length;
    if ((N - 1) % (K - 1) > 0) {//N个数，到底能不能K个相邻的数合并，最终变成1个数
      return -1;
    }
    int[] presum = new int[N + 1];
    for (int i = 0; i < N; i++) {
      presum[i + 1] = presum[i] + stones[i];
    }
    int[][][] dp = new int[N][N][K + 1];
    return process2(0, N - 1, 1, stones, K, presum, dp);
  }

  //再优化
  public static int process2(int L, int R, int P, int[] arr, int K, int[] presum, int[][][] dp) {
    if (dp[L][R][P] != 0) {
      return dp[L][R][P];
    }
    if (L == R) {
      return P == 1 ? 0 : -1;
    }
    if (P == 1) {
      int next = process2(L, R, K, arr, K, presum, dp);
      if (next == -1) {
        dp[L][R][P] = -1;
        return -1;
      } else {
        dp[L][R][P] = next + presum[R + 1] - presum[L];
        return next + presum[R + 1] - presum[L];
      }
    } else {
      int ans = Integer.MAX_VALUE;
      for (int mid = L; mid < R; mid += K - 1) {
        int next1 = process2(L, mid, 1, arr, K, presum, dp);
        int next2 = process2(mid + 1, R, P - 1, arr, K, presum, dp);
        if (next1 == -1 || next2 == -1) {
          dp[L][R][P] = -1;
          return -1;
        } else {
          ans = Math.min(ans, next1 + next2);
        }
      }
      dp[L][R][P] = ans;
      return ans;
    }
  }

}
