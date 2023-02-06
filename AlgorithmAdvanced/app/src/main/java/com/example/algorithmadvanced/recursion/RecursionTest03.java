package com.example.algorithmadvanced.recursion;

public class RecursionTest03 {

  public static void main(String[] args) {

  }

  public static int way1(int[] arr, int aim) {
    return process(arr, 0, aim);
  }

  public static int process(int[] arr, int index, int rest) {
    if (index == arr.length) {
      return rest == 0 ? 1 : 0;
    }
    //还有钱可以选
    int ways = 0;
    for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {
      ways += process(arr, index + 1, rest - arr[index] * zhang);
    }
    return ways;
  }

  //对有枚举型的进行优化
  public static int ways2(int[] arr, int aim) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int N = arr.length;
    int[][] dp = new int[N][N];
    dp[N][0] = 1;
    for (int index = N - 1; index >= 0; index--) {
      for (int rest = 0; rest <= aim; rest++) {
        int ways = 0;
        for (int zhang = 0; arr[index] * zhang <= rest; zhang++) {
          ways += dp[index + 1][rest - arr[index] * zhang];
        }
        dp[index][rest] = ways;
      }
    }
    return dp[0][aim];
  }

  public static int dpWays(int[] arr, int aim){
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int N = arr.length;
    int[][] dp=new int[N+1][aim+1];
    dp[N][0] = 1;
    for (int index = N-1;index>=0;index--){
      for (int rest = 0;rest<=aim;rest++){
        dp[index][rest] = dp[index+1][rest];
        if(rest-arr[index]>=0){
          dp[index][rest]+=dp[index][rest-arr[index]];
        }
      }
    }
    return dp[0][aim];
  }
}
