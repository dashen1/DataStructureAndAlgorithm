package com.example.algorithmadvanced.problem02;

public class Problem_6_8_04 {
  public static void main(String[] args) {
    /**
     * 一个子序列的消除规则如下：
     * 1）在某个子序列中，如果‘1’的左边有‘0’，那么这两个字符->“01”可以消除
     * 2）在某个子序列中，如果‘3’的左边有‘2’，那么这两个字符->“23”可以消除
     * 3）当这个子序列的某个部分消除之后，认为其他字符会自动贴在一起，可以继续寻找消除的机会
     * 比如，某个子序列“0231”，先消除掉“23”，那么剩下的字符贴在一起变成“01”，继续消除就没有字符了
     * 如果某个子序列通过最优良的方式，可以都消掉，那么这样的子序列叫做“全消子序列”
     * 一个只由'0','1','2','3'四种字符组成的字符串str,可以生成很多子序列，返回“全消子序列”的最大长度
     * 字符串str长度 <= 200
     */
  }

  public static int f(int[] str, int L, int R) {
    if (L == R) {
      return 0;
    }
    if (L == R - 1) {
      return (str[L] == '0' && str[R] == '1') || (str[L] == '2' && str[R] == '3') ? 2 : 0;
    }
    //L...R 有若干个字符 > 2
    // str[L...R]上，都能消掉的子序列，最长是多少？
    //可能性1，子序列完全不考虑str[L],最长是多少？
    int p1 = f(str, L + 1, R);
    if (str[L] == '1' || str[L] == '3') {
      return p1;
    }
    //可能性，能消掉的子序列必须考虑str[L],最长是多少？
    //str[L]=='0' 或者 '2'
    // '0'去找'1'
    // '2'去找'3'
    char find = str[L] == '0' ? '1' : '3';
    int p2 = 0;
    for (int i = L + 1; i <= R; i++) {
      //L(0)...i(1) i+1...R
      if (str[i] == find) {
        p2 = Math.max(p2, f(str,L + 1, i - 1) + 2 + f(str, i + 1, R));
      }
    }
    return Math.max(p1, p2);
  }

}
