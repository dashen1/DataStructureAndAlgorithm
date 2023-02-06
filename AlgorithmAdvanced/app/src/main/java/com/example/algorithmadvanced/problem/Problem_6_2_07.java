package com.example.algorithmadvanced.problem;

import android.net.vcn.VcnConfig;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Problem_6_2_07 {

  public static void main(String[] args) {

    /**
     * TODO
     * 时间复杂度 O(N)
     * 空间复杂度 O(1)
     * 超级水王（帖子灌水）/摩尔投票
     * 给一个数组，是否存在某个数的数量大于数组的一半
     *
     * 解决思路：每次删除两个不同值的数，如果存在水王，数组最后一定会留下那个数 (但是数组剩下来的数不一定是水王)
     */
  }

  public static void printHalfMajor(int[] arr) {
    int candidate = 0;
    int HP = 0;//假设血量
    for (int i = 0; i < arr.length; i++) {
      if (HP == 0) {
        candidate = arr[i];
        HP = 1;
      } else if (arr[i] == candidate) {
        HP++;
      } else {
        HP--;
      }
    }
    if (HP == 0) {
      System.out.println("no such number！");
    }
    HP = 0;
    for (int j = 0; j < arr.length; j++) {
      if (arr[j] == candidate) {
        HP++;
      }
    }
    if (HP > arr.length / 2) {
      System.out.println(candidate);
    } else {
      System.out.println("no such number!");
    }
  }

  /**
   * 扩展
   * ？次 > N/K次的数有多少个？  使用k-1个候选
   * 迭代器边迭代边删除会出问题
   */
  public static void printKMajor(int[] arr, int k) {
    if (k < 2) {
      System.out.println("the value of k is invalid.");
      return;
    }
    //赞候选 cands,候选表，最多K-1条记录 > N / K次的数字，最多有K -1
    HashMap<Integer, Integer> cands = new HashMap<>();
    for (int i = 0; i != arr.length; i++) {
      if (cands.containsKey(arr[i])) {
        cands.put(arr[i], cands.get(arr[i]) + 1);
      } else {//当前数arr[i]不是候选
        if (cands.size() == k - 1) {//当前数不能要，同时每个候选血量减1
          allCandsMinusOne(cands);
        } else {
          cands.put(arr[i], 1);
        }
      }
    }
    //所有可能的候选，都在cands表中，遍历一遍arr，每个候选手机真实的次数
    HashMap<Integer, Integer> reals = getReals(arr, cands);
    boolean hasPrint = false;
    for (Map.Entry<Integer, Integer> set : reals.entrySet()) {
      Integer value = set.getValue();
      if (value > (arr.length / k)) {
        hasPrint = true;
        System.out.println(set.getKey());
      }
    }
    System.out.println(hasPrint ? "" : "no such number!");
  }

  private static HashMap<Integer, Integer> getReals(int[] arr, HashMap<Integer, Integer> cands) {
    HashMap<Integer, Integer> reals = new HashMap<>();
    for (int i = 0; i != arr.length; i++) {
      int curNum = arr[i];
      if (cands.containsKey(curNum)) {
        if (reals.containsKey(curNum)) {
          reals.put(curNum, reals.get(curNum) + 1);
        } else {
          reals.put(curNum, 1);
        }
      }
    }
    return reals;
  }

  private static void allCandsMinusOne(HashMap<Integer, Integer> map) {
    LinkedList<Integer> removeList = new LinkedList<>();
    for (Map.Entry<Integer, Integer> set : map.entrySet()) {
      Integer key = set.getKey();
      Integer value = set.getValue();
      if (value == 1) {
        removeList.add(key);
      }
      map.put(key, value - 1);
    }
    for (Integer removeKey : removeList) {
      map.remove(removeKey);
    }
  }
}
