package com.example.algorithmadvanced.demo;

public class PrintNumWithMultiThread {

    public static void main(String[] args) {

        MyThread thread_1 = new MyThread(0);
        MyThread thread_2 = new MyThread(1);
        MyThread thread_3 = new MyThread(2);

        thread_1.start();
        thread_2.start();
        thread_3.start();
    }


}

class MyThread extends Thread {

    // 锁对象
    private static Object obj = new Object();
    // 计数器
    private static int count = 1;
    // 线程编号
    private int tNum;

    MyThread(int tNum) {
        this.tNum = tNum;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (obj) {
                if (count > 100) {
                    break;
                }
                // 用数量取余3是否等于编号的操作，实现线程间的切换
                if (count % 3 == tNum) {
                    System.out.println("线程编号：" + this.tNum + " 打印：" + count);
                    count++;
                } else {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 因为代码是从上到下顺序执行的，所以下面这行代码一定会执行
                obj.notifyAll();
            }
        }
    }
}
