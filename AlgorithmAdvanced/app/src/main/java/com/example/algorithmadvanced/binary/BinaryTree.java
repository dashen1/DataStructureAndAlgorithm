package com.example.algorithmadvanced.binary;

import java.util.Stack;

public class BinaryTree {

  public static void main(String[] args) {
    /**
     *
     * AxB 先序遍历
     * DxE 后序遍历
     * A交E一定是x的祖先节点
     *
     * 递归能回到一个节点3次
     */
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    Node node5 = new Node(5);
    Node node6 = new Node(6);
    Node node7 = new Node(7);
    node1.left = node2;
    node1.right = node3;
    node2.left = node4;
    node2.right = node5;
    node3.left = node6;
    node3.right = node7;
    System.out.println("先序遍历");
    preOrder(node1);
    System.out.println("中序遍历");
    inOrder(node1);
    System.out.println("后序遍历");
    postOrder(node1);

    System.out.println("非递归实现");
    preOrder1(node1);
    System.out.println("===");
    inOrder1(node1);
  }

  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
      this.value = value;
      left = null;
      right = null;
    }
  }

  public static void preOrder(Node head) {
    if (head == null) {
      return;
    }
    System.out.println(head.value);
    preOrder(head.left);
    preOrder(head.right);
  }

  public static void inOrder(Node head) {
    if (head == null) {
      return;
    }
    inOrder(head.left);
    System.out.println(head.value);
    inOrder(head.right);
  }

  public static void postOrder(Node head) {
    if (head == null) {
      return;
    }
    postOrder(head.left);
    postOrder(head.right);
    System.out.println(head.value);
  }

  //非递归实现先序、中序、后序遍历
  public static void preOrder1(Node head) {
    if (head == null) {
      return;
    }
    Stack<Node> stack = new Stack<>();
    stack.add(head);
    while (!stack.isEmpty()) {
      Node cur = stack.pop();
      System.out.println("node:" + cur.value);
      if (cur.right != null) {
        stack.push(cur.right);
      }
      if (cur.left != null) {
        stack.push(cur.left);
      }
    }
  }

  public static void inOrder1(Node head){
    if(head==null){
      return;
    }
    Stack<Node> stack1 = new Stack<>();
    Stack<Node> stack2 = new Stack<>();
    stack1.push(head);
    while (!stack1.isEmpty()){
      head = stack1.pop();
      stack2.push(head);
      if(head.left!=null){
        stack1.push(head.left);
      }
      if(head.right!=null){
        stack1.push(head.right);
      }
      while (!stack2.isEmpty()){
        System.out.print(stack2.pop().value+" ");
      }
    }
  }

}
