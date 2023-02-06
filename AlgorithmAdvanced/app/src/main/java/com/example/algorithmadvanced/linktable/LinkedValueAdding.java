package com.example.algorithmadvanced.linktable;

import java.util.List;

public class LinkedValueAdding {

  public static void main(String[] args) {

  }

  public static class Node {
    public int value;
    public Node next;

    public Node(int value) {
      this.value = value;
    }
  }

  public static class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value) {
      this.value = value;
      this.next = null;
    }
  }

  public static ListNode mergeTwoLists(ListNode head1, ListNode head2) {

    if (head1 == null || head2 == null) {
      return head1 == null ? head2 : head1;
    }
    ListNode head = head1.value >= head2.value ? head2 : head1;
    ListNode cur1 = head.next;
    ListNode cur2 = head == head1 ? head2 : head1;
    ListNode pre = head;
    while (cur1 != null && cur2 != null) {
      if (cur1.value <= cur2.value) {
        pre.next = cur1;
        cur1 = cur1.next;
      } else {
        pre.next = cur2;
        cur2 = cur2.next;
      }
      pre = pre.next;
    }
    //看哪个先到结尾
    pre.next = cur1 != null ? cur1 : cur2;
    return head;
  }

  public static ListNode addTwoNumbers(ListNode head1, ListNode head2) {
    int len1 = listLength(head1);
    int len2 = listLength(head2);
    ListNode l = len1 >= len2 ? head1 : head2;
    ListNode s = l == head1 ? head2 : head1;
    ListNode curL = l;
    ListNode curS = s;
    ListNode last = curL;
    int carry = 0;
    int curNum = 0;
    while (curS != null) {
      curNum = curL.value + curS.value + carry;
      curL.value = curNum % 10;
      carry = curNum / 10;
      last = curL;
      curL = curL.next;
      curS = curS.next;
    }
    while (curL != null) {
      curNum = curL.value + carry;
      curL.value = curNum % 10;
      carry = curNum / 10;
      last = curL;
      curL = curL.next;
    }
    if (carry != 0) {
      last.next = new ListNode(1);
    }
    return l;
  }

  public static int listLength(ListNode head) {
    int len = 0;
    while (head != null) {
      len++;
      head = head.next;
    }
    return len;
  }
}
