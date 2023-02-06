package com.example.algorithmadvanced.tree;

import java.math.RoundingMode;

public class PrefixTree {

  public static void main(String[] args) {

  }

  public static class Tree {
    private Node root;

    public Tree() {
      root = new Node();
    }

    public void insert(String word) {
      if (word == null) {
        return;
      }
      char[] str = word.toCharArray();
      Node node = root;
      node.pass++;
      int path = 0;
      for (int i = 0; i < str.length; i++) {
        path = str[i] - 'a';
        if (node.nexts[path] == null) {
          node.nexts[path] = new Node();
        }
        node = node.nexts[path];
        node.pass++;
      }
      node.end++;
    }

    public int search(String pre) {
      char[] str = pre.toCharArray();
      Node node = root;
      int index = 0;
      for (int i = 0; i < str.length; i++) {
        index = str[i] - 'a';
        if (node.nexts[index] == null) {
          return 0;
        }
        node = node.nexts[index];
      }
      return node.end;
    }

    public int prefixNumber(String pre) {
      if (pre == null) {
        return 0;
      }
      char[] str = pre.toCharArray();
      Node node = root;
      int index = 0;
      for (int i = 0; i < str.length; i++) {
        index = str[i] - 'a';
        if (node.nexts[index] == null) {
          return 0;
        }
        node = node.nexts[index];
      }
      return node.pass;
    }

    public void delete(String word) {
      if (search(word) != 0) {
        char[] str = word.toCharArray();
        Node node = root;
        node.pass--;
        int path = 0;
        for (int i = 0; i < str.length; i++) {
          path = str[i] - 'a';
          if (--node.nexts[path].pass == 0) {
            node.nexts[path] = null;
            return;
          }
          node = node.nexts[path];
        }
        node.end--;
      }
    }

  }

  public static class Node {
    public int pass;
    public int end;
    public Node[] nexts;

    public Node() {
      pass = 0;
      end = 0;
      nexts = new Node[26];
    }


  }
}
