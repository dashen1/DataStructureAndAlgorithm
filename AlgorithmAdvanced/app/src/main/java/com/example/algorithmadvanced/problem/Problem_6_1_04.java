package com.example.algorithmadvanced.problem;

public class Problem_6_1_04 {

  public static void main(String[] args) {

  }

  public static int countEval0(String express, int desired) {
    if (express == null || express.equals("")) {
      return 0;
    }
    char[] exp = express.toCharArray();
    int N = exp.length;
    Info[][] dp = new Info[N][N];
    Info allInfo = func(exp, 0, exp.length - 1, dp);
    return desired == 1 ? allInfo.t : allInfo.f;
  }

  public static class Info {
    public int t;
    public int f;

    public Info(int t, int f) {
      this.t = t;
      this.f = f;
    }
  }

  //限制：
  //L...R上，一定有奇数个字符
  //L位置的字符和R位置的字符，非0即1，不能是逻辑符号
  //返回str[L...R]这一段，为true的方法数，和false的方法数
  public static Info func(char[] str, int L, int R, Info[][] dp) {
    if (dp[L][R] != null) {
      return dp[L][R];
    }
    int t = 0;
    int f = 0;
    if (L == R) {
      t = str[L] == '1' ? '1' : '0';
      f = str[L] == '0' ? '1' : '0';
    } else {//L...R>=3
      //每一种逻辑符号
      for (int split = L + 1; split < R; split += 2) {
        Info leftInfo = func(str, L, split - 1, dp);
        Info rightInfo = func(str, split + 1, R, dp);
        int a = leftInfo.t;
        int b = leftInfo.f;
        int c = rightInfo.t;
        int d = rightInfo.f;
        switch (str[split]) {
          case '&':
            t += a * c;
            f += b * c + b * d + a * d;
            break;
          case '|':
            t += a * c + a * d + b * c;
            f += b * d;
            break;
          case '^':
            t += a * d + b * c;
            f += a * c + b * d;
            break;
        }
      }
    }
    dp[L][R] = new Info(t, f);
    return dp[L][R];
  }


  //改动态记忆化搜索
}
