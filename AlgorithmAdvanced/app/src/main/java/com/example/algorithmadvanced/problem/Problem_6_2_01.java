package com.example.algorithmadvanced.problem;

import java.util.HashMap;

public class Problem_6_2_01 {

  public static void main(String[] args) {
    /**
     * 返回字符串中有多少子面值不同的子序列的数量
     */
    String str = "bccdddsssfffffghgfffffggebbcxccbmnnmb";
    System.out.println("数量：" + zuo(str));
  }

  public static int zuo(String s) {
    if (s == null || s.length() == 0) {
      return 0;
    }
    int m = 1000000007;
    char[] str = s.toCharArray();
    int all = 1;
    HashMap<Character, Integer> map = new HashMap<>();
    for (char x : str) {
      int newAdd = all;
      //记录以x结尾的数量，因为当x和x+1相同的时候，要减去相同结尾的的数量
//      int curAll = all + newAdd - (map.containsKey(x)?map.get(x):0);
      int curAll = all;
      curAll = (curAll + newAdd) % m;
      curAll = (curAll - (map.containsKey(x) ? map.get(x) : 0) + m) % m;
      all = curAll;
      map.put(x, newAdd);
    }
    return all;
  }
}
