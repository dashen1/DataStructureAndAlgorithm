package com.example.datastructureandalgorithms.datastructure.sorting;

import java.util.Arrays;

public class RadixSort {

    public static void main(String[] args) {

        int[] arr = {53, 3, 542, 748, 14, 214};
        radixSort(arr);
    }

    private static void radixSort(int[] arr) {
        //第一轮 针对每个元素 进行个位数排序

        //第二轮 针对每个元素 进行十位数排序

        //第三轮 针对每个元素 进行百位数排序

        //遍历得到最大数 得到该最大数的长度，即要循环的次数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int maxLength = (max + "").length();

        //定义一个二维数组，10表示有10个桶，arr.length表示每个桶能放入的数据个数
        int[][] bucket = new int[10][arr.length];
        //为了几率每个桶中实际存放了多少个数据，定义一个一位数组来记录各个桶每次放入的数据个数
        int[] bucketElementCounts = new int[10];//哪个桶放了多少个数据

        for (int i = 1, n = 1; i < maxLength; i++, n *= 10) {
            for (int j = 0; j < arr.length; j++) {
                int indexOfElement = arr[j] / n % 10;//放入哪个桶
                bucket[indexOfElement][bucketElementCounts[indexOfElement]] = arr[j];//把数据放进指定的桶中
                bucketElementCounts[indexOfElement]++;//记录该桶存放数据的个数
            }

            //把数据放回原始数组
            int index = 0;
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，才放入元数据
                if (bucketElementCounts[k] != 0) {
                    //循环该桶
                    for (int j = 0; j < bucketElementCounts[k]; j++) {
                        //取出元素放到arr中
                        arr[index++] = bucket[k][j];
                    }
                }
                //第一轮结束后 要重置为0
                bucketElementCounts[k] = 0;
            }
            System.out.println("第" + i + "轮，对各位的排序处理：" + Arrays.toString(arr));
        }
        //1、从左到右 2、范围模型 3、样本
//        for (int j = 0; j < arr.length; j++) {
//            int indexOfElement = arr[j] /10 % 10;//放入哪个桶
//            bucket[indexOfElement][bucketElementCounts[indexOfElement]] = arr[j];//把数据放进指定的桶中
//            bucketElementCounts[indexOfElement]++;//记录该桶存放数据的个数
//        }
//
//        //================================
//
//        //bucket 这个二维数组不需要重置，因为bucketElementCounts该数组已经记录了每个桶所存放数据的个数，所以不会出现数据混乱问题
//        //把数据放回原始数组
//        index = 0;
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            //如果桶中有数据，才放入元数据
//            if (bucketElementCounts[k] != 0) {
//                //循环该桶
//                for (int j = 0; j < bucketElementCounts[k]; j++) {
//                    //取出元素放到arr中
//                    arr[index++] = bucket[k][j];
//                }
//            }
//            //第一轮结束后 要重置为0
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("第二轮，对各位的排序处理：" + Arrays.toString(arr));
//
//        for (int j = 0; j < arr.length; j++) {
//            int indexOfElement = arr[j] /100 % 10;//放入哪个桶
//            bucket[indexOfElement][bucketElementCounts[indexOfElement]] = arr[j];//把数据放进指定的桶中
//            bucketElementCounts[indexOfElement]++;//记录该桶存放数据的个数
//        }
//
//        //把数据放回原始数组
//        index = 0;
//        for (int k = 0; k < bucketElementCounts.length; k++) {
//            //如果桶中有数据，才放入元数据
//            if (bucketElementCounts[k] != 0) {
//                //循环该桶
//                for (int j = 0; j < bucketElementCounts[k]; j++) {
//                    //取出元素放到arr中
//                    arr[index++] = bucket[k][j];
//                }
//            }
//            //第一轮结束后 要重置为0
//            bucketElementCounts[k] = 0;
//        }
//        System.out.println("第三轮，对各位的排序处理：" + Arrays.toString(arr));
//    }
    }
    }
