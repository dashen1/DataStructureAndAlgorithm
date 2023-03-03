package com.example.algorithmadvanced.sorting;

import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Stack;

import static com.example.algorithmadvanced.sorting.Random.copyArray;

public class QuickSorting {

  public static void main(String[] args) {

    int[] arr = {7, 1, 3, 5, 4, 5, 1, 4, 2, 2, 3};
    int[] arr2 = {7, 1, 3, 5, 4, 5, 1, 4, 2, 2, 3};
    splitNum2(arr);
    System.out.println(Arrays.toString(arr));

    System.out.println("快排左神");
    int testTimes = 500000;
    int maxSize = 100;
    int maxValue = 100;
    boolean succeed = true;
    System.out.println("test begin");
    quickSort(arr);
    System.out.println(Arrays.toString(arr));
    System.out.println("非递归实现");
    quickSort2(arr2);
    System.out.println(Arrays.toString(arr2));
    for (int i = 0; i < testTimes; i++) {
      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr3 = copyArray(arr1);
      quickSort(arr1);
      quickSort2(arr3);
      if (!isEqual(arr1, arr3)) {
        System.out.println();
        succeed = false;
        break;
      }
    }
    System.out.println(succeed ? "Nice!" : "Oops!");

    int a = 13;
    int b = 603;

    a = a^b;
    b = a^b;
    a = a^b;
    System.out.println("a:"+a);
    System.out.println("b:"+b);

  }


  public static void printOddTimesNum(int[] arr){
    int eor = 0;
    for (int i = 0; i < arr.length; i++) {
      eor ^= arr[i];
    }
    System.out.println(eor);
  }

  public static class Job {
    public int L;
    public int R;

    public Job(int l, int r) {
      L = l;
      R = r;
    }
  }

  public static void quickSort2(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    Stack<Job> stack = new Stack<>();
    stack.push(new Job(0, arr.length - 1));
    while (!stack.isEmpty()) {
      Job cur = stack.pop();
      int[] equals = partition(arr, cur.L, cur.R);
      if (equals[0] > cur.L) {
        stack.push(new Job(cur.L, equals[0] - 1));
      }
      if (equals[1] < cur.R) {
        stack.push(new Job(equals[1] + 1, cur.R));
      }
    }
  }

  private static boolean isEqual(int[] arr1, int[] arr3) {
    for (int i = 0; i < arr1.length; i++) {
      if (arr1[i] != arr3[i]) {
        return false;
      }
    }
    return true;
  }

  private static int[] generateRandomArray(int maxSize, int maxValue) {
    int size = (int) (Math.random() * maxSize);
    int[] arr = new int[size];
    for (int i = 0; i < size; i++) {
      arr[i] = (int) (Math.random() * maxValue);
    }
    return arr;
  }

  public static void quickSorting1() {
    int[] arr = {3, 21, 45, 6, 0, 55};
  }

  //在arr[L...R]范围上，拿arr[R]作划分值
  public static int[] partition(int[] arr, int L, int R) {
    // 把相同的数都放在中间
    int lessR = L - 1;
    int moreL = R;
    int index = L;// 当前数从L开始遍历
    while (index < moreL) {
      if (arr[index] < arr[R]) {
        swap(arr, ++lessR, index++);
      } else if (arr[index] > arr[R]) {
        swap(arr, --moreL, index);
      } else {
        index++;
      }
    }
    swap(arr, moreL, R);
    return new int[]{lessR + 1, moreL};
  }


  public static void quickSort(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    process(arr, 0, arr.length - 1);
  }

  private static void process(int[] arr, int L, int R) {
    if (L >= R) {
      return;
    }
    int[] equal = partition(arr, L, R);
    //equalE[0] 等于区域的第一个数
    //equalE[1] 等于区域的最后一个数
    process(arr, L, equal[0] - 1);
    process(arr, equal[1] + 1, R);
  }


  public static void splitNum2(int[] arr) {
    int N = arr.length;
    int lessR = -1;//小于右边的左区
    int moreL = N - 1;//大于左边的右区
    int index = 0;
    while (index < moreL) {
      if (arr[index] < arr[N - 1]) {//从左往右找比p小的值，找到就归为左区
        swap(arr, ++lessR, index++);
      } else if (arr[index] > arr[N - 1]) {//从左往右找到比p大的值，找到就归为右区
        swap(arr, --moreL, index);
      } else {
        index++;
      }
    }
    swap(arr, moreL, N - 1);
  }

  public static void splitNum(int[] arr) {
    int lessEqualR = -1;
    int index = 0;
    int mostR = arr.length - 1;
    while (index < arr.length) {
      if (arr[index] <= arr[mostR]) {
        swap(arr, ++lessEqualR, index++);
      } else {
        index++;
      }
    }
  }

  private static void swap(int[] arr, int index, int mostR) {
    int tmp = arr[mostR];
    arr[mostR] = arr[index];
    arr[index] = tmp;
  }


}
