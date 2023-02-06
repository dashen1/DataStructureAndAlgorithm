package com.example.algorithmadvanced.hashmap;

import java.util.TreeMap;

public class Map {

  public static void main(String[] args) {
    //HashMap 的key值都是按值传递

    TreeMap<Integer, String> treeMap1 = new TreeMap<>();

    treeMap1.put(3, "我说3");
    treeMap1.put(0, "我说3");
    treeMap1.put(7, "我说3");
    treeMap1.put(2, "我说3");
    System.out.println(treeMap1.containsKey(7));
    System.out.println(treeMap1.containsKey(6));

    treeMap1.put(3, "hello");
    System.out.println(treeMap1.get(3));

    System.out.println(treeMap1.firstKey());
    System.out.println(treeMap1.lastKey());
    //离5最近的key
    System.out.println(treeMap1.floorKey(5));//<=5
    System.out.println(treeMap1.ceilingKey(5));//>=5

    System.out.println("===============");

    Node node = new Node(1);
    node.next = new Node(2);
    node.next.next = new Node(3);

    Node ans = reverseLinkedList(node);
    System.out.println(ans.value);
    System.out.println("双向链表反转");

    System.out.println("双端队列");
  }



  public static class Node {
    int value;
    Node next;

    public Node(int value) {
      this.value = value;
    }
  }

  public static class DoubleNode {
    public int value;
    public DoubleNode pre;
    public DoubleNode next;

    public DoubleNode(int data) {
      value = data;
    }
  }

  public static DoubleNode reverseDoubleLinkedList(DoubleNode head){
    DoubleNode pre = null;
    DoubleNode next = null;
    while (head!=null){
      next = head.next;
      head.next = pre;
      head.pre = next;
      pre = head;
      head = next;
    }
    return pre;
  }

  public static Node reverseLinkedList(Node head) {
    Node pre = null;
    Node next = null;
    while (head != null) {
      next = head.next;
      head.next = pre;
      pre = head;
      head = next;
    }
    return pre;
  }
}
