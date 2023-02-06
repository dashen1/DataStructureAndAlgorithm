package com.example.algorithmadvanced.linktable;

public class Linked {

  public static void main(String[] args) {

    /**
     * 快慢指针 解决
     * 1、链表中间节点问题
     * 2、链表回文问题（用栈容器最快）
     *
     */
  }

  public static class Node<V>{
    public V value;
    public Node<V> next;

    public Node(V value) {
      this.value = value;
      next = null;
    }
  }

  public static class Node2<V>{
    public V value;
    public Node2<V> pre;
    public Node2<V> next;

    public Node2(V value) {
      this.value = value;
      pre = null;
      next = null;
    }
  }

  public static class MyDeque<V>{
    private Node2<V> head;
    private Node2<V> tail;
    private int size;

    public MyDeque() {
      head = null;
      tail =null;
      size = 0;
    }

    public boolean isEmpty(){
      return size == 0;
    }

    public int size(){
      return size;
    }

    public void pushHead(V value){
      Node2<V> cur = new Node2<>(value);
      if(head==null){
        head = cur;
        tail = cur;
      }else {
        cur.next = head;
        head.pre = cur;
        head = cur;
      }
      size++;
    }

    public void pushTail(V value){
      Node2<V> cur = new Node2<>(value);
      if(head==null){
        head = cur;
        tail = cur;
      }else {
        tail.next = cur;
        cur.pre = tail;
        tail = cur;
      }
      size++;
    }

    public V poolHead(){
      V ans = null;
      if(head==null){
        return ans;
      }
      size--;
      ans = head.value;
      if(head==tail){
        head = null;
        tail = null;
      }else {
        head = head.next;
        head.pre = null;
      }
      return ans;
    }

    public V pollTail(){
      V ans = null;
      if(tail==null){
        return ans;
      }
      size--;
      ans = tail.value;
      if(head==tail){
        head=null;
        tail=null;
      }else {
        tail = tail.pre;
        tail.next = null;
      }
      return ans;
    }
  }
  public static class MyStack<V>{
    private Node<V> head;
    private int size;

    public MyStack() {
      head = null;
      size = 0;
    }

    public boolean isEmpty(){
      return size == 0;
    }

    public int size(){
      return size;
    }

    public void push(V value){
      Node<V> cur = new Node<>(value);
      if(head == null){
        head = cur;
      } else {
        cur.next = head;
        head = cur;
      }
      size++;
    }

    public V poll(){
      V ans = null;
      if(head!=null){
        ans = head.value;
        head = head.next;
        size--;
      }
      return ans;
    }

    public V peek(){
      V ans = null;
      if(head!=null){
        ans = head.value;
      }
      return ans;
    }
  }

  public static class MyQueue<V>{
    private Node<V> head;
    private Node<V> tail;
    private int size;

    public MyQueue() {
      head = null;
      tail = null;
      size = 0;
    }

    public boolean isEmpty(){
      return size == 0;
    }

    public void enqueue(V value){
      Node<V> cur = new Node<>(value);
      if (tail==null){
        head=cur;
        tail = cur;
      }else {
        tail.next = cur;
        tail = cur;
      }
      size++;
    }

    public V poll(){
      V ans = null;
      if(head!=null){
        ans = head.value;
        head = head.next;
        size--;
      }
      if(head==null){
        tail = null;
      }
      return ans;
    }

    public V peek(){
      V ans = null;
      if(head!=null){
        ans = head.value;
      }
      return ans;
    }
  }
}
