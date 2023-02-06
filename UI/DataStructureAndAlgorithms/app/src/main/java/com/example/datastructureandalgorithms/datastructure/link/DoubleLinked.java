package com.example.datastructureandalgorithms.datastructure.link;

public class DoubleLinked {

    public static void main(String[] args) {

        System.out.println("双向链表的测试：");
        HeroNode2 hero1 = new HeroNode2(1, "松江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "无用", "及时雨");
        HeroNode2 hero3 = new HeroNode2(3, "林冲", "及时雨");
        HeroNode2 hero4 = new HeroNode2(4, "小梦", "及时雨");
        DoubleLinkedList singleLinkedList = new DoubleLinkedList();
        singleLinkedList.addNode(hero1);
        singleLinkedList.addNode(hero2);
        singleLinkedList.addNode(hero3);
        singleLinkedList.addNode(hero4);
        singleLinkedList.list();
    }
}

class DoubleLinkedList{

    private HeroNode2 head = new HeroNode2(0,"","");

    public void addNode(HeroNode2 heroNode) {
        //因为head头结点不能动，所以需要一个临时变量来保存
        HeroNode2 temp = head;

        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }


    //双向链表和单项链表的修改一样
    public void update(HeroNode2 heroNode) {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode2 temp = head.next;
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

    public void deleteNode(int no){
        // 判断当前链表是否为空
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }

        HeroNode2 temp = head.next;//不需要辅助节点 只需要找到要删除的节点即可
        boolean isExit = false;
        while(true){
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                isExit = true;
                break;
            }
            temp = temp.next;
        }
        if(isExit) {
            //temp.next = temp.next.next;
            temp.pre.next = temp.next;
            //如果删除的是最后一个节点 下面这行代码有问题
            if (temp.next !=null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("要删除的节点不存在！");
        }
    }
    public void list() {
        if (head.next == null) {
            System.out.println("链表为空！");
            return;
        }
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }



}


class HeroNode2 {
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;
    public HeroNode2 pre;

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
