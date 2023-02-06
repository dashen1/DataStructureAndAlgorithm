package com.example.algorithmadvanced.queuestack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TwoQueueImplementStack {

  public static void main(String[] args) {
    TwoStackQueue queue = new TwoStackQueue();
    queue.add(2);
    queue.add(3);
    queue.add(4);
    System.out.println(queue.pop());
    System.out.println(queue.peek());
  }

  public static class TwoQueueStack<T>{
    public Queue<T> queue;
    public Queue<T> help;

    public TwoQueueStack() {
      queue = new LinkedList<>();
      help = new LinkedList<>();
    }

    public void push(T value){
      //return false if insert failed
      queue.offer(value);
    }

    public T poll(){
      while (queue.size()>1){
        help.offer(queue.poll());
      }
      T ans = queue.poll();
      Queue<T> tmp = help;
      help = queue;
      queue = tmp;
      return ans;
    }

    public T peek(){
      while (queue.size()>1){
        help.offer(queue.poll());
      }
      T ans = queue.poll();
      help.offer(ans);
      Queue<T> tmp = help;
      help = queue;
      queue = tmp;
      return ans;
    }
  }

  public static class TwoStackQueue{
    public Stack<Integer> stackPush;
    public Stack<Integer> stackPop;

    public TwoStackQueue() {
      stackPush = new Stack<>();
      stackPop = new Stack<>();
    }

    public void pushToPop(){
      if(stackPop.isEmpty()){
        while (!stackPush.isEmpty()){
          stackPop.push(stackPush.pop());
        }
      }
    }

    public void add(int value){
      stackPush.push(value);
      pushToPop();
    }

    public int pop(){
      if(stackPop.isEmpty()&&stackPush.isEmpty()){
        throw new RuntimeException("Queue is empty!");
      }
      pushToPop();
      return stackPop.pop();
    }

    public int peek(){
      if(stackPop.isEmpty()&&stackPush.isEmpty()){
        throw new RuntimeException("Queue is empty!");
      }
      pushToPop();
      return stackPop.peek();
    }
  }
}
