package com.example.algorithmadvanced.problem;

public class Problem_6_1_06 {

  public static void main(String[] args) {
    /**
     * 超级洗衣机
     */
  }

  public static int findMinMoves(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int size = arr.length;
    int sum = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
    }
    if (sum % size != 0) {
      return -1;
    }
    // leftRest | i | rightRest
    // i洗衣机需要从左侧得到或者给出leftRest件衣服，需要从右侧得到或者给出rightRest件衣服
    int avg = sum / size;
    int leftSum = 0;//左侧的累加和
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      int leftRest = leftSum - i * avg;
      //(sum - leftSum - arr[i]) 右侧拥有的衣服
      //(size - i - 1) * avg 右侧需要的衣服
      int rightRest = (sum - leftSum - arr[i]) - (size - i - 1) * avg;
      if (leftRest < 0 && rightRest < 0) {
        //i洗衣机需要给出衣服给左侧和右侧
        ans = Math.max(ans, Math.abs(leftRest) + Math.abs(rightRest));
      } else {
        ans = Math.max(ans, Math.max(Math.abs(leftRest), Math.abs(rightRest)));
      }
    }
    return ans;
  }
}
