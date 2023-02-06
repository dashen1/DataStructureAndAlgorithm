package com.example.datastructureandalgorithms.datastructure.search;

public class SequenceSearch {

    public static void main(String[] args) {

        int arr[] = {1,9,11,-1,34,89};

        int index = sequenceSearch(arr,-1);
        if (index==-1){
            System.out.println("没有找到！");
        }else {
            System.out.println("查找的下标："+index);
        }

    }

    private static int sequenceSearch(int[] arr, int value) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==value){
                return i;
            }
        }
        return -1;
    }
}
