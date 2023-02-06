package com.example.datastructureandalgorithms.theleftgod.huffman;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Huffman {

    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int j : arr) {
            pq.add(j);
        }
        int sum = 0;
        int cur = 0;
        while (pq.size() > 1) {
            cur = pq.poll() + pq.poll();
            sum += cur;
            pq.add(cur);
        }
        return sum;
    }

    public static class MinHeadComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }
}

