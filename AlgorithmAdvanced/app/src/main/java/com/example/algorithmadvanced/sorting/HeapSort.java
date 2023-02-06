package com.example.algorithmadvanced.sorting;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapSort {

  @RequiresApi(api = Build.VERSION_CODES.N)
  public static void main(String[] args) {
    //默认是小根堆
    //PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
    PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
    heap.add(5);
    heap.add(3);
    //改成大根堆 需添加比较器
    heap.add(7);
    heap.add(0);
    while (!heap.isEmpty()) {
      System.out.println(heap.poll());
    }

    int[] arr = {3, 4, 1, 0, 77, 22};
    heapSort1(arr);
    System.out.println(Arrays.toString(arr));
  }


  public static class MyComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer o1, Integer o2) {
      return o2 - o1;
    }
  }

  public static void heapSort1(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }

    //指令运行时间 10^8 c/c++ 1~2s, java 2~4s 估计算法时间复杂度
    //O(N*logN) 调整到大根堆方式一
    for (int i = 0; i < arr.length; i++) {
      heapInsert1(arr, i);
    }
    //O(N) 从下往上 调整大根堆方式二
//    for (int i = arr.length - 1; i >= 0; i--) {
//      heapify1(arr, i, arr.length);
//    }
    int heapSize = arr.length;
    swap(arr, 0, --heapSize);//先交换后下沉
    while (heapSize > 0) {
      heapify1(arr, 0, heapSize);
      swap(arr, 0, --heapSize);
    }
  }

  public static void heapInsert1(int[] arr, int index) {
    while (arr[index] > arr[(index - 1) / 2]) {
      swap(arr, index, (index - 1) / 2);
      index = (index - 1) / 2;
    }
  }

  //不断下沉堆 每次把第一个位置的数放到最后一个位置的数，最后一个位置的数放到第一个位置的数的时候需要比较下沉维护大小堆
  public static void heapify1(int[] arr, int index, int heapSize) {
    int left = index * 2 + 1;
    while (left < heapSize) {//有左孩子
      int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;//判断有没有右孩子
      largest = arr[largest] > arr[index] ? largest : index;//得到较大孩子的下标
      if (largest == index) {
        break;
      }
      swap(arr, largest, index);
      index = largest;
      left = index * 2 + 1;
    }
  }

  public static void swap(int[] arr, int L, int R) {
    int tmp = arr[R];
    arr[R] = arr[L];
    arr[L] = tmp;
  }


}
