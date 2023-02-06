package com.example.datastructureandalgorithms.datastructure.head;

import java.util.Arrays;

public class HeadSort {


    public static void main(String[] args) {

        int arr[] = {4, 6, 8, 5, 9};
        headSort(arr);
    }

    //堆排序方法 升序
    public static void headSort(int[] arr) {
        int temp = 0;
        System.out.println("堆排序");
        //将数组（二叉树）调整成一个大顶堆
        //先分布
//        adjustHead(arr,1,arr.length);
//        System.out.println("第一次："+Arrays.toString(arr));
//
//        adjustHead(arr,0,arr.length-1);
//        System.out.println("第二次："+Arrays.toString(arr));

        //经过下面一轮循环之后，基本可以确定所有的父节点都大于孩子节点 整体上基本是有序的
        //arr.length / 2 - 1 最后一个父节点
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHead(arr, i, arr.length);
        }

        System.out.println("数组1："+Arrays.toString(arr));

        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHead(arr, 0, j);
        }
        System.out.println("数组："+Arrays.toString(arr));

    }

    /**
     * @param arr
     * @param i      非叶子节点在数组中的下标 i = arr.length/2 - 1 最后一个非叶子节点
     * @param length 还有多少个元素需要调整 length在逐渐减少
     */
    public static void adjustHead(int[] arr, int i, int length) {

        int temp = arr[i];
        //i * 2 + 1左节点 k*2+1(非叶子节点的左孩子节点)
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //k+1<length 右节点小于数组长度 arr[k]<arr[k+1] 右节点比左节点大
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;//方便
            }
            //如果孩子节点大于父节点 交换
            if (arr[k] > temp) {
                arr[i] = arr[k];
                arr[k] = temp;
                i = k;
            } else {
                break;//为什么能直接break?
            }
        }
        //当for循环结束时，已经将以i为父节点的最大值，放到了最顶部（局部）
        arr[i] = temp;//把arr[i]放到了合适的位置了
    }
}
