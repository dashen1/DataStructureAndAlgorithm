package com.example.algorithmadvanced.problem02;

public class Problem_6_10_07 {
  public static void main(String[] args) {
    /**
     * K个关闭的灯泡
     */
  }

  public static int kEmptySlots2(int[] bulbs, int k) {
    //灯数组转为时间数组
    int n = bulbs.length;
    int[] days = new int[n];
    //原数组     [4 2 1 3] -> 4号灯泡在1小时的时候亮，2号灯泡在2小时的时候亮 ...
    //转换的数组 [3 2 4 1] -> 1号灯泡在3小时的时候亮，2号灯泡在2小时的时候亮 ...
    for (int i = 0; i < n; i++) {
      days[bulbs[i] - 1] = i + 1;
    }
    int ans = Integer.MAX_VALUE;

    for (int left = 0, mid = 1, right = k + 1; right < n; mid++) {
      //验证指针mid
      //mid 永远不和left撞上的
      //1）mid在left和right中间验证的时候，没有通过
      //2）mid撞上right的时候
      if (mid == right) {
        if (days[mid] <= Math.max(days[left], days[right])) {
          ans = Math.min(ans, Math.max(days[left], days[right]));
        }
        left = mid;
        right = mid + k + 1;
      }
    }
    return (ans == Integer.MAX_VALUE) ? -1 : ans;
  }
}
