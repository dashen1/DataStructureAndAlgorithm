package com.example.datastructureandalgorithms.datastructure.search;

import java.util.ArrayList;

public class BinarySearch {

    public static void main(String[] args) {

        int arr[] = {1, 8, 10, 89, 1000, 1234};
        int index = binarySearch(arr, 0, arr.length - 1, -1);
        if (index == -1) {
            System.out.println("没有找到！");
        } else {
            System.out.println("查找的下标：" + index);
        }
        int arr2[] = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        ArrayList arrayList = binarySearchList(arr2, 0, arr.length - 1, 1000);
        System.out.println(" " + arrayList);
    }

    public static int binarySearch(int[] arr, int left, int right, int findVal) {
        //1.首先确定该数组的中间下标 mid=(left+right)/2
        //2.然后让需要查找的findVal和midVal比较
        //2.1 findVal>midVal说明要查找的数在右边，因此需要向右递归查找
        //2.2 findVal<midVal说明要查找的数在左边，因此需要向左递归查找
        //2.2 findVal==midVal说明找到了

        //当left>right时，说明递归完了，但没有查找到
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearch(arr, left + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearch(arr, left, mid - 1, findVal);
        } else {
            return mid;
        }
    }

    public static ArrayList binarySearchList(int[] arr, int left, int right, int findVal) {
        //1.首先确定该数组的中间下标 mid=(left+right)/2
        //2.然后让需要查找的findVal和midVal比较
        //2.1 findVal>midVal说明要查找的数在右边，因此需要向右递归查找
        //2.2 findVal<midVal说明要查找的数在左边，因此需要向左递归查找
        //2.2 findVal==midVal说明找到了

        //当left>right时，说明递归完了，但没有查找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];
        if (findVal > midVal) {
            return binarySearchList(arr, left + 1, right, findVal);
        } else if (findVal < midVal) {
            return binarySearchList(arr, left, mid - 1, findVal);
        } else {
            /**
             * 将所有满足1000元素的下标加入到集合
             */
            ArrayList<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp -= 1;
            }

            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != findVal) {
                    break;
                }
                list.add(temp);
                temp += 1;
            }
            return list;
        }
    }
}
