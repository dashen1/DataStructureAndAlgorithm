package com.example.algorithmadvanced.problem;

public class Problem_6_1_03 {

  public static void main(String[] args) {
    /**
     * 跳棋问题
     */
  }

  public static int jump(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    /**
     * cur是step步能到的位置，next是step+1步能到的位置
     * 只有大于cur所能到达的位置才能更新step和cur，
     * 也只有大于next步的的才更新
     */
    int step = 0;
    int cur = 0;
    int next = 0;
    for (int i = 0; i < arr.length; i++) {
      if (cur < i) {
        step++;
        cur = next;
      }
      next = Math.max(next, i + arr[i]);
    }
    return step;
  }
}
