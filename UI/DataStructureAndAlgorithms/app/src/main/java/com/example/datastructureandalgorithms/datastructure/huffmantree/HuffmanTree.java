package com.example.datastructureandalgorithms.datastructure.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

    public static void main(String[] args) {

        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        Node root = createHuffmanTree(arr);
        preOrder(root);
        /**
         * endianness 字节序
         *
         */
    }

    public static void preOrder(Node root){
        if (root!=null){
            root.preOrder();
        }else {
            System.out.println("哈夫曼树是空树，不能遍历!");
        }
    }

    public static Node createHuffmanTree(int[] arr) {
        //为操作方便
        //1.遍历数组
        //2.将arr的每个元素构成一个Node
        //3.将Node放入到ArrayList
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }


        while (nodes.size()>1){
            //排序从小到大
            Collections.sort(nodes);
            //System.out.println("node= " + nodes);

            //取出权值最小的二叉树
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

            //从ArrayList删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //把新的二叉树放进ArrayList
            nodes.add(parent);
        }
        //最后返回哈夫曼树的root节点
        return nodes.get(0);
    }
}

//为了让Node对象支持排序，让Node实现Comparable接口
class Node implements Comparable<Node> {
    int value;//权值
    Node left;
    Node right;

    //写一个前序遍历
    public void preOrder(){
        System.out.println(this);
        if (this.left!=null){
            this.left.preOrder();
        }
        if (this.right!=null){
            this.right.preOrder();
        }
    }

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {// a negative integer, zero, or a positive integer | less than, equal to, or greater

        //表示从小到大排
        return this.value - o.value;
    }
}