package com.example.algorithmadvanced.problem;

public class Problem_6_6_01 {

  public static void main(String[] args) {
    /**
     * Pow(x,n)
     * Sqrt(x)
     */
    System.out.println("负数：" + myPow(34.0, -3));
  }

  public static int pow(int a, int n) {
    int ans = 1;
    int t = a;
    while (n != 0) {
      if ((n & 1) != 0) {
        ans *= t;
      }
      t *= t;
      n >>= 1;
    }
    return ans;
  }

  public static double myPow(double x, int n) {
    if (n == 0) {
      return 1D;
    }
    //如果x值是系统最小值，先转为系统最大值计算
    int pow = Math.abs(n == Integer.MIN_VALUE ? n + 1 : n);
    double t = x;
    double ans = 1D;
    while (pow != 0) {
      if ((pow & 1) != 0) {
        ans *= t;
      }
      pow >>= 1;
      t = t * t;
    }
    if (n == Integer.MIN_VALUE) {
      ans *= x;
    }
    return n < 0 ? (1D / ans) : ans;
  }

  //开根号
  public static int mySqrt(int x) {
    if (x == 0) {
      return 0;
    }
    if (x < 3) {
      return 1;
    }
    long ans = 1;
    long L = 1;
    long R = x;
    long M = 0;
    while (L <= R) {
      M = (L + R) / 2;
      if (M * M <= x) {
        ans = M;
        L = M + 1;
      } else {
        R = M - 1;
      }
    }
    return (int) ans;
  }
}
