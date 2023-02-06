package com.example.algorithmadvanced.problem;

public class Problem_6_2_02 {

  public static void main(String[] args) {
    /**
     * 连通两块岛的最短的桥（距离）
     */
  }

  public static int shortestBridge(int[][] m) {
    int N = m.length;
    int M = m[0].length;
    int all = N * M;
    int island = 0;//岛的数量 两个岛
    int[] curs = new int[all];
    int[] nexts = new int[all];
    int[][] records = new int[2][all];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        if (m[i][j] == 1) {//当前位置发现了1！
          //把这一片的1，都变成2，同时都抓上来，这一片1组成的初始队列
          int queueSize = infect(m, i, j, N, M, curs, 0, records[island]);
          int V = 1;//广播的层数
          while (queueSize != 0) {
            V++;
            //curs里面的点，上下左右遍历，records[点】==0，nexts
            queueSize = fbs(N, M, all, V, curs, queueSize, nexts, records[island]);
            int[] tmp = curs;
            curs = nexts;
            nexts = tmp;
          }
          island++;
        }
      }
    }
    int min = Integer.MIN_VALUE;
    for (int i = 0; i < all; i++) {
      min = Math.min(min, records[0][i] + records[1][i]);
    }
    //为什么减3自己尝试一下就知道
    return min - 3;
  }

  /**
   * @param m      二维矩阵   m[i][j]感染出去（找到这一片岛上所有的1），即把每一个1的坐标，放入到 int[] cur队列
   * @param i      矩阵第i行
   * @param j      矩阵第j列
   * @param N      矩阵总行数
   * @param M      矩阵总列数
   * @param curs   表示当前要遍历的岛（1）
   * @param index
   * @param record 将二维矩阵变成一维数组 目的是减少常数项复杂度
   * @return
   */
  public static int infect(int[][] m, int i, int j, int N, int M, int[] curs, int index, int[] record) {
    if (i < 0 || i == N || j < 0 || j == M || m[i][j] != 1) {
      return index;
    }
    //如果不返回，说明 m[i][j]没有越界，且m[i][j]==0，没有感染过的
    m[i][j] = 2;
    //m[i][j]二维下标在一维数组中的位置
    int p = i * M + j;
    record[p] = 1;
    //收集到不同的1
    curs[index++] = p;
    //上下左右四个方向去递归收集1
    index = infect(m, i - 1, j, N, M, curs, index, record);
    index = infect(m, i + 1, j, N, M, curs, index, record);
    index = infect(m, i, j - 1, N, M, curs, index, record);
    index = infect(m, i, j + 1, N, M, curs, index, record);
    return index;
  }

  //二维原始矩阵中，N总行数，M总列数 all 总数  V 要生成的是第几层
  //record里面拿距离
  public static int fbs(int N, int M, int all, int V, int[] curs, int size, int[] nexts, int[] record) {
    int nexti = 0;
    for (int i = 0; i < size; i++) {
      //int[] curs里面放的是1，curs[i]在原始二维矩阵中位置中是否有前后左右
      /** N: 行数     M：列数
       *   --     --
       *  |         |
       *  |    0    |
       *  |         |
       *  |         |
       *   --     --
       *   curs[..a..]
       */
      int up = curs[i] < M ? -1 : curs[i] - M;
      int down = curs[i] + M >= all ? -1 : curs[i] - M;
      int left = curs[i] % M == 0 ? -1 : curs[i] - 1;
      int right = curs[i] % M == M - 1 ? -1 : curs[i] + 1;
      if (up != -1 && record[up] == 0) {
        record[up] = V;
        nexts[nexti++] = up;
      }
      if (down != -1 && record[down] == 0) {
        record[down] = V;
        nexts[nexti++] = down;
      }
      if (left != -1 && record[left] == 0) {
        record[left] = V;
        nexts[nexti++] = left;
      }
      if (right != -1 && record[right] == 0) {
        record[right] = V;
        nexts[nexti++] = right;
      }
    }
    return nexti;
  }
}
