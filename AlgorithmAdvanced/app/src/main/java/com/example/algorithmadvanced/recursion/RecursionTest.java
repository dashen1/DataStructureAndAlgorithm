package com.example.algorithmadvanced.recursion;

public class RecursionTest {

  public static void main(String[] args) {

  }

  public static int process(int[] arr, int L, int R) {
    //base case 递归什么时候结束 答案显而易见
    //任何递归都可改为非递归
    if (L == R) {
      return arr[L];
    }
    int mid = L + ((R - L) >> 1);
    int leftMax = process(arr, L, mid);
    int rightMax = process(arr, mid + 1, R);
    return Math.max(leftMax, rightMax);
  }

  //Master 公式 --》用来分析递归时间复杂度 T(N) = a*T(N/b)+O(N^d)
}
