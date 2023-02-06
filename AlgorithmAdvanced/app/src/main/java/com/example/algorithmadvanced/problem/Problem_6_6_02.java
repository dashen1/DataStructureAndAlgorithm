package com.example.algorithmadvanced.problem;

public class Problem_6_6_02 {

  public static void main(String[] args) {
    /**
     * 矩阵置零 （空间复杂度最小）
     * 普通思路：分别用行表和列表记录要置零的下标位置，后面再遍历置零
     */
  }

  public static void setZeroes1(int[][] matrix) {
    boolean row0Zero = false;
    boolean col0Zero = false;
    int i = 0;
    int j = 0;
    for (i = 0; i < matrix[0].length; i++) {
      if (matrix[0][i] == 0) {
        row0Zero = true;
        break;
      }
    }
    for (j = 0; j < matrix.length; j++) {
      if (matrix[i][0] == 0) {
        col0Zero = true;
        break;
      }
    }
    for (i = 1; i < matrix.length; i++) {
      for (j = 1; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          matrix[0][j] = 0;
        }
      }
    }
    for (i = 1; i < matrix.length; i++) {
      for (j = 0; j < matrix[0].length; j++) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }
    }
    if (row0Zero) {
      for (i = 0; i < matrix[0].length; i++) {
        matrix[0][i] = 0;
      }
    }
    if (col0Zero) {
      for (j = 0; j < matrix.length; j++) {
        matrix[j][0] = 0;
      }
    }
  }

  public static void setZeroes2(int[][] matrix) {
    boolean col0 = false;
    int i = 0;
    int j = 0;
    for (i = 0; i < matrix.length; i++) {
      for (j = 0; j < matrix[0].length; j++) {
        if (matrix[i][j] == 0) {
          matrix[i][0] = 0;
          //判断matrix第一列是否有0
          if (j == 0) {
            col0 = true;//用于标志将第一列置零
          } else {
            matrix[0][j] = 0;
          }
        }
      }
    }
    for (i = matrix.length - 1; i >= 0; i--) {
      for (j = 1; j < matrix[0].length; j++) {
        if (matrix[i][0] == 0 || matrix[0][j] == 0) {
          matrix[i][j] = 0;
        }
      }
    }
    if (col0) {
      for (i = 0; i < matrix.length; i++) {
        matrix[i][0] = 0;
      }
    }
  }
}
