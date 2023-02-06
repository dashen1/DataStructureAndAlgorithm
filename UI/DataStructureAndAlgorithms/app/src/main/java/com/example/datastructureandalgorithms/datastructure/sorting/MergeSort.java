package com.example.datastructureandalgorithms.datastructure.sorting;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0,arr.length-1,temp);
        System.out.println("归并排序后：" + Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp){
        if (left<right){
            int mid = (left+right)/2;
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1,right,temp);
            merge(arr,left,mid,right,temp);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;//指向temp数组的当前索引
        //1.先把左右两边的数据按照规则填充到temp,直到一边处理完毕
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {//谁小移谁
                temp[t] = arr[i];
                t += 1;
                i += 1;
            } else {
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }
        //2.把有剩余数据的一边的数据依次全部填充到temp

        while (i <= mid) {//左边还有剩余
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }
        while (j <= right) {//左边还有剩余
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }
        //3.将temp数组拷贝到原始数组
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {//第一次合并 tempLeft=0
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }
}
