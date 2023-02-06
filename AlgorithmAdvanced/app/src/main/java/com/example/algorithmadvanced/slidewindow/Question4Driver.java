package com.example.algorithmadvanced.slidewindow;

import java.util.Arrays;

public class Question4Driver {

  public static void main(String[] args) {
    /**
     * 现有司机N*2人，调度中心会将所有司机平分给A,B两个区域
     * 第i个司机去A可得收入为income[i][0]
     * 第i个司机去B可得收入为income[i][1]
     * 返回所有调度方案中能使所有司机总收入最高的方案，是多少钱？
     */

    System.out.println("向上取整：" + Math.ceil(4.5));
    int[] arr = {1, 1, 1, 1, 1, 2, 3, 5, 5, 5, 5, 6, 6, 8, 8, 9};
    int num = minBoat(arr, 10);
    System.out.println("最小船数：" + num);

    String str = "(())((()";
    String s = new StringBuilder(str).reverse().toString();
    System.out.println(s);
  }

  //income -> N*2的矩阵 N是偶数 司机一定是偶数才能平分
  public static int maxMoney(int[][] income) {
    if (income == null || income.length < 2 || (income.length & 1) != 0) {
      return 0;
    }
    int N = income.length;
    int M = N >> 1;

    return 0;
  }

  //index 所有司机，往A区和B区域分配
  //A区域还有多少rest个名额
  public static int process(int[][] income, int index, int rest) {
    if (index == income.length) {
      return 0;
    }
    //还剩下司机 B区域满了，只能往A区域放
    if (income.length - index == rest) {
      return income[index][0] + process(income, index + 1, rest - 1);
    }
    //A区域没有名额了，只能去B区域
    if (rest == 0) {
      return income[index][1] + process(income, index, rest);
    }
    //当去司机可以去A区域，也可以去B区域
    int p1 = income[index][0] + process(income, index + 1, rest - 1);
    int p2 = income[index][1] + process(income, index + 1, rest);
    return Math.max(p1, p2);
  }

  //最长无重复字符串的子串长度
  //解决思路：
  // 1）当前字符上次的位置
  // 2）i-1位置往左推的距离
  public static int lengthOfLongestSubstring(String str) {
    if (str == null || str.equals("")) {
      return 0;
    }

    char[] chars = str.toCharArray();
    //0~255
    //map[i] = v i这个ascii码的字符，上次出现v的位置
    int[] map = new int[256];
    for (int i = 0; i < 256; i++) {
      map[i] = -1;
    }
    int N = chars.length;
    int ans = 1;
    int pre = 1;
    map[chars[0]] = 0;
    for (int i = 1; i < N; i++) {
      int p1 = i - map[chars[i]];
      int p2 = pre + 1;
      int cur = Math.min(p1, p2);
      ans = Math.max(ans, cur);
      pre = cur;
      map[chars[i]] = i;
    }
    return ans;
  }


  public static int maxPairNum2(int[] arr, int k) {
    if (k < 0 || arr == null || arr.length < 2) {
      return 0;
    }
    //排序保持单调性
    Arrays.sort(arr);
    int ans = 0;
    int L = 0;
    int R = 0;
    int N = arr.length;
    boolean[] usedR = new boolean[N];
    while (L < N && R < N) {
      if (usedR[L]) {
        L++;
      } else if (L >= R) {
        R++;
      } else {//否则不止一个数，而且都没有用过
        int distance = arr[R] - arr[L];
        if (distance == k) {
          ans++;
          usedR[R++] = true;
          L++;
        } else if (distance < k) {
          R++;
        } else {
          L++;
        }
      }
    }
    return ans;
  }

  /**
   * 给定一个正数数组arr，代表若干人的体重
   * 再给定一个正数limit,表示所有船共同拥有的载重量
   * 每艘船最多坐两人，且不能超过载重
   * 向让所有人同时过河，并且用最好的分配方法让船尽量少
   * 返回最少的传数
   */
  public static int minBoat(int[] arr, int limit) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int N = arr.length;
    Arrays.sort(arr);
    if (arr[N - 1] > limit) {
      return -1;
    }
    int lessR = -1;
    for (int i = N - 1; i >= 0; i--) {
      if (arr[i] <= (limit / 2)) {//找到limit/2的下标位置
        lessR = i;
        break;
      }
    }
    if (lessR == -1) {
      return N;
    }
    int indexL = lessR;
    int indexR = lessR + 1;
    while (indexL >= 0 && indexR < N - 1) {
      int sum = arr[indexL] + arr[indexR];
      if (sum <= limit) {
        indexR++;
      } else {
        indexL -= indexR - lessR;
      }
    }
    int rightNum = indexR - lessR;
    int leftNum = lessR - rightNum + 1;
    return rightNum + (int) Math.ceil(leftNum / 2.0) + N - indexR - 1;
  }

  //返回一个数组中，子数组最大累加和
  public static int maxSubArray(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int max = Integer.MIN_VALUE;
    int cur = 0;
    for (int i = 0; i < arr.length; i++) {
      cur += arr[i];
      max = Math.max(max, cur);
      cur = Math.max(cur, 0);//负数不要，正数才要
    }
    return max;
  }

  public static int maxSubArray2(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int pre = arr[0];
    int max = arr[0];
    for (int i = 1; i < arr.length; i++) {
      pre = Math.max(arr[i], arr[i] + pre);
      max = Math.max(max, pre);
    }
    return max;
  }

  //糖果分配问题
  public static int candy1(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    int N = arr.length;
    int[] left = new int[N];
    for (int i = 1; i < N; i++) {
      if (arr[i - 1] < arr[i]) {
        left[i] = left[i - 1] + 1;
      }
    }
    int[] right = new int[N];
    for (int i = N - 2; i >= 0; i--) {
      if (arr[i] > arr[i + 1]) {
        right[i] = right[i - 1] + 1;
      }
    }
    int ans = 0;
    for (int i = 0; i < N; i++) {
      ans += Math.max(left[i], right[i]);
    }
    return ans + N;
  }

  //升级：相邻孩子分数一样，糖数也得一样
  //1）比左边大 ++
  //2）和左边相等 ==
  //3）比左边小 变回1

  /**
   * 字符串交错组成
   * str1: ab123de 7个
   * str2: fts67k  6个
   * 总：  ftab12s367kde
   * 样本对应模型
   */

  /**
   * 求相等子树的数量
   */

  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
      this.value = value;
    }
  }

  //时间复杂度O(N*logN)
  public static int sameNumber1(Node head) {
    if (head == null) {
      return 0;
    }
    return sameNumber1(head.left) + sameNumber1(head.right) + (same(head.left, head.right) == true ? 1 : 0);
  }

  private static boolean same(Node h1, Node h2) {
    //h1和h2只有一个为null
    if (h1 == null ^ h2 == null) {
      return false;
    }
    if (h1 == null && h2 == null) {
      return true;
    }
    return h1.value == h2.value && same(h1.left, h2.left) && same(h1.right, h2.right);
  }

  //时间复杂度 O(N)
  public static int sameNumber2(Node head) {
    String algorithm = "SHA-256";
//    Hash hash = new Hash(algorithm);
//    return process(head, hash).ans;
    return 0;
  }

  public static class Info {
    public int ans;
    public String str;

    public Info(int ans, String str) {
      this.ans = ans;
      this.str = str;
    }
  }

//  public static Info process(Node head, Hash hash) {
//    if (head == null) {
//      return new Info(0, hash.hashCode("#,"));
//    }
//    Info l = process(head.left, hash);
//    Info r = process(head.right, hash);
//    int ans = (l.str.equals(r.str) ? 1 : 0) + l.ans + r.ans;
//    String str = hash.hashCode(head.value + "," + l.str + r.str);
//    return new Info(ans, str);
//  }

  //最小编辑距离 str1 编辑成 str2 【样本对应模型】
  //ab1c -> abc
  //1)保留 0
  //2)删除 10
  //3)添加 5
  //4)替换 30

  /**
   * @param s1
   * @param s2
   * @param ic 添加cost
   * @param dc 删除cost
   * @param rc 替换cost
   * @return 四中可能情况
   * 1）str1 -> absbbs str2 -> dbsbb
   * str1长度为6，str2长度为5，str1[i-1]先变成str2,然后再删除最后一个
   * dp[i-1][j]+d(删除)
   * <p>
   * 2）str1 -> absbb str2 -> dbsbbs
   * str1长度为5，str2长度为6，str1先变成str2[j-1],然后在末尾添加一个
   * dp[i][j-1]+a(添加)
   * <p>
   * 3）如果str1[i-1] == str2[j-1],那么 dp[i-1][j-1]
   * <p>
   * 4）如果str1[i-1] != str2[j-1],那么 dp[i-1][j-1]+r(替换)
   */
  public static int minCost(String s1, String s2, int ic, int dc, int rc) {
    {
      if (s1 == null || s2 == null) {
        return 0;
      }
      char[] str1 = s1.toCharArray();
      char[] str2 = s2.toCharArray();
      int N = str1.length + 1;
      int M = str2.length + 1;
      int[][] dp = new int[N][M];
      for (int i = 1; i < N; i++) {
        //先把第一列填完
        dp[i][0] = dc * i;
      }
      for (int j = 1; j < M; j++) {
        dp[0][j] = ic * j;
      }
      for (int i = 1; i < N; i++) {
        for (int j = 1; j < M; j++) {
          if (str1[i - 1] == str2[j - 1]) {
            dp[i][j] = dp[i - 1][j - 1];
          } else {
            dp[i][j] = dp[i - 1][j - 1] + rc;
          }
          dp[i][j] = Math.min(dp[i][j], dp[i][j - 1] + ic);
          dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + dc);
        }
      }
      return dp[N-1][M-1];
    }
  }
}
