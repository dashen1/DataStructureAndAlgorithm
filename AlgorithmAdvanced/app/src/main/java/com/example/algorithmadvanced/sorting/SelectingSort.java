package com.example.algorithmadvanced.sorting;

import java.util.Arrays;

import javax.xml.transform.Source;

public class SelectingSort {

  public static void main(String[] args) {
    selectingSort1();
    int[] arr = {7, 1, 3, 5, 1, 6, 8, 1, 3, 5, 7, 5, 6};
    zuoSelectSort(arr);
  }

  public static void selectingSort1() {
    int[] arr = {5, 2, 33, 0, 34, 10};

    int index;
    for (int i = 0; i < arr.length - 1; i++) {
      index = i;

      for (int j = i + 1; j < arr.length; j++) {
        if (arr[j] < arr[index]) {
          index = j;
        }
      }
      if (arr[index] != arr[i]) {
        int tmp = arr[i];
        arr[i] = arr[index];
        arr[index] = tmp;
      }
    }
    System.out.println(Arrays.toString(arr));
  }

  public static void zuoSelectSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    //0~n-1
    //1~n-1
    int N = arr.length;
    for (int i = 0; i < N - 1; i++) {
      int minValueIndex = i;
      for (int j = i + 1; j < N; j++) {
        if (arr[minValueIndex] > arr[j]) {
          minValueIndex = j;
        }
      }
      if (arr[i] != arr[minValueIndex]) {
        swap(arr, i, minValueIndex);
      }
    }
    System.out.println(Arrays.toString(arr));
  }

  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[j];
    arr[j] = arr[i];
    arr[i] = tmp;
  }
}
