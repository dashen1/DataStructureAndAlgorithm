package com.example.datastructureandalgorithms.theleftgod.stack;

import java.util.Stack;

public class InvertedSequence {

    public static void main(String[] args) {


        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        reverse(stack);
        while(!stack.isEmpty()){
            System.out.println(stack.pop());
        }
    }

    public static void reverse(Stack<Integer> stack){
        if(stack.isEmpty()){
            return;
        }
        int i = f(stack);
        reverse(stack);
        stack.push(i);
    }

    private static int f(Stack<Integer> stack) {
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        }else {
            int last = f(stack);
            stack.push(result);
            return last;
        }
    }
}
