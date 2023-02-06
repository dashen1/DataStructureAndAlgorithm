package com.example.datastructureandalgorithms.theleftgod.huffman;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Comparator;
import java.util.PriorityQueue;

public class IPO {

    public static class Node {
        public int p;
        public int c;

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    public static class MaxProfileComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int findMaximizedCapital(int k, int w, int[] profiles, int[] capital) {
        PriorityQueue<Node> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfileQ = new PriorityQueue<>(new MaxProfileComparator());
        for (int i = 0; i < profiles.length; i++) {
            minCostQ.add(new Node(profiles[i], capital[i]));
        }
        //进行k轮
        for (int i = 0; i < k; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= w) {// w 初始资金
                maxProfileQ.add(minCostQ.poll());
            }
            if (maxProfileQ.isEmpty()) {
                return w;
            }
            w += maxProfileQ.poll().p;
        }
        return w;
    }
}
