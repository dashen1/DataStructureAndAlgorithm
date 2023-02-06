package com.example.datastructureandalgorithms.datastructure.sorting;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {

        //-9,78,0,23,-567,70
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("shellSort2排序后：" + Arrays.toString(arr));
    }

    private static void quickSort(int[] arr, int left, int right) {
        int l = left;
        int r = right;
        int pivot = arr[(l + r) / 2];
        //把pivot小的放到左边，把比pivot大的放到右边
        int temp = 0;
        while (l < r) {
            //在pivot的左边一直找到比pivot大的数
            while (arr[l] < pivot) {
                l += 1;
            }
            //在pivot的左边一直找到比pivot小或等于的数
            while (arr[r] > pivot) {
                r -= 1;
            }
            //说明pivot左右两边的值，左边小于等于pivot,右边大于等于pivot

            if (l >= r) {
                break;
            }
            //否则说明，左边找到了比pivot大的数或者右边找到了比pivot小的数
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //避免出现死循环 即避免数组中出现两个或两个以上相同的数
            //因为一旦出现这种情况，l或者r其中一个或者两个下标值就会一直不变，那么就永远不会有l>=r;无法退出循环
            //避免排序带重复数字的数组

//            l += 1;
//            r -= 1;
            //这样做就是为了让小于pivot的数一定都在pivot的左边
            if (arr[l] == pivot) {
                r -= 1;
            }
            //让大于pivot的数都在右边
            if (arr[r] == pivot) {
                l += 1;
            }
        }
        //避免栈溢出 方便下次递归
        if (l == r) {
            l += 1;
            r -= 1;
        }
        if (left < r) {
            quickSort(arr, left, r);
        }
        if (right > l) {
            quickSort(arr, l, right);
        }
    }


}
