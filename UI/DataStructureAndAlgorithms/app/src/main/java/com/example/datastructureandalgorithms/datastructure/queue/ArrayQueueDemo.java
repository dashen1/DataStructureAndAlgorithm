package com.example.datastructureandalgorithms.datastructure.queue;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数到队列");
            System.out.println("g(get):从队列取数据");
            System.out.println("h(head):查看队列头数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数：");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    System.out.println("从队列取出数据：");
                    try {
                        int result = arrayQueue.getQueue();
                        System.out.printf("取出的数据是：%d\n",result);
                    } catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 'h':
                    System.out.println("查看队头数据：");
                    try {
                        int head =  arrayQueue.headQueue();
                        System.out.printf("查看队头数据：%d\n",head);
                    } catch (Exception e){
                        System.out.println(e);
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出");
    }
}

//使用数组模拟队列
class ArrayQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;
        rear = -1;
    }

    public boolean isFull(){
        return rear == maxSize -1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满！");
            return;
        }
        rear++;
        arr[rear] = n;
    }

    public int getQueue() {
        if (isEmpty()) {
            System.out.println("队列已空！");
            throw new RuntimeException("队列空");
        }
        front++;
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据!");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i,arr[i]);
        }
    }

    public int headQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列是空的");
        }
        return arr[front+1];
    }
}
