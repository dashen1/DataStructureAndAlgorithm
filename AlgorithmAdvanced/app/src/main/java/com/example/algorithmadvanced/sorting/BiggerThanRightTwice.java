package com.example.algorithmadvanced.sorting;

public class BiggerThanRightTwice {

  public static void main(String[] args) {

    int maxSize = 50;
    int maxValue = 200;
    int testTimes = 1000000;
    System.out.println("测试开始==");
    for (int i = 0; i < testTimes; i++) {
      int[] arr1 = generateRandomArray(maxSize, maxValue);
      int[] arr2 = copyArray(arr1);
      //System.out.println("arr:"+ Arrays.toString(arr1));
      int ans1 = sum(arr1);
      int ans2 = comparator(arr2);
      if(ans1!=ans2){
        System.out.println("ans1:"+ans1);
        System.out.println("ans2:"+ans2);
        System.out.println("测试错误！");
        break;
      }
    }
    System.out.println("测试成功！");
  }

  public static int comparator(int[] arr) {
    int ans = 0;
    for (int i = 0; i < arr.length - 1; i++) {
      for (int j = i + 1; j < arr.length; j++) {
        if (arr[i] > arr[j] * 2) {
          ans++;
        }
      }
    }
    return ans;
  }

  private static int[] generateRandomArray(int maxSize, int maxValue) {
    int arrSize = (int) (Math.random() * maxSize);
    int[] arr = new int[arrSize];
    for (int i = 0; i < arrSize; i++) {
      arr[i] = (int) (Math.random() * (maxValue + 1)) - (int) (Math.random() * maxValue);
    }
    return arr;
  }

  public static int[] copyArray(int[] arr) {
    int[] newArr = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      newArr[i] = arr[i];
    }
    return newArr;
  }

  public static int sum(int[] arr) {
    if (arr == null || arr.length == 0) {
      return 0;
    }
    return process(arr, 0, arr.length - 1);
  }

  public static int process(int[] arr, int L, int r) {
    if (L == r) {
      return 0;
    }
    int mid = L + (r - L) / 2;
    return process(arr, L, mid) + process(arr, mid + 1, r) + merge(arr, L, mid, r);
  }

  public static int merge(int[] arr, int L, int mid, int r) {
    int ans = 0;
    //目前囊括进来的数 是从[m+1,windowR)
    int windowR = mid + 1;
    for (int i = L; i <= mid; i++) {
      while (windowR <= r && arr[i] > (arr[windowR] * 2)) {
        windowR++;
      }
      //3 3 5 6, 1 2 3 4 比如左边的5和右边比较，windowR只能到右边的2  也就是6-3-1=2，右边符合要求的只有一个数
      ans += windowR - mid - 1;
    }

    int[] help = new int[r - L + 1];
    int i = 0;
    int p1 = L;
    int p2 = mid + 1;
    while (p1 <= mid && p2 <= r) {
      help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
    }
    while (p1 <= mid) {
      help[i++] = arr[p1++];
    }
    while (p2 <= r) {
      help[i++] = arr[p2++];
    }

    for (int j = 0; j < help.length; j++) {
      arr[L + j] = help[j];
    }
    return ans;
  }
}
