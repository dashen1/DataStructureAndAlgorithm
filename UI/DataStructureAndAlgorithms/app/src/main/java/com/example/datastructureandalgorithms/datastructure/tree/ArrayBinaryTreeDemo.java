package com.example.datastructureandalgorithms.datastructure.tree;

public class ArrayBinaryTreeDemo {

    public static void main(String[] args) {

        int[] arr={1,2,3,4,5,6,7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(arr);
        System.out.println("前序遍历");
        arrayBinaryTree.preOrder();
        System.out.println("中序遍历");
        arrayBinaryTree.infixOrder();
        System.out.println("后序遍历");
        arrayBinaryTree.postOrder();
    }
}

class ArrayBinaryTree{
    private int[] arr;

    public ArrayBinaryTree(int[] arr) {
        this.arr = arr;
    }

    public void preOrder(){
        preOrder(0);
    }

    public void infixOrder(){
        infixOrder(0);
    }

    public void postOrder(){
        postOrder(0);
    }

    //顺序存储 前序遍历

    /**
     *
     * @param index 数组下标
     */
    public void preOrder(int index){
        if (arr==null||arr.length==0){
            System.out.println("数组为空，不能前序遍历");
        }
        System.out.println(arr[index]);
        if (index*2+1<arr.length){
            preOrder(index*2+1);
        }
        if (index*2+2<arr.length){
            preOrder(index*2+2);
        }
    }

    public void infixOrder(int index){
        if (arr==null||arr.length==0){
            System.out.println("数组为空，不能中序遍历");
        }
        if (index*2+1<arr.length){
            infixOrder(index*2+1);
        }
        System.out.println(arr[index]);
        if (index*2+2<arr.length){
            infixOrder(index*2+2);
        }
    }

    public void postOrder(int index){
        if (arr==null||arr.length==0){
            System.out.println("数组为空，不能后序遍历");
        }
        if (index*2+1<arr.length){
            postOrder(index*2+1);
        }
        if (index*2+2<arr.length){
            postOrder(index*2+2);
        }
        System.out.println(arr[index]);
    }
}
