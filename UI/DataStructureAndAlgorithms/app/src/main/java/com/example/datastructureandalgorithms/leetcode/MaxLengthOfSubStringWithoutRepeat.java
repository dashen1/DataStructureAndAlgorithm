package com.example.datastructureandalgorithms.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaxLengthOfSubStringWithoutRepeat {

  public static void main(String[] args) {
    String s="abcbac";
    int len = lengthOfLongestSubstring2(s);
    System.out.println("len="+len);
  }

  public static int lengthOfLongestSubstring2(String s) {
    int[] m = new int[128];
    int len = 0;
    for(int i = 0, j = 0; j < s.length(); j++){
      i = Math.max(m[s.charAt(j)], i);
      len = Math.max(len, j - i + 1);
      m[s.charAt(j)] = j + 1;
      System.out.println("m[s.charAt(j)]="+m[s.charAt(j)]);
      System.out.println("len="+len);
    }
    return len;
  }

  public static int lengthOfLongestSubstring(String s) {
    List<Character> list = new ArrayList<>();
    int maxLen=0;
    int indexR=-1;//-1表示还没开始
    int index=0;
    for(int i=0;i<s.length();){
      if(i!=0){
         for (int j=0;j<index+1;j++){
           list.remove(0);
         }
      }
      while(indexR+1<s.length()&&!list.contains(s.charAt(indexR+1))){
        list.add(s.charAt(indexR+1));
        indexR++;
      }
      //跳出while循环说明遇到相同字符的或者整个字符串没有重复的
      maxLen=Math.max(maxLen,indexR+1-i);
      if(indexR==s.length()-1){
        break;
      }
      if(indexR+1<s.length()){
        index = list.indexOf(s.charAt(indexR+1));
        i+=index+1;
      }
    }
    return maxLen;
  }
}
