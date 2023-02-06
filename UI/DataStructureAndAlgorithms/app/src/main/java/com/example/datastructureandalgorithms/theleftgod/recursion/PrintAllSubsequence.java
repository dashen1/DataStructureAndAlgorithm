package com.example.datastructureandalgorithms.theleftgod.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PrintAllSubsequence {

    public static void main(String[] args) {

        char[] str = {'a','b','c','d','e'};
        ArrayList<String> res = new ArrayList<>();
        printAllSubsequence(str, res);
        int i =1;
        for (String s : res) {
            System.out.println(i+" "+s);
            i++;
        }
    }

    public static void printAllSubsequence(char[] str, ArrayList<String> res){
        process4(str,0,res);
    }

    public static void process(char[] str,int i, List<Character> res){
        if(i==str.length){
            printList(res);
            return;
        }
        List<Character> resKeep = copyList(res);
        resKeep.add(str[i]);
        process(str,i+1,resKeep);//要当前字符
        List<Character> resNotInclude = copyList(res);
        process(str,i+1,resNotInclude);//不要当前字符
    }

    public static void process2(char[] str, int i){
        if (i == str.length){
            System.out.println(String.valueOf(str));
            return;
        }
        process2(str,i+1);
        char tmp = str[i];
        str[i]=0;
        process2(str,i+1);
        str[i]=tmp;
    }

    public static void process3(char[] str, int i, ArrayList<String> res){
        if (i == str.length){
            res.add(String.valueOf(str));
        }
        for (int j = i; j < str.length ; j++) {
            swap(str, i, j);
            process3(str,i+1,res);
            swap(str,i,j);
        }
    }

    //去重复的全排列
    public static void process4(char[] str, int i, ArrayList<String> res){
        if (i == str.length){
            res.add(String.valueOf(str));
        }
        boolean[] visit = new boolean[26];
        for (int j = i; j < str.length ; j++) {
            if(!visit[str[j] - 'a']) {
                visit[str[j] - 'a'] = true;
                swap(str, i, j);
                process4(str, i + 1, res);
                swap(str, i, j);
            }
        }
    }

    private static void swap(char[] str, int i, int j) {
        char tmp = str[i];
        str[i] = str[j];
        str[j] = tmp;
    }


    public static void printList(List<Character> res){
        for (Character c : res) {
            System.out.print(c);
        }
        System.out.println();
    }

    public static List<Character> copyList(List<Character> res){
        if (res !=null){
            return new ArrayList<>(res);
        }
        return null;
    }
}
