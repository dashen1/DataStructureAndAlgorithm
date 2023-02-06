package com.example.algorithmadvanced.sorting;

import java.util.Arrays;

public class InsertSorting {

  public static void main(String[] args) {
    insertSorting1();
    System.out.println(";;");
    insertSorting2();

    random();
  }

  public static void insertSorting1() {
    int[] arr = {5, 3, 1, 5, 6, 1, 0, 11, 33};

    // 0~0 已经有序
    // 0~1
    // 0~2
    for (int i = 1; i < arr.length; i++) {
      int insertIndex = i;
      while (insertIndex - 1 >= 0 && arr[insertIndex - 1] > arr[insertIndex]) {
        swap(arr, insertIndex-1, insertIndex);
        insertIndex--;
      }
    }
    System.out.println(Arrays.toString(arr));
  }

  public static void insertSorting2() {
    int[] arr = {5, 3, 1, 5, 6, 1, 0, 11, 33};

    // 0~0 已经有序
    // 0~1
    // 0~2
    for (int i = 1; i < arr.length; i++) {
      for (int pre = i-1; pre >=0&&arr[pre]>arr[pre+1]; pre--) {
        swap(arr, pre, pre+1);
      }
    }
    System.out.println(Arrays.toString(arr));
  }

  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  public static void random(){
    //[0,1)左闭右开等概率
    int testTimes = 100000000;
    int count = 0;
    for (int i = 0; i < testTimes; i++) {
      if(Math.random()<0.3){
        count++;
      }
    }

    System.out.println("count:"+(double)count/(double)testTimes);

    int k=9;
    //[0,k) -> [0,8]
    int[] counts = new int[9];
    for (int i = 0; i < testTimes; i++) {
      int ans = (int) (Math.random()*k);
      counts[ans]++;
    }

    double x = 0.17;
    for (int i = 0; i < testTimes; i++) {

    }
  }

  public static double xToXPower2(){
    return Math.max(Math.random(), Math.random());
  }
}
