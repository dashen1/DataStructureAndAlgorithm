package com.example.datastructureandalgorithms.datastructure.sorting;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BubbleSort {

    public static void main(String[] args) {
        // 3,9,-1,10,-2 从小到大排序
        // 如果在某趟排序中，没有发生一次交换，就可以提前结束排序

        int arr[] = {3, 9, -1, 10, -2};
        bubbleSort(arr);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(" "+arr[i]);
        }

        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int) (Math.random()*80000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是："+dateStr);
        bubbleSort(arr2);
        Date date1 = new Date();
        String dateStr2 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间是："+dateStr2);

    }

    //将前面的冒泡排序进行封装
    public static void bubbleSort(int[] arr){
        int temp = 0;
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {//趟数
            for (int j = 0; j < arr.length - 1-i; j++) {//比较次数
                //前面的数比后面的数大 就交换
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (!flag){
                break;
            } else {
                flag = false;
            }
        }
    }
}
