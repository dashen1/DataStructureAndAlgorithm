package com.example.algorithmadvanced.problem;

public class Problem_6_6_04 {
  public static void main(String[] args) {
    /**
     * 阶乘后的零 (本质上是求有多少个5的因子)
     * 1*2*3*4*5*6*7*...n!
     *
     * 1）n/5
     * 2)n/25
     * 3)n/125
     */

    System.out.println("ans:" + reverseBit(10));
  }

  public static int trailingZeroes(int n) {
    int ans = 0;
    while (n != 0) {
      n /= 5;
      ans += n;
    }
    return ans;
  }

  /**
   * 颠倒二进制位
   */

  public static int reverseBit(int n) {
    //将高位和低位交换
    n = (n >>> 16) | (n << 16);

    n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
    n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
    n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
    n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
    return n;
  }

  /**
   * 计数质数(素数)
   * 除了1和自己之外没有其它因子，1不是
   */
  public static int countPrimes(int n) {
    if (n < 3) {
      return 0;
    }
    boolean[] f = new boolean[n];
    //所有偶数都不要，还剩几个数
    int count = n / 2;
    for (int i = 3; i * i < n; i++) {
      if (f[i]) {
        continue;
      }
      for (int j = 0; j < n; j += 2 * i) {
        if (!f[j]) {
          --count;
          f[j] = true;
        }
      }
    }
    return count;
  }
}
