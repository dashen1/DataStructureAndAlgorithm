package com.example.algorithmadvanced.problem02;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Problem_6_10_08 {
  public static void main(String[] args) {
    /**
     * 避免洪水泛滥
     * [3 1 7]
     * 0天 3号湖下雨
     * 1天 1号湖下雨
     * 2天 7号湖下雨
     */
  }

  //rains[i] = j 第i天轮到j号湖泊下雨
  public static int[] avoidFlood(int[] rains) {
    int n = rains.length;
    int[] ans = new int[n];
    int[] invalid = new int[0];
    //遍历统计
    //key 某个湖
    //value 这个湖在哪些位置下雨
    //4 : {3,7,19,21}
    //1 : {13}
    //2 : {4,56}
    HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
    for (int i = 0; i < n; i++) {
      if (rains[i] != 0) {
        if (!map.containsKey(rains[i])) {
          map.put(rains[i], new LinkedList<>());
        }
        map.get(rains[i]).addLast(i);
      }
    }
    //某个湖如果满了，加入到set里
    //某个湖被抽干了，从set中移除
    HashSet<Integer> set = new HashSet<>();
    //这个堆的堆顶表示最先处理的湖是哪个
    PriorityQueue<Work> heap = new PriorityQueue<Work>();
    for (int i = 0; i < n; i++) {
      if (rains[i] != 0) {//下雨，不干活
        if (set.contains(rains[i])) {//如果没抽干的表里存在当前要下雨的湖，则发洪水，结束
          return invalid;
        }
        //如果没抽干的表里不存在当前要下雨的湖
        set.add(rains[i]);
        map.get(rains[i]).pollFirst();
        if (!map.get(rains[i]).isEmpty()) {
          heap.add(new Work(rains[i], map.get(rains[i]).peekFirst()));
        }
        ans[i] = -1;
      } else {//不下雨，干活
        if (heap.isEmpty()) {//如果干活表里什么都没有，就不需要干活
          ans[i] = 1;
        } else {
          Work cur = heap.poll();
          set.remove(cur.lake);//对当前湖抽完水后，需要将下雨表对应的湖删除
          ans[i] = cur.lake;//今天对哪个湖干活了
        }
      }
    }
    return ans;
  }

  public static class Work implements Comparable<Work> {
    public int lake;
    public int nextRainTime;

    public Work(int lake, int nextRainTime) {
      this.lake = lake;
      this.nextRainTime = nextRainTime;//下次下雨的时间
    }

    @Override
    public int compareTo(Work o) {
      return nextRainTime - o.nextRainTime;
    }
  }
}
