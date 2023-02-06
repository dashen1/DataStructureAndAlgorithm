package com.example.algorithmadvanced.sorting;

public class QuickSortTest {
  public static void main(String[] args) {

  }

  //O(N*logN) 常数项高
  public static void quick(int[] arr, int L,int R){
    if(L==R){
      return;
    }
    //荷兰国旗
    int[] p = partition(arr, L,R);
    quick(arr, L,p[0]-1);
    quick(arr,p[1]+1,R);
  }

  private static int[] partition(int[] arr, int l, int r) {
    return new int[0];
  }

  private static void merge(int[] arr, int L, int mid, int R) {

  }
}
