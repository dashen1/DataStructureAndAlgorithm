package com.example.datastructureandalgorithms.algorithm.binarysearchrecursion;

public class BinarySearchRecur {

    public static void main(String[] args) {

        int[] arr={1,3,8,10,10,10,100};

        int index = binarySearch(arr, 10);
        System.out.println("index="+index);

    }

    //分治算法的一种具体实现
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {//向左边找
                right = mid - 1;
            } else {//向右边找
                left = mid + 1;
            }
        }
        return -1;
    }
}
