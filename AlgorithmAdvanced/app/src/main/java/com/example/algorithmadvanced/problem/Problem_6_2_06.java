package com.example.algorithmadvanced.problem;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.PriorityQueue;

public class Problem_6_2_06 {

  /**
   * 二维接雨水
   */

  public static class Node {
    public int value;
    public int row;
    public int col;

    public Node(int value, int row, int col) {
      this.value = value;
      this.row = row;
      this.col = col;
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  public static int trapRainWater(int[][] heightMap) {
    if (heightMap == null || heightMap.length == 0 || heightMap[0] == null || heightMap[0].length == 0) {
      return 0;
    }
    int N = heightMap.length;
    int M = heightMap[0].length;
    //用于检查是否对上下左右都遍历过
    boolean[][] isEnter = new boolean[N][M];
    //小根堆 保证每次拿出来的都是最小的数
    PriorityQueue<Node> heap = new PriorityQueue<>((a, b) -> a.value - b.value);
    //先将最外围的数都放进堆
    //将最上边数放进堆
    for (int col = 0; col < M - 1; col++) {
      isEnter[0][col] = true;
      heap.add(new Node(heightMap[0][col], 0, col));
    }
    //将最左边的数放进堆
    for (int row = 0; row < N - 1; row++) {
      isEnter[row][0] = true;
      heap.add(new Node(heightMap[row][0], row, 0));
    }
    //将最下边的数放进堆
    for (int col = 0; col < M - 1; col++) {
      isEnter[N - 1][col] = true;
      heap.add(new Node(heightMap[N - 1][col], N - 1, col));
    }
    //将最右边的数放进堆
    for (int row = 0; row < N - 1; row++) {
      isEnter[row][M - 1] = true;
      heap.add(new Node(heightMap[row][M - 1], row, M - 1));
    }
    int water = 0;
    int max = 0;
    while (!heap.isEmpty()) {
      Node cur = heap.poll();
      max = Math.max(max, cur.value);
      int r = cur.row;
      int c = cur.col;
      //查看上下左右有没有进过堆
      //往上查看
      if (r > 0 && !isEnter[r - 1][c]) {
        water += Math.max(0, max - heightMap[r - 1][c]);
        isEnter[r - 1][c] = true;
        heap.add(new Node(heightMap[r - 1][c], r - 1, c));
      }
      //往下查看
      if (r < N - 1 && !isEnter[r + 1][c]) {
        water += Math.max(0, max - heightMap[r + 1][c]);
        isEnter[r + 1][c] = true;
        heap.add(new Node(heightMap[r + 1][c], r + 1, c));
      }
      //往左查看
      if (c > 0 && !isEnter[r][c - 1]) {
        water += Math.max(0, max - heightMap[r][c - 1]);
        heap.add(new Node(heightMap[r][c - 1], r, c - 1));
      }
      //往右查看
      if (c < M - 1 && !isEnter[r][c + 1]) {
        water += Math.max(0, max - heightMap[r][c + 1]);
        isEnter[r][c + 1] = true;
        heap.add(new Node(heightMap[r][c + 1], r, c + 1));
      }
    }
    return water;
  }
}
