package com.example.datastructureandalgorithms.datastructure.sorting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {

    public static void main(String[] args) {

        //原始数组 101,34,119,1
        int[] arr = {101, 34, 119, 1};
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int) (Math.random()*80000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是："+dateStr);
        selectSort(arr2);
        Date date1 = new Date();
        String dateStr2 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间是："+dateStr2);
        System.out.println("排序后："+ Arrays.toString(arr));

    }

    public static void selectSort(int[] arr) {

        //第一轮：101, 34, 119, 1
        //第一轮：1,34,119,101
        //每次选择最小值放到最前面
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    minIndex = j;
                }
            }
            //将最小值放到arr[0],即交换 当前下标的值不是最小值才进行交换
            if (minIndex!=i){//如果
                arr[minIndex]=arr[i];
                arr[i]=min;
            }
//            System.out.println("第"+i+"轮后：");
//            System.out.println(Arrays.toString(arr));
        }
    }
}
