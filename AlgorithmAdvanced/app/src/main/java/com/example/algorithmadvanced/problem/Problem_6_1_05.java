package com.example.algorithmadvanced.problem;

public class Problem_6_1_05 {

  public static void main(String[] args) {

    /**
     * google题
     * 面值为1~100的组成一组
     * 每次你从组里面等概率的抽搐1~10中一张
     * 下次抽会换一个新的组，有无限组
     * 当累加和<17时，你将一直抽牌
     * 当累加和>=17且<21时，你将获胜
     * 当累加和>=21时，你将失败
     * 返回获胜的概率
     */
  }

  public static double f1() {
    return p1(0);
  }

  private static double p1(int cur) {
    if (cur >= 17 && cur <= 21) {
      return 1.0;
    }
    if (cur >= 21) {
      return 0.0;
    }
    double w = 0.0;
    for (int i = 1; i <= 10; i++) {
      w += p1(cur + i);
    }
    return w / 10;
  }

  /**
   * 扩展版
   * 面值为1~N的组成一组
   * 每次你从组里面等概率的抽搐1~10中一张
   * 下次抽会换一个新的组，有无限组
   * 当累加和<a时，你将一直抽牌
   * 当累加和>=a且<b时，你将获胜
   * 当累加和>=b时，你将失败
   * 返回获胜的概率,给定参数为N,a,b
   */

  public static double f2(int N, int a, int b) {
    if (N < 1 || a >= b || a < 0 || b < 0) {
      return 0.0;
    }
    if (N <= (b - a)) {
      return 1.0;
    }
    //所有参数都合法
    return p2(0, N, a, b);
  }

  private static double p2(int cur, int N, int a, int b) {
    if (cur >= a && cur < b) {
      return 1.0;
    }
    if (cur >= b) {
      return 0.0;
    }
    double w = 0.0;
    for (int i = 1; i < N; i++) {
      w += p2(cur + i, N, a, b);
    }
    return w / N;
  }

  //f2改进版 用到了观察位置优化枚举的技巧
  public static double f3(int N,int a,int b){
    if(N < 1 || a >= b || a < 0){
      return 0.0;
    }
    if(b-a>=N){
      return 1.0;
    }
    return 0.0;
  }

}
