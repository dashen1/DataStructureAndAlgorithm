package com.example.datastructureandalgorithms.datastructure.link;

import java.util.Stack;

public class SingleLinked {

    public static void main(String[] args) {

        HeroNode hero1 = new HeroNode(1, "松江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "无用", "及时雨");
        HeroNode hero3 = new HeroNode(3, "林冲", "及时雨");
        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.addNode(hero1);
        singleLinkedList.addNode(hero2);
        singleLinkedList.addNode(hero3);
        singleLinkedList.list();
    }
}

//

class SingleLinkedList {

    private HeroNode head = new HeroNode(0, "", "");

    public static int getLinkedLength(HeroNode head){
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    // 查找倒数第k个节点
    public static HeroNode findLastIndexNode(HeroNode head, int index){
        if (head.next == null ){
            return null;
        }
        int size = getLinkedLength(head);
        if (index <=0 || index >size){
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    //将单链表反转
    public static void reverserList(HeroNode head) {
        //如果链表为空或者只有一个元素的时候不需要反转
        if (head.next == null || head.next.next == null){
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点的下一个节点
        HeroNode reverserHead = new HeroNode(0,"","");
        while(cur != null) {
            next = cur.next;
            cur.next = reverserHead.next;//将cur的下一个节点指向新的链表的最前面
            reverserHead.next = cur;
            cur = next;
        }
        head.next = reverserHead.next;
    }

    //逆序打印 利用栈
    public static void reversePrint(HeroNode head){
        if (head.next == null) {
            return;
        }

        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while(cur != null){
            stack.push(cur);
            cur = cur.next;
        }

        while(stack.size() >0){
            System.out.println(stack.pop());
        }
    }
    //合并两个有序的单链表，合并之后仍然有序

    public void addNode(HeroNode heroNode) {
        //因为head头结点不能动，所以需要一个临时变量来保存
        HeroNode temp = head;

        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
    }

    public void addByOrder(HeroNode heroNode) {

        HeroNode temp = head;
        boolean isExit = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.next.no > heroNode.no) {
                break;
            } else if (temp.next.no == heroNode.no) {
                isExit = true;
                break;
            }
            temp = temp.next;
        }

        if (isExit) {
            System.out.println("待添加的英雄编号已经存在！");
        } else {//按顺序插入
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    public void update(HeroNode heroNode) {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode temp = head.next;
        boolean isExit = false;
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                isExit = true;
                break;
            }
            temp = temp.next;
        }
        if (isExit) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.println("没有找到要修改的英雄");
        }
    }
    //head不能动，因此需要temp辅助节点找到带删除的节点的前一个节点
    public void deleteNode(int no){
        HeroNode temp = head;
        boolean isExit = false;
        while(true){
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                isExit = true;
                break;
            }
            temp = temp.next;
        }
        if(isExit) {
            temp.next = temp.next.next;
        }else {
            System.out.println("要删除的节点不存在！");
        }
    }

    public void list() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
        next = null;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
