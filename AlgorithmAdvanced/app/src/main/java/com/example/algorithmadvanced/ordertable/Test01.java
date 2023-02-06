package com.example.algorithmadvanced.ordertable;

public class Test01 {

  public static void main(String[] args) {
    //System.out.println("hello");
  }

  public static int minParts(String s){
    if(s==null||s.length()==0){
      return 0;
    }
    if(s.length()==1){
      return 1;
    }

    return process(s.toCharArray(), 0);
  }

  public static int process(char[] str, int i){
    if(i==str.length){
      return 0;
    }
    //尝试每一个end，如果str[i...end]是回文，就去尝试这个部分是作为回文的第一块
    int ans = Integer.MAX_VALUE;
    for (int end = i; end < str.length; end++) {
      if(valid(str, i,end)){
        ans = Math.min(ans, 1+process(str,end+1));
      }
    }
    return ans;
  }

  public static boolean valid(char[] str, int L, int R){
    //预处理查询表，降低时间复杂度
    return false;
  }

  public static int maxEOR(int arr[]){
    if(arr==null||arr.length==0){
      return 0;
    }
    //尝试必须以arr[i]结尾的子数组，最大疑惑和是多少，尝试所有的arr[i]
    int ans = 0;
    for (int i = 0; i < arr.length; i++) {
      //必须以arr[i]结尾
      for (int start = 0; start <= i; start++) {
        int sum = 0;
        for (int index = start; index <= i; index++) {
          sum^=arr[index];
        }

      }
    }
    return 0;
  }
}
