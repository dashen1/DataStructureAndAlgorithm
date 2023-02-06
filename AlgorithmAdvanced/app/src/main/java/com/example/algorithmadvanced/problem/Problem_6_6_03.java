package com.example.algorithmadvanced.problem;

public class Problem_6_6_03 {

  public static void main(String[] args) {
    /**
     * 乘积最大子数组
     */
  }

  public static double max(double[] arr){
    if(arr==null||arr.length==0){
      return 0;
    }
    int N = arr.length;
    double[] dpMax = new double[N];
    double[] dpMin = new double[N];
    double ans = arr[0];
    dpMax[0] = arr[0];
    dpMin[0] = arr[0];
    for (int i = 1; i < N; i++) {
      double p1 = arr[i];
      double p2 = arr[i]*dpMax[i-1];
      double p3 = arr[i]*dpMin[i-1];
      dpMax[i] = Math.max(Math.max(p1,p2),p3);
      dpMin[i] = Math.min(Math.min(p1,p2),p3);
      ans = Math.max(ans, dpMax[i]);
    }
    return ans;
  }

  public static double max2(double[] arr){
    if(arr==null||arr.length==0){
      return 0;
    }
    int N = arr.length;
    //上一步最大
    double premax = arr[0];
    //上一步最小
    double premin = arr[0];
    double ans = arr[0];
    for (int i = 1; i < arr.length; i++) {
      double p1 = arr[i];
      double p2 = arr[i]*premax;
      double p3 = arr[i]*premin;
      double curmax = Math.max(Math.max(p1,p2),p3);
      double curmin = Math.min(Math.min(p1,p2),p3);
      ans = Math.max(ans,curmax);
      premax = curmax;
      premin = curmin;
    }
    return ans;
  }
}
