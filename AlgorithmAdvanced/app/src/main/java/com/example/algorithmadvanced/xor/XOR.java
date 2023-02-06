package com.example.algorithmadvanced.xor;

import java.util.HashMap;
import java.util.HashSet;

public class XOR {

  public static void main(String[] args) {

    int[] arr = {2, 2, 2, 1, 4, 4, 4, 4, 5, 5};
    //printOddTimesNum(arr);

    int[] arr2 = {-1, 3, 1, 3, 3, 1, 1, -1};
    //System.out.println(onlyKTimes(arr2, 2, 3));

    int kinds = 4;
    int range = 200;
    int testTimes = 100000;
    int max = 9;
    System.out.println("测试开始");
    int ans2 = 0;
    for (int i = 0; i < testTimes; i++) {
      int a = (int) (Math.random() * max) + 1;//a 1~9
      int b = (int) (Math.random() * max) + 1;//b 1~9
      int k = Math.min(a, b);
      int m = Math.max(a, b);
      //k<m
      if (k == m) {
        m++;
      }
      int[] arr3 = randomArray(kinds, range, k, m);
      int ans1 = test(arr3, k, m);
      ans2 = onlyKTimes(arr3, k, m);
      if (ans1 != ans2) {
        System.out.println("ans1:"+ans1);
        System.out.println("ans2:"+ans2);
        System.out.println("出错了");
        break;
      }
    }
    System.out.println("答案是："+ans2);
    System.out.println("测试结束");
  }

  public static int test(int[] arr, int k, int m) {
    HashMap<Integer, Integer> map = new HashMap<>();
    for (int num : arr) {
      if (map.containsKey(num)) {
        map.put(num, map.get(num) + 1);
      } else {
        map.put(num, 1);
      }
    }
    for (int num : map.keySet()) {
      if (map.get(num) == k) {
        return num;
      }
    }
    return -1;
  }

  //一个数组中，有一种数出现的次数是k,其他的数出现的次数是m, k<m
  public static int[] randomArray(int maxKinds, int range, int k, int m) {

    int ktimeNum = (int) (Math.random() * range);//最大值范围
    //生成多少种不同的数 必须是两种或两种以上

    //真命天子出现的次数
    int times = Math.random() < 0.5 ? k : ((int) (Math.random() * (m - 1)) + 1);

    int numKinds = (int) (Math.random() * maxKinds) + 2;
    //int[] arr = new int[k + (numKinds - 1) * m];
    int[] arr = new int[times + (numKinds - 1) * m];
    int index = 0;
    for (; index < times; index++) {
      arr[index] = ktimeNum;
    }
    numKinds--;
    HashSet<Integer> set = new HashSet<>();
    set.add(ktimeNum);
    while (numKinds != 0) {
      int curNum = 0;
      do {
        curNum = randomNumber(range);
      } while (set.contains(curNum));
      set.add(curNum);
      numKinds--;
      for (int i = 0; i < m; i++) {
        arr[index++] = curNum;
      }
    }
    //arr填好了
    //现在arr数组的中的数是有序的，现在打乱它
    for (int i = 0; i < arr.length; i++) {
      int j = (int) (Math.random() * arr.length);
      int tmp = arr[i];
      arr[i] = arr[j];
      arr[j] = tmp;
    }
    return arr;
  }

  //[-range,+range]
  public static int randomNumber(int range) {
    return (int) (Math.random() * range + 1) - (int) (Math.random() * range + 1);
  }


  /**
   * 一个数组中有两种数出现奇次，其他数都出现了偶次，怎么找到并打印这两种奇次数
   */
  public static void printOddTimesNum(int[] arr) {
    int eor = 0;
    //{2,2,2,1,4,4,4,4,5,5}
    for (int i = 0; i < arr.length; i++) {
      eor ^= arr[i];
    }
    //eor = 2^1 = 11
    int rightOne = eor & (~eor + 1);//11&00+1=01   1说明a和b在这一位一定不相等
    int onlyOne = 0;
    for (int i = 0; i < arr.length; i++) {//把a和b分开，分成两组
      if ((arr[i] & rightOne) != 0) {
        onlyOne ^= arr[i];
      }
    }
    //因为eor = a^b,onlyOne = a,所以eor^onlyOne = a^b^a=b
    System.out.println(onlyOne + " : " + (eor ^ onlyOne));
  }

  public static void printOddTimesNum2(int[] arr) {
    int eor = 0;
    for (int i = 0; i < arr.length; i++) {
      eor ^= arr[i];
    }
    //a和b不相同的最右位
    int rightOne = eor & (~eor + 1);
    int oddFirst = 0;
    for (int i = 0; i < arr.length; i++) {
      if ((rightOne & arr[i]) != 0) {
        oddFirst ^= arr[i];
      }
    }

    System.out.println(oddFirst + ":oddSecond" + (eor ^ oddFirst));
  }

  //请保证arr中，只有一种数出现了k次，其他数出现了M次
  public static int onlyKTimes(int[] arr, int k, int m) {
    int[] t = new int[32];
    //t[0] 0位置的1出现了几个
    //t[i] i位置的1出现了几个
    for (int num : arr) {
      for (int i = 0; i <= 31; i++) {
        t[i] += (num >> i) & 1;
      }
    }
    int ans = 0;
    for (int i = 0; i < 32; i++) {

      //System.out.println("这里开始");
      //如果在一个数组中，其他数都出现了m次，只有一种数出现了k次 返回出现k次的数；如果其他数都出现了m次，而另一个数没有出现k次，返回-1
      if (t[i] % m == 0) {
        continue;
      }
      //因为 k<m 所以求模等于k的
      if (t[i] % m == k) {
        //ans 0 .... 0  1右移i位
        //目的是找到出现k次的数在32位的int上1所在的位置，全部都找出来就是这个k的数
        ans |= (1 << i);
      } else {
        return -1;
      }
      //System.out.println("这里结束");
//
//      if (t[i] % m != 0) {//说明在第i位上有1
//        ans |= (1 << i);
//      }
    }
    if (ans == 0) {
      int count = 0;
      for (int num : arr) {
        if (num == 0) {
          count++;
        }
      }
      if (count != k) {
        return -1;
      }
    }
    return ans;
  }
}
