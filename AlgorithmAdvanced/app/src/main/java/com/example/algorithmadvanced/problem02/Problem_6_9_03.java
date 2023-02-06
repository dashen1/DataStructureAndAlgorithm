package com.example.algorithmadvanced.problem02;

import java.util.Arrays;

public class Problem_6_9_03 {
  public static void main(String[] args) {
    /**
     *返回全排列的下一排列
     * [1,2,3]全排列有：
     * [1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]
     * 如；nums = [1,2,3]
     *  output: [1,3,2]
     *  nums = [3,2,1]
     *  output:[1,2,3]
     *
     *  思路：比如
     *   4 76321
     *   从右往左找到降序的点，也就是7，4的左边不变，从4的有序序列中找到比4大的第一个数，也就是6,4和6交换，6 74321，之后74321逆序，6 12347即为下一个排列
     */

    int[] arr = {1, 2, 3, 4, 5, 7};
    reverse(arr, 0, 5);
    System.out.println("reverse:" + Arrays.toString(arr));
  }

  public static void nextPermutation(int[] nums) {
    int N = nums.length;
    int firstLess = -1;
    for (int i = N - 2; i >= 0; i--) {
      if (nums[i] < nums[i + 1]) {
        firstLess = i;
        break;
      }
    }
    if (firstLess < 0) {
      reverse(nums, 0, N - 1);
    } else {
      int rightClosestMore = -1;
      for (int i = N - 1; i > firstLess; i--) {
        if (nums[i] > nums[firstLess]) {
          rightClosestMore = i;
          break;
        }
      }
      swap(nums, firstLess, rightClosestMore);
      reverse(nums, firstLess + 1, N - 1);
    }
  }

  private static void reverse(int[] arr, int i, int j) {
    int N = j - i + 1;
    int L = 0;
    int R = 0;
    if (N % 2 == 0) {
      L = (i + j) / 2;
      R = L + 1;
    } else {
      L = i + N / 2 - 1;
      R = i + N / 2 + 1;
    }
    while (L >= i && R <= j) {
      swap(arr, L, R);
      L--;
      R++;
    }
  }

  private static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }
}
