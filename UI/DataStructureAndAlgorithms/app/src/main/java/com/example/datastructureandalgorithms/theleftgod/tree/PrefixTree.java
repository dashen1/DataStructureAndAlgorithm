package com.example.datastructureandalgorithms.theleftgod.tree;

public class PrefixTree {


}

class Tree{
    private Node root;

    public Tree() {
        root = new Node();
    }

    public void insert(String str){
        if(str == null || str.length() ==0){
            return;
        }
        Node node = root;
        root.pass++;
        char[] chars = str.toCharArray();
        for (char aChar : chars) {
            int index = aChar - 'a';
            if (node.nexts[index] == null) {
                node.nexts[index] = new Node();
            }
            node = node.nexts[index];
            node.pass++;
        }
        node.end++;
    }

    //word 这个单词加入过几次
    public int search(String word){
        if(word == null){
            return 0;
        }
        char[] chars = word.toCharArray();
        Node node = root;
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            if(node.nexts[index] == null){
                return 0;
            }
            node = node.nexts[index];
        }
        return node.end;
    }

    public void delete(String word){
        if(word == null){
            return;
        }
        if (search(word) == 0){
            return;
        }
        Node node = root;
        node.pass--;
        char[] chars = word.toCharArray();
        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            index = chars[i] - 'a';
            if(--node.nexts[index].pass == 0){
                node.nexts[index] = null;
                return;
            }
            node = node.nexts[index];
        }
        node.end--;
    }
}

class Node{
    public int pass;
    public int end;
    public Node[] nexts;

    public Node() {

        pass = 0;
        end = 0;
        nexts = new Node[26];
    }
}