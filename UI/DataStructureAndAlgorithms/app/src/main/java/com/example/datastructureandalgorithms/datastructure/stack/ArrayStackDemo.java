package com.example.datastructureandalgorithms.datastructure.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {

        ArrayStack stack = new ArrayStack(4);
        char key = ' ';
        boolean loop = true;
        while(loop){
            Scanner scanner = new Scanner(System.in);
            System.out.println("show:表示显示栈：");
            System.out.println("exit:表示退出程序：");
            System.out.println("push:表示入栈：");
            System.out.println("pop:表示出栈：");
            key = scanner.next().charAt(0);
            switch (key){
                case 's':
                    stack.list();
                    break;
                case 'p':
                    System.out.println("输入要入栈的数据：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case 'o':
                    int result = stack.pop();
                    System.out.printf("出栈：%d\n",result);
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
    }
}

class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;//栈顶

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public boolean isFull(){
        return top == maxSize -1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public void push(int value){
        if (isFull()){
            System.out.println("栈满了");
            return;
        }
        top++;
        stack[top] = value;
    }

    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("站空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    public void list(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }

        for (int i = top; i > -1 ; i--) {
            System.out.printf("栈的元素：%d\n",stack[i]);
        }
    }
}
