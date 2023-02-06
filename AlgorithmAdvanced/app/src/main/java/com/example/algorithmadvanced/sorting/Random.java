package com.example.algorithmadvanced.sorting;

public class Random {

  public static void main(String[] args) {

    int[] counts = new int[8];
    int testTimes = 10000000;
    for (int i = 0; i < testTimes; i++) {
      int num = f3();
      counts[num]++;
    }

    for (int i = 0; i < 8; i++) {
      System.out.println(i + "这个数，出现了 " + counts[i] + " 次");
    }

    System.out.println("========================");

    int maxLen = 50;
    int maxValue = 1000;
    testTimes = 10000;
    //验证排序是否出错
    for (int i = 0; i < testTimes; i++) {
      int[] arr1 = lenRandomValueRandom(maxLen,maxValue);
      int[] tmp = copyArray(arr1);
//      selectionSort(arr1);
//      insertSort(arr1);
      if (isSorted(arr1)){
        System.out.println("排序错了！");
      }
    }

  }

  public static boolean isSorted(int[] arr){
    if(arr.length<2){
      return true;
    }
    int max = arr[0];
    for (int i = 0; i < arr.length; i++) {
      if(max>arr[i]){
        return false;
      }
      max = Math.max(max, arr[i]);
    }
    return true;
  }

  public static int f1() {
    return (int) (Math.random() * 5 + 1);//[1,5]
  }

  public static int f2() {
    int ans = 0;
    do {
      ans = f1();
    } while (ans == 3);

    return ans < 3 ? 0 : 1;
  }

  //得到000~到111做到等概率
  public static int f3() {
    return (f2() << 2) + (f2() << 1) + f2();
  }

  //做到0~6等概率返回
  public static int f4() {
    int ans = 0;
    do {
      ans = f3();
    } while (ans == 7);
    return ans;
  }

  //返回0~7
  public static int g() {
    return f4() + 1;
  }

  public static int x() {
    return Math.random() < 0.84 ? 0 : 1;
  }

  //等概率返回0和1
  public static int y() {
    int ans = 0;
    do {
      ans = x();
    } while (ans == x());//只要01和10
    //ans=0 1
    //ans=1 0
    return ans;
  }

  public static int[] lenRandomValueRandom(int maxLen,int maxValue){
    int len = (int) (Math.random()*maxLen);
    int[] ans = new int[len];
    for (int i = 0; i < len; i++) {
      ans[i] = (int) (Math.random()*maxValue);
    }
    return ans;
  }

  public static int[] copyArray(int[] arr){
    int[] ans = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      ans[i] = arr[i];
    }
    return ans;
  }

  public static boolean equalValues(int[] arr1, int[] arr2){
    for (int i = 0; i < arr1.length; i++) {
      if(arr1[i] !=arr2[i]){
        return false;
      }
    }
    return true;
  }
}
