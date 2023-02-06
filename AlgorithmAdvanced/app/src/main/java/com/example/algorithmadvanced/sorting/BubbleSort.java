package com.example.algorithmadvanced.sorting;

import java.util.Arrays;

public class BubbleSort {

  public static void main(String[] args) {
    bubbleSorting1();
    bubbleSorting2();
  }

  public static void bubbleSorting1() {
    int[] arr = {5, 1, 7, 10, 13, 22};

    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = 0; j < arr.length - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int tmp = arr[j + 1];
          arr[j + 1] = arr[j];
          arr[j] = tmp;
        }
      }
      System.out.println("1 i:"+i);
    }

    System.out.println(Arrays.toString(arr));
  }

  public static void bubbleSorting2() {
    int[] arr = {5, 6, 3, 11, 22, 23};

    for (int i = 0; i < arr.length - 1; i++) {
      boolean isOrdering = true;
      for (int j = 0; j < arr.length - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          isOrdering = false;
          int temp = arr[j + 1];
          arr[j + 1] = arr[j];
          arr[j] = temp;
        }
      }
      System.out.println("2 i:"+i);
      if (isOrdering) {
        break;
      }
    }
    System.out.println(Arrays.toString(arr));
  }
}
