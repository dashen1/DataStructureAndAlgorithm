package com.example.algorithmadvanced.problem;

import java.util.HashMap;

public class Problem_6_2_03 {

  public static void main(String[] args) {

    /**
     * LRU内存替换算法
     * 双向链表+HashMap
     */
  }


  public static class Node<K, V> {
    public K key;
    public V value;
    public Node<K, V> last;
    public Node<K, V> next;

    public Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }

  public static class NodeDoubleLinkedList<K, V> {
    private Node<K, V> head;
    private Node<K, V> tail;

    public NodeDoubleLinkedList() {
      head = null;
      tail = null;
    }

    public void addNode(Node<K, V> newNode) {
      if (newNode == null) {
        return;
      }
      if (head == null) {
        head = newNode;
        tail = newNode;
      } else {
        tail.next = newNode;
        newNode.last = tail;
        tail = newNode;
      }
    }

    //node 入参,一定要保证 node在双向链表里
    private void moveNodeToTail(Node<K, V> node) {
      if (tail == node) {
        return;
      }
      if (this.head == node) {
        this.head = head.next;
        this.head.last = null;
      } else {
        node.last.next = node.next;
        node.next.last = node.last;
      }
      node.last = this.tail;
      node.next = null;
      this.tail.next = node;
      this.tail = node;
    }

    public Node<K, V> removeHead() {
      if (head == null) {
        return null;
      }
      Node<K, V> res = this.head;
      if (this.head == this.tail) {
        this.head = null;
        this.tail = null;
      } else {
        this.head = res.next;
        res.next = null;
        this.head.last = null;
      }
      return res;
    }
  }

  public static class MyCache<K, V> {
    private HashMap<K, Node<K, V>> keyNodeMap;
    private NodeDoubleLinkedList<K, V> nodeList;
    private final int capacity;

    public MyCache(int capacity) {
      this.capacity = capacity;
      keyNodeMap = new HashMap<>();
      nodeList = new NodeDoubleLinkedList<>();
    }

    public V get(K key) {
      if (keyNodeMap.containsKey(key)) {
        Node<K, V> res = keyNodeMap.get(key);
        nodeList.moveNodeToTail(res);
        return res.value;
      }
      return null;
    }

    //可能是新增 也可能是更新
    public void set(K key, V value) {
      if (keyNodeMap.containsKey(key)) {//更新
        Node<K, V> node = keyNodeMap.get(key);
        node.value = value;
        nodeList.moveNodeToTail(node);
      }else {//新增
        Node<K, V> newNode = new Node<>(key, value);
        keyNodeMap.put(key,newNode);
        nodeList.addNode(newNode);
        if(keyNodeMap.size()==capacity+1){
          removeMostUnusedCache();
        }
      }
    }

    private void removeMostUnusedCache() {
      Node<K, V> removeNode = nodeList.removeHead();
      keyNodeMap.remove(removeNode.key);
    }
  }
}
