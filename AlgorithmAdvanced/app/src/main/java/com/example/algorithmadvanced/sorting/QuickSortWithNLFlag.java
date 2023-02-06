package com.example.algorithmadvanced.sorting;

import java.security.PublicKey;
import java.util.Stack;

public class QuickSortWithNLFlag {

  public static void main(String[] args) {

  }

  public static int[] netherlandsFlag(int[] arr, int L, int R) {
    if (L > R) {
      return new int[]{-1, -1};
    }
    if (L == R) {
      return new int[]{L, R};
    }
    int less = L - 1;
    int more = R;
    int index = L;
    while (index < more) {
      if (arr[index] == arr[R]) {
        index++;
      } else if (arr[index] < arr[R]) {
        swap(arr, index++, ++less);
      } else {
        swap(arr, index, --more);
      }
    }
    swap(arr, more, R);
    return new int[]{less + 1, more};
  }

  public static void quickSort3(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    process3(arr, 0, arr.length - 1);
  }

  private static void process3(int[] arr, int L, int R) {
    if (L >= R) {
      return;
    }
    //把选取的值换成数组中的随机一个数，也就是把最好情况和最坏情况(o(n^2))时间复杂度降至 o(N*logN)随机概率问题
    swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
    int[] equalArea = netherlandsFlag(arr, L, R);
    process3(arr, L, equalArea[0] - 1);
    process3(arr, equalArea[1] + 1, R);
  }

  //快排4.0非递归
  //要处理的是什么范围上的排序
  public static class Op {
    public int l;
    public int r;

    public Op(int left, int right) {
      l = left;
      r = right;
    }
  }

  public static void quickSort4(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    int N = arr.length;
    swap(arr, (int) (Math.random() * (N)), N - 1);
    int[] equalArea = netherlandsFlag(arr, 0, N - 1);
    int el = equalArea[0];
    int er = equalArea[1];
    Stack<Op> stack = new Stack<>();
    stack.push(new Op(0, el - 1));
    stack.push(new Op(er + 1, N - 1));
    while (!stack.isEmpty()) {
      Op op = stack.pop();
      if (op.l < op.r) {
        swap(arr, op.l + (int) (Math.random() * (op.r - op.l + 1)), op.r);
        equalArea = netherlandsFlag(arr, op.l, op.r);
        el = equalArea[0];
        er = equalArea[1];
        stack.push(new Op(op.l, el - 1));
        stack.push(new Op(er + 1, op.r));
      }
    }
  }

  //荷兰国旗递归
  public static void quickSort2(int[] arr) {
    if (arr == null || arr.length < 2) {
      return;
    }
    process2(arr, 0, arr.length - 1);
  }

  private static void process2(int[] arr, int L, int R) {
    if (L >= R) {
      return;
    }
    int[] equalArea = netherlandsFlag(arr, L, R);
    process2(arr, L, equalArea[0] - 1);
    process2(arr, equalArea[1] + 1, R);
  }

  //真正的随机快排

  public static void swap(int[] arr, int L, int R) {
    int tmp = arr[R];
    arr[R] = arr[L];
    arr[L] = tmp;
  }
}
