package com.example.algorithmadvanced.linktable;

import android.os.Parcelable;

import java.util.List;

public class ReverseNodesInKGroup {

  public static void main(String[] args) {

    double a = 2.4;
    System.out.println(Math.floor(a));
  }

  public static class ListNode {
    public int val;
    public ListNode next;
  }

  public static ListNode reverseKGroup(ListNode head, int k) {
    ListNode start = head;
    ListNode end = getKGroupEnd(start, k);
    if (end == null) {
      return head;
    }
    //第一组凑齐了
    reverse(start, end);
    //上一组的结尾节点
    ListNode lastEnd = start;
    while (lastEnd.next != null) {
      start = lastEnd.next;
      end = getKGroupEnd(start, k);
      if (end == null) {
        return head;
      }
      reverse(start, end);
      lastEnd.next = end;
      lastEnd = start;
    }
    return head;
  }

  public static ListNode getKGroupEnd(ListNode start, int k) {
    //为什么要--k?
    while (--k != 0 && start != null) {
      start = start.next;
    }
    return start;
  }

  public static void reverse(ListNode start, ListNode end) {
    end = end.next;
    ListNode pre = null;
    ListNode cur = start;
    ListNode next = null;
    while (cur != end) {
      next = cur.next;
      cur.next = pre;
      pre = cur;
      cur = next;
    }
    start.next = end;
  }
}
