package com.example.datastructureandalgorithms.datastructure.link;

public class Josepfu {

    public static void main(String[] args) {

        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(4);
        circleSingleLinkedList.showBoys();
    }
}

class CircleSingleLinkedList{
    //创建一个first节点
    private Boy first = null;
    //添加小孩节点，构建一个环形链表
    public void addBoy(int num) {
        //num 租偶一个数据校验
        if (num < 1){
            System.out.println("num不正确");
            return;
        }
        //for循环来创建环形链表
        Boy curBoy = first;
        for (int i = 1; i <= num; i++) {
            Boy boy = new Boy(i);
            if (i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    public void showBoys(){
        if (first == null){
            System.out.println("没有任何小孩");
            return;
        }
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号：%d\n",curBoy.getNo());
            curBoy = curBoy.getNext();
            if (curBoy == first){
                break;
            }
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序
    public void countBoy(int startNo, int countNum, int num){
        if (first == null || startNo < 1||startNo>num){
            System.out.println("输入参数有误，请重新输入！");
            return;
        }
        Boy helper = first;
        //先将helper指针指向最后一个节点，方便出圈
       while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //开始循环，知道只剩下一个节点
        while (true){
            if (helper == first){
                break;
            }
            //先移动，在出圈
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            System.out.printf("小孩%d出圈\n",first.getNo());
            //这时将first指向的小孩出圈
            first = first.getNext();
            helper.setNext(first);
        }
    }
}

class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
