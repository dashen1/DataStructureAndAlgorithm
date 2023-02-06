package com.example.algorithmadvanced.head;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * T 一定是非基础类型
 *
 * @param <T>
 */
public class HeadGreater<T> {

  private ArrayList<T> heap;
  private HashMap<T, Integer> indexMap;
  private int heapSize;
  private Comparator<? super T> comp;

  public HeadGreater(Comparator<T> c) {
    heap = new ArrayList<>();
    indexMap = new HashMap<>();
    heapSize = 0;
    comp = c;
  }

  public boolean isEmpty() {
    return heapSize == 0;
  }

  public int size() {
    return heapSize;
  }

  public boolean contains(T obj) {
    return indexMap.containsKey(obj);
  }

  public T peek() {
    return heap.get(0);
  }

  public void push(T obj) {
    heap.add(obj);
    indexMap.put(obj, heapSize);
    heapInsert(heapSize++);
  }

  public void resign(T obj) {
    heapInsert(indexMap.get(obj));
    heapify(indexMap.get(obj));
  }

  public T pop() {
    T ans = heap.get(0);
    swap(0, heapSize - 1);
    heap.remove(--heapSize);
    heapify(0);
    return ans;
  }

  //拿最后一个位置上的数顶替要删除的数的位置，再进行插入和建堆
  public void remove(T obj) {
    T replace = heap.get(heapSize - 1);
    int index = indexMap.get(obj);
    indexMap.remove(obj);
    heap.remove(--heapSize);
    //要删除的数不是最后一个才进行插入和建堆
    while (obj != replace) {
      heap.set(index, replace);
      indexMap.put(replace, index);
      resign(obj);
    }
  }


  public List<T> getAllElements() {
    List<T> ans = new ArrayList<>();
    for (T c : heap) {
      ans.add(c);
    }
    return ans;
  }

  private void heapInsert(int index) {
    while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
      swap(index, (index - 1) / 2);
      index = (index - 1) / 2;
    }
  }

  private void heapify(int index) {
    int left = index * 2 + 1;
    while (left < heapSize) {
      int finalIndex = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
      finalIndex = comp.compare(heap.get(finalIndex), heap.get(index)) < 0 ? finalIndex : index;
      if (finalIndex == index) {
        break;
      }
      swap(finalIndex, index);
      index = finalIndex;
      left = index * 2 + 1;
    }
  }

  private void swap(int i, int j) {
    T o1 = heap.get(i);
    T o2 = heap.get(j);
    heap.set(i, o2);
    heap.set(j, o2);
    indexMap.put(o2, i);
    indexMap.put(o1, j);
  }
}
