package com.example.algorithmadvanced.linktable;

import java.util.HashMap;

public class CopyListWithRan {

  public static void main(String[] args) {

  }


  public static class Node {
    public Node next;
    public Node rand;
    public int value;

    public Node(int value) {
      this.value = value;
    }
  }

  //不用容器的方法
  public static Node copyListWithRand2(Node head) {
    if (head == null) {
      return null;
    }
    Node cur = head;
    Node next = null;
    while (cur != null) {
      next = cur.next;
      cur.next = new Node(cur.value);
      cur.next.next = next;
      cur = next;
    }
    cur = head;
    Node curCopy = null;
    while (cur != null) {
      next = cur.next.next;//原来链表的下一个节点
      curCopy = cur.next;
      //下面这行代码有疑问？
      //解答：因为cur.rand是老节点，cur.rand.next是对应老节点的新的克隆节点
      //所以实质上cur.rand和cur.rand.next是一样的
      curCopy.rand = cur.rand != null ? cur.rand.next : null;
      cur = next;
    }
    Node res = head.next;
    cur = head;
    while (cur != null) {
      next = cur.next.next;
      curCopy = cur.next;
      cur.next = next;
      curCopy.next = next != null ? next.next : null;
      cur = next;
    }
    return res;
  }

  //用容器的方法
  public static Node copyListWithRand1(Node head) {
    HashMap<Node, Node> map = new HashMap<>();
    Node cur = head;
    while (cur != null) {
      map.put(cur, new Node(cur.value));
      cur = cur.next;
    }

    cur = head;
    while (cur != null) {
      //cur 老节点
      //map.get(cur)新节点
      map.get(cur).next = map.get(cur.next);
      map.get(cur).rand = map.get(cur.rand);
      cur = cur.next;
    }
    return map.get(head);
  }
}
