package com.example.algorithmadvanced.slidewindow;

import java.util.HashMap;
import java.util.TreeSet;

public class SlideWindow {

  public static void main(String[] args) {
    /**
     * 给定一个有序数组arr，代表坐落在X轴上的点
     * 给定一个正数K,代表绳子的长度
     * 返回最多压中几个点？
     * 即使绳子边缘盖住点也算盖住
     */

    String str = "BBGGBBGG";
    int step = minStep1(str);
    System.out.println("step1:" + step);
    System.out.println("step2:" + minStep2(str));
  }

  //使用二分法 时间复杂度 O(N*logN)
  public static int maxPoint1(int[] arr, int K) {
    int res = 1;
    for (int i = 0; i < arr.length; i++) {
      int nearest = nearestIndex(arr, i, arr[i] - K);
      res = Math.max(res, i - nearest + 1);
    }
    return res;
  }

  public static int nearestIndex(int[] arr, int R, int value) {
    int L = 0;
    int index = R;
    while (L <= R) {
      int mid = L + ((R - L) >> 1);
      if (arr[mid] <= value) {
        index = mid;
        R = mid - 1;
      } else {
        L = mid + 1;
      }
    }
    return index;
  }

  //使用滑动窗口 时间复杂度 O(N)
  public static int maxPoint2(int[] arr, int L) {
    int left = 0;
    int right = 0;
    int N = arr.length;
    int max = 0;
    while (left < N) {
      while (right < N && arr[right] - arr[left] <= L) {
        right++;
      }
      max = Math.max(max, right - (left++));
    }
    return max;
  }

  /**
   * 一个数组中只有两种字符‘G'和’B',
   * 想让所有的G都放在左侧，所有的B都放在右侧
   * 但是只能在相邻字符之间进行交换操作，
   * 返回至少需要交换几次？
   */
  //注意只能相邻交换 例如：BBA,A需要交换两次才能变成ABB
  public static int minStep1(String s) {
    if (s == null || s.equals("")) {
      return 0;
    }
    char[] str = s.toCharArray();
    int step1 = 0;
    int gi = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] == 'G') {
        step1 += i - (gi++);
      }
    }
    int step2 = 0;
    int bi = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] == 'B') {
        step2 += i - (bi++);
      }
    }
    return Math.min(step1, step2);
  }

  public static int minStep2(String s) {
    if (s == null || s.equals("")) {
      return 0;
    }
    char[] str = s.toCharArray();
    int step1 = 0;
    int step2 = 0;
    int gi = 0;
    int bi = 0;
    for (int i = 0; i < str.length; i++) {
      if (str[i] == 'G') {
        step1 += i - (gi++);
      } else {
        step2 += i - (bi++);
      }
    }
    return Math.min(step1, step2);
  }

  /**
   * 给定一个数组arr，你可以在每个数字之前决定+或者-
   * 但是必须所有数字都参与
   * 在给定一个数target，请问最后算出target的方法数是多少？
   * 1 +(-) 2+(-) 3+(-) = target方法数
   */
  public static int findTargetSumWays1(int[] arr, int s) {
    return process1(arr, 0, s);
  }

  private static int process1(int[] arr, int index, int rest) {
    if (index == arr.length) {
      return rest == 0 ? 1 : 0;
    }
    return process1(arr, index + 1, rest - arr[index]) + process1(arr, index + 1, rest + arr[index]);
  }

  //优化 记忆化搜索
  public static int process2(int[] arr, int index, int rest, HashMap<Integer, HashMap<Integer, Integer>> dp) {
    if (dp.containsKey(index) && dp.get(index).containsKey(rest)) {
      return dp.get(index).get(rest);
    }
    int ans = 0;
    if (index == arr.length) {
      ans = rest == 0 ? 1 : 0;
    } else {
      ans = process2(arr, index + 1, rest - arr[index], dp) + process2(arr, index + 1, rest + arr[index], dp);
    }
    if (!dp.containsKey(index)) {
      dp.put(index, new HashMap<>());
    }
    dp.get(index).put(rest, ans);
    return ans;
  }

  /**
   * arr[1,3,-4,5,-6,7]
   * 继续优化
   * 优化点一：
   * 你可以认为arr中都是非负数
   * 因为即便是arr中有负数，比如[3,-4,2]
   * 因为你能够在每个数前面用+或者-号
   * 所以[3,-4,2]其实和[3,4,2]达到的效果是一样的
   * 那么我们就可以全吧arr变成非负数，不会影响结果
   * 优化点二：
   * 如果arr都是非负数，并且所有书的累加和都是sum
   * 那么如果target<sum，很明显没有任何方法可以达到target，可以直接返回0 【这个有疑惑？】
   * <p>
   * 优化点三：
   * 因为题目要求一定要使用所有数字去拼target，
   * 所以不管这些数字怎么用+和-折腾，最终的结果都一定不会改变奇偶性
   * 所以，如果所有数的累加和是sum
   * 并且与target的奇偶性不一致，没有任何方法可以达到target，可以直接返回0
   * 优化点四：
   * 比如说给定一个数组，arr = [1,2,3,4,5],并且target = 3
   * 其中一个方案是：+1 -2 +3 -4 +5 = 3
   * 该方案中取了正数的集合为 P = {1,3,5}
   * 该方案中取了负数的集合为 N = {-2，-4}
   * 所以任何一种方案，一定有 sum(P)-sum(N) = target
   * 现在我们来处理一下这个等式，把左右两边都加上sum(P)+sum(N),那么就会变成如下：
   * sum(P)-sum(N)+sum(P)+sum(N) = target+sum(P)+sum(N)
   * 2 * sum(P) = target+sum()数组所有数的累加和
   * sum(P) = (target+数组所有数的累加和) / 2
   * 也就是说，任何一个集合，只要累加和是 （target+数组所有数的累加和) / 2
   * 那么就一定对应一种target的方式
   * 也就是说，比如非负数组arr，target，而所有数累加和是11
   * 求所有所有数字的情况下，多少方法最后形成7？
   * 其实就是求有多少个子集的累加和是9 -> (7+11) / 2
   * 优化点五：
   * 二维动态规划的空间压缩技巧
   */
  //arr[]非负
  public static int findTargetSumWays3(int[] arr, int target) {
    int sum = 0;
    for (int n : arr) {
      sum += n;
    }
    //^异或运算 不同为1（1^0=1,0^1=1） &与运算 相同为1
    return sum < target || ((target & 1) ^ (sum & 1)) != 0 ? 0 : subset(arr, (target + sum) >> 2);
  }

  //经典背包问题
  private static int subset(int[] arr, int s) {
    int[] dp = new int[s + 1];
    dp[0] = 1;
    for (int n : arr) {
      for (int i = s; i >= 0; i--) {
        dp[i] += dp[i - n];
      }
    }
    return dp[s];
  }
}
