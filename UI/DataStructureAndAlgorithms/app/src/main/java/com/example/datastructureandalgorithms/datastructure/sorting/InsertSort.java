package com.example.datastructureandalgorithms.datastructure.sorting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {

    public static void main(String[] args) {

        int[] arr = {101, 34, 119, 1};

        insertSort(arr);
        System.out.println("排序后："+ Arrays.toString(arr));
        int[] arr2 = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr2[i] = (int) (Math.random() * 80000);
        }

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        System.out.println("排序前的时间是：" + dateStr);
        //insertSort(arr2);
        Date date1 = new Date();
        String dateStr2 = simpleDateFormat.format(date1);
        System.out.println("排序后的时间是：" + dateStr2);
    }

    private static void insertSort(int[] arr) {
        //逐步推导
        //定义带插入的数
        //{101, 34, 119, 1}
        //第一个for循环表示要插入元素的个数
        //第二个for循环当前要插入的元素和前面的元素进行比较，找到比自己大的元素就将要插入的元素插入到当前遍历的元素，知道前面没有元素或前面元素比自己小就停止
        //此时将待插元素方放到该下标位置上
        for (int i = 1; i < arr.length; i++) {
            int insertValue = arr[i];//保存待插元素
            int insertIndex = i;//前面可插入下标，不包括自己，所以要减1
            //保证下标不越界
            //往前比较，所以要判断下标要大于等于0，因为排序顺序是从小到大的，所以要找到前面比自己小的元素就停止
            while (insertIndex-1 >= 0 && insertValue < arr[insertIndex-1]) {
                arr[insertIndex] = arr[insertIndex-1];
                insertIndex--;
                System.out.println("while insertIndex的大小："+insertIndex);
            }
            System.out.println("insertIndex的大小："+insertIndex);
            arr[insertIndex] = insertValue;
//            if (insertIndex + 1 !=i){
//                arr[insertIndex + 1] = insertValue;
//            }
        }
    }
}
