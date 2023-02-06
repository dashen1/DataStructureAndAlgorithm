package com.example.algorithmadvanced.recursion;

import javax.xml.transform.Source;

public class RecursionTest01 {

  public static void main(String[] args) {
    int[] arr = {3, 100, 2, 50};
    System.out.println(f(arr, 0, 3));

    System.out.println("使用动态表："+dpF(arr));
  }


  //先手
  public static int f(int[] arr, int i, int j) {
    if (i == j) {
      return arr[i];
    }
    return Math.max(
            arr[i] + last(arr, i + 1, j),
            arr[j] + last(arr, i, j - 1)
    );
  }

  public static int last(int[] arr, int i, int j) {
    if (i == j) {
      return 0;
    }
    return Math.min(
            f(arr, i + 1, j),
            f(arr, i, j - 1)
    );
  }

  public static int dpF(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int[][] f = new int[arr.length][arr.length];
    int[][] l = new int[arr.length][arr.length];
    for (int i = 0; i < arr.length; i++) {
      f[i][i] = arr[i];
    }
    int row = 0;
    int col = 1;
    //对角线开始位置row行col列
    while (col < arr.length) {
      int i = row;
      int j = col;
      while (i < arr.length && j < arr.length) {
        f[i][j]=Math.max(arr[i]+l[i+1][j],arr[j]+l[i][j-1]);
        l[i][j]=Math.min(f[i+1][j],f[i][j-1]);
        i++;
        j++;
      }
      col++;
    }
    return Math.max(f[0][arr.length-1],l[0][arr.length-1]);
  }
}
