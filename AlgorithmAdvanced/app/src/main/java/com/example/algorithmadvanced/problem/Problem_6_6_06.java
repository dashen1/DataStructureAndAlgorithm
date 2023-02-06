package com.example.algorithmadvanced.problem;

public class Problem_6_6_06 {
  public static void main(String[] args) {
    /**
     * 打家劫舍
     * 子序列最大累加和（不能取相邻数）
     */

    int[] arr = {1,24,55,34,6,7,23,8,10};
    System.out.println("sum:"+pickMaxSum2(arr));
  }

  //arr长度大于等于1
  public static int pickMaxSum(int[] arr) {
    int N = arr.length;
    //dp[i]:表示arr[0..i]范围上，随意算则，但是，任何两个数不能相邻，得到的最大累加和是多少？
    int[] dp = new int[N];
    dp[0] = arr[0];
    dp[1] = Math.max(arr[0], arr[1]);
    for (int i = 2; i < N; i++) {
      int p1 = arr[i];
      int p2 = dp[i - 1];
      int p3 = dp[i - 2] + arr[i];
      dp[i] = Math.max(p1, Math.max(p2, p3));
    }
    return dp[N - 1];
  }

  public static int pickMaxSum2(int[] arr) {
    int N = arr.length;
    //dp[i]:表示arr[0..i]范围上，随意算则，但是，任何两个数不能相邻，得到的最大累加和是多少？
    int pre1 = arr[0];
    int pre2 = Math.max(arr[0], arr[1]);
    for (int i = 2; i < N; i++) {
      int tmp = Math.max(pre2, arr[i] + pre1);
      pre1 = pre2;
      pre2 = tmp;
    }
    return pre2;
  }

  public static int rob(int[] nums) {
    if (nums == null || nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0];
    }
    if (nums.length == 2) {
      return Math.max(nums[0], nums[1]);
    }
    int pre2 = nums[0];
    int pre1 = Math.max(nums[0], nums[1]);
    for (int i = 2; i < nums.length; i++) {
      int tmp = Math.max(pre1, nums[i] + pre2);
      pre2 = pre1;
      pre1 = tmp;
    }
    int ans1 = pre1;
    pre2 = nums[1];
    pre1 = Math.max(nums[1], nums[2]);
    for (int i = 3; i < nums.length; i++) {
      int tmp = Math.max(pre1, nums[i] + pre2);
      pre2 = pre1;
      pre1 = tmp;
    }
    int ans2 = pre1;
    return Math.max(ans1, ans2);
  }
}
