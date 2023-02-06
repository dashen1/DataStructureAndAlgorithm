package com.example.algorithmadvanced.problem02;

import java.sql.SQLOutput;
import java.util.Arrays;

import javax.xml.transform.Source;

public class Problem_6_9_08 {
  public static void main(String[] args) {
    /**
     * 供暖器
     * 供暖站对用户覆盖范围最小（供暖半径）
     * 暖炉点有重复的时候有坑，死循环
     */

    int maxLen = 5;
    int maxValue = 20;
    int testTimes = 10000;
    for (int i = 0; i < testTimes; i++) {
      int[] a = randomArray(maxLen, maxValue);
      int[] b = randomArray(maxLen, maxValue);
      int ans1 = findRadius(a, b);
      int ans2 = findRadius2(a, b);
      if (ans1 != ans2) {
        System.out.println("A:");
        for (int num : a) {
          System.out.print(num + " ");
        }
        System.out.println();
        System.out.println("B:");
        for (int num : b) {
          System.out.print(num + " ");
        }
        System.out.println();
        System.out.println(ans1);
        System.out.println();
        System.out.println(ans2);
      }
    }
  }

  public static int findRadius(int[] houses, int[] heaters) {
    Arrays.sort(houses);
    Arrays.sort(heaters);
    int ans = 0;
    //时间复杂度O(N)
    //i是地点，j是供暖点
    for (int i = 0, j = 0; i < houses.length; i++) {
      while (!best(houses, heaters, i, j)) {
        j++;
      }
      ans = Math.max(ans, Math.abs(heaters[j] - houses[i]));
    }
    return ans;
  }

  public static int findRadius2(int[] houses, int[] heaters) {
    Arrays.sort(houses);
    Arrays.sort(heaters);
    int ans = 0;
    //时间复杂度O(N)
    //i是地点，j是供暖点
    for (int i = 0, j = 0; i < houses.length; i++) {
      while (!best2(houses, heaters, i, j)) {
        j++;
      }
      ans = Math.max(ans, Math.abs(heaters[j] - houses[i]));
    }
    return ans;
  }

  //当前的地点houses[i]由heaters[j]来供暖是最优解的吗？
  //当前的地点houses[i]由heaters[j]来供暖，产生的半径是a
  //当前的地点houses[i]由heaters[j+1]来供暖，产生的半径是b
  //如果a < b,说明是最优解供暖不应该跳到下一个位置
  //如果a >= b,说明不是最优解，应该跳到下一个位置
  private static boolean best(int[] houses, int[] heaters, int i, int j) {
    return j == heaters.length - 1
            || Math.abs(heaters[j] - houses[i]) < Math.abs(heaters[j + 1] - houses[i]);
  }

  private static boolean best2(int[] houses, int[] heaters, int i, int j) {
    return j == heaters.length - 1
            || Math.abs(heaters[j] - houses[i]) <= Math.abs(heaters[j + 1] - houses[i]);
  }

  public static int[] randomArray(int maxLen, int maxValue) {
    int[] arr = new int[maxLen];
    for (int i = 0; i < maxLen; i++) {
      arr[i] = (int) (Math.random() * maxValue) + 1;
    }
    return arr;
  }
}
