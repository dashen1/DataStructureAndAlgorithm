package com.example.datastructureandalgorithms.datastructure.tree;

import com.example.datastructureandalgorithms.dao.HeroNode;

public class BinaryTreeDemo {

    public static void main(String[] args) {

        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "松江");
        HeroNode node2 = new HeroNode(2, "无用");
        HeroNode node3 = new HeroNode(3, "陆军");
        HeroNode node4 = new HeroNode(4, "林冲");

        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        binaryTree.setRoot(root);

        //前序遍历
        System.out.println("前序遍历：");
        binaryTree.preOrder();

        System.out.println("中序遍历：");
        binaryTree.infixOrder();

        System.out.println("后序遍历：");
        binaryTree.postOrder();

        System.out.println("前序遍历查找。");
        HeroNode resNode = binaryTree.preOrderSearch(3);
        if (resNode!=null){
            System.out.printf("找到了！no=%d,name=%s\n",resNode.getNo(),resNode.getName());
        } else {
            System.out.println("没有找到");
        }

        System.out.println("中序遍历查找。");
        resNode = binaryTree.infixOrderSearch(2);
        if (resNode!=null){
            System.out.printf("找到了！no=%d,name=%s\n",resNode.getNo(),resNode.getName());
        } else {
            System.out.println("没有找到");
        }

        System.out.println("递归遍历删除=========");
        System.out.println("删除前");
        binaryTree.preOrder();
        binaryTree.delNode(1);
        System.out.println("删除后");
        binaryTree.preOrder();
    }
}
//定义二叉树

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void delNode(int no){
        if (root!=null){
            if (root.getNo()==no){
                root=null;
            }else {
                this.root.delNode(no);
            }
        }else {
            System.out.println("空树，不可删除~");
        }
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空！");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}


