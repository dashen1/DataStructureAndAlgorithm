package com.example.datastructureandalgorithms.datastructure.tree;

import com.example.datastructureandalgorithms.dao.HeroNode;

public class ThreadedBinaryTreeDemo {

    public static void main(String[] args) {

        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(2, "jack");
        HeroNode node3 = new HeroNode(3, "smith");
        HeroNode node4 = new HeroNode(4, "mary");
        HeroNode node5 = new HeroNode(5, "king");
        HeroNode node6 = new HeroNode(6, "dim");
//        HeroNode node7 = new HeroNode(7, "kafka");
//        HeroNode node8 = new HeroNode(8, "redis");


        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //测试 以10号节点 node5
        HeroNode leftNode = node5.getLeft();
        System.out.println("10:" + leftNode);

        System.out.println("遍历线索化二叉树");
        threadedBinaryTree.threadList();

    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    //指向当前节点的前驱节点的引用 保留的是前一个节点
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //因为线索化后 各个节点的指向都发生了改变 所以不能简单的用之前的遍历方式
    //中序遍历线索化二叉树
    public void threadList() {
        HeroNode node = root;
        //循环找到leftType==1的节点
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            //如果当前节点的右指针指向的是后继节点，就也一直输出
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            //root为根节点 root的左子树遍历完了 到root的右子树
            node = node.getRight();
        }
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        //前驱节点就是前面的节点
        //中序遍历 先线索化左子树 线索化当前节点() 线索化右子树
        threadedNodes(node.getLeft());
        if (node.getLeft() == null) {//如果当前节点是叶子节点
            node.setLeft(pre);
            node.setLeftType(1);
        }
        //处理后继节点  {8,3,10,1,14}
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }

        pre = node;
        threadedNodes(node.getRight());
    }
}


