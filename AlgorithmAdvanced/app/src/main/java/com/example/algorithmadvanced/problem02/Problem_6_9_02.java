package com.example.algorithmadvanced.problem02;

import java.util.Arrays;
import java.util.HashMap;

import static java.util.Arrays.binarySearch;

public class Problem_6_9_02 {
  public static void main(String[] args) {
    /**
     * 一个无需数组长度n，所有数字都不一样，并且值都在[0...n-1]范围上
     * 整体变有序的最小交换次数
     *
     * 下标循环怼？？？ 形成环
     *
     * 如果数组：                arr[17,35,5]
     * 我们可以对数组进行离散化   arr[1,2,0]
     * 之后就可以像上面一样进行操作
     */

    int[] arr = {45,6,89};
    System.out.println("disperse:"+Arrays.toString(disperse(arr)));
    System.out.println("disperse:"+Arrays.toString(change(arr)));
  }

  public static int[] change(int[] arr){
    int[] copy = Arrays.copyOf(arr,arr.length);
    Arrays.sort(copy);
    HashMap<Integer,Integer> map = new HashMap<>();
    for (int i = 0; i < copy.length; i++) {
      map.put(copy[i],i);
    }
    for (int i = 0; i < arr.length; i++) {
      arr[i] = map.get(arr[i]);
    }
    return arr;
  }

  public static int[] disperse(int[] arr){
    if(arr==null||arr.length==0){
      return null;
    }
    int N = arr.length;
    int[] copy = new int[N];
    System.arraycopy(arr,0, copy, 0,copy.length);
    Arrays.sort(copy);
    for (int i = 0; i < arr.length; i++) {
      arr[i] = binarySearch(copy,0,N-1,arr[i]);
    }
    return arr;
  }

  public static int binarySearch(int[] arr, int L,int R,int num){
    while (L<=R){
      int mid = (R+L)/2;
      if(arr[mid]==num){
        return mid;
      }else if(arr[mid]<num){
        L=mid+1;
      }else {
        R=mid-1;
      }
    }
    return -1;
  }

  public static int minSwap2(int[] arr) {
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      while (i != arr[i]) {
        swap(arr, i, arr[i]);
        ans++;
      }
    }
    return ans;
  }

  private static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
