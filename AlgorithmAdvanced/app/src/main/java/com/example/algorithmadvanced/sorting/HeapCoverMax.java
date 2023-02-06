package com.example.algorithmadvanced.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapCoverMax {

  public static void main(String[] args) {

    Line l1 = new Line(4, 9);
    Line l2 = new Line(1, 4);
    Line l3 = new Line(7, 15);
    Line l4 = new Line(2, 4);
    Line l5 = new Line(4, 6);
  }

  public static class Line {
    public int start;
    public int end;

    public Line(int start, int end) {
      this.start = start;
      this.end = end;
    }
  }

  public static class EndComparator implements Comparator<Line> {

    @Override
    public int compare(Line o1, Line o2) {
      return o1.end - o2.end;
    }
  }

  public static int maxCover2(int[][] m) {
    Line[] lines = new Line[m.length];
    for (int i = 0; i < m.length; i++) {
      lines[i] = new Line(m[i][0], m[i][1]);
    }
    Arrays.sort(lines, new EndComparator());

    PriorityQueue<Integer> heap = new PriorityQueue<>();
    int max = 0;
    for (int i = 0; i < lines.length; i++) {
      while (!heap.isEmpty() && heap.peek() <= lines[i].start) {
        heap.poll();
      }
      heap.add(lines[i].end);
      max = Math.max(max, heap.size());
    }
    return max;
  }
}
