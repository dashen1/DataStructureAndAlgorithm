package com.example.algorithmadvanced.problem02;

public class Problem_6_9_04 {
  public static void main(String[] args) {
    /**
     * 二维0,1矩阵
     * 最佳聚会地点：所有人在二维矩阵中距离最短的点
     * 大流程：先找到最优行，然后再找到最优列，最后这个最优行，列坐标点就是最佳聚会地点
     */
  }

  public static int minTotalDistance(int[][] grid) {
    int N = grid.length;
    int M = grid[0].length;
    int[] iOnes = new int[N];
    int[] jOnes = new int[M];
    //统计行和列1的个数
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (grid[i][j] == 1) {
          iOnes[i]++;
          jOnes[j]++;
        }
      }
    }
    int total = 0;
    int i = 0;
    int j = N - 1;
    int iRest = 0;
    int jRest = 0;
    //分别找出最优行和列
    while (i < j) {
      if (iOnes[i] + iRest <= iOnes[j] + jRest) {
        total += iOnes[i] + iRest;
        iRest += iOnes[i++];
      } else {
        total += iOnes[j] + jRest;
        jRest += iOnes[j--];
      }
    }
    i = 0;
    j = M - 1;
    iRest = 0;
    jRest = 0;
    while (i < j) {
      if (jOnes[i] + iRest <= jOnes[j] + jRest) {
        total += jOnes[i] + iRest;
        iRest += jOnes[i++];
      } else {
        total += jOnes[j] + jRest;
        jRest += jOnes[j--];
      }
    }
    return total;
  }
}
