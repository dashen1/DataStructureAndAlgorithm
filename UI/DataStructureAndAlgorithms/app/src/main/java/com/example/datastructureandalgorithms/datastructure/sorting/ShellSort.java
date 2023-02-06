package com.example.datastructureandalgorithms.datastructure.sorting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

    public static void main(String[] args) {

        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        //shellSort(arr);
        shellSort2(arr);
        System.out.println("shellSort2排序后：" + Arrays.toString(arr));
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int) (Math.random() * 80000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是：" + dateStr);
        shellSort(arr2);
        Date date1 = new Date();
        String dateStr2 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间是：" + dateStr2);
    }

    private static void shellSort(int[] arr) {
        //第一轮
        //步长 5
        //根据步长进行分组
        //交换式希尔排序 -》
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {//有多少组需要比较 相当于趟数
                //遍历各组中的所有元素
                for (int j = i - gap; j >= 0; j -= gap) {//每组有多少个元素需要比较 从后面元素开始比较
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    private static void shellSort2(int[] arr) {
        //第一轮
        //步长 5
        //根据步长进行分组
        //交换式希尔排序 -》
        //{8, 9, 1, 7, 2, 3, 5, 4, 6, 0}
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中的所有元素
                int k = i;
                int temp = arr[k];//保存待插元素
                if (arr[k] < arr[k - gap]) {// 6 > 3
                    while (k - gap >= 0 && temp < arr[k - gap]) {
                        arr[k] = arr[k - gap];//待插元素小于前面的元素，将当前元素放到待插元素位置
                        k -= gap;
                        System.out.println("while k的大小："+k);
                    }
                    System.out.println("k的大小："+k);
                    arr[k] = temp;
                }
            }
        }
    }
}
