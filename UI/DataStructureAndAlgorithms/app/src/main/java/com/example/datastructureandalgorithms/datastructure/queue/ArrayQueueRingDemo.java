package com.example.datastructureandalgorithms.datastructure.queue;

import java.util.Scanner;

public class ArrayQueueRingDemo {

    public static void main(String[] args) {
        ArrayRingQueue arrayQueue = new ArrayRingQueue(3);
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


class ArrayRingQueue {
    private int maxSize;
    private int front;
    private int rear;
    private int[] arr;

    public ArrayRingQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull(){
        return (rear+1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            System.out.println("队列已满！");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) %maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            System.out.println("队列已空！");
            throw new RuntimeException("队列空");
        }
       int value =arr[front];
        front = (front+1)%maxSize;
        return value;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据!");
            return;
        }
        for (int i = 0; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n", i%maxSize,arr[i%maxSize]);
        }
    }

    public int headQueue(){
        if (isEmpty()) {
            throw new RuntimeException("队列是空的");
        }
        return arr[front];
    }

    public int size() {
        return (rear+maxSize-front)%maxSize;
    }
}

