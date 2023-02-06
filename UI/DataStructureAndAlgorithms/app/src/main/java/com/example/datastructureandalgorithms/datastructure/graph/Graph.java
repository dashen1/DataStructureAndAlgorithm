package com.example.datastructureandalgorithms.datastructure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {

    private ArrayList<String> vertexList;
    private int[][] edges;
    private int numOfEdges;

    private boolean[] isVisited;


    public static void main(String[] args) {

        int n = 8;
//        String vertexValue[] = {"A", "B", "C", "D", "E"};
        String vertexValue[] = {"1", "2", "3", "4", "5","6","7","8"};

        Graph graph = new Graph(n);
        for (String value : vertexValue) {
            graph.insertVertex(value);
        }

//        graph.insertEdge(0, 1, 1);
//        graph.insertEdge(0, 2, 1);
//        graph.insertEdge(1, 2, 1);
//        graph.insertEdge(1, 3, 1);
//        graph.insertEdge(1, 4, 1);

        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        graph.showGraph();
        System.out.println("深度遍历==");
        //graph.dfs(0);
        System.out.println();
        //广度优先 不需要回溯 所以不需要递归，直接遍历输出即可
        System.out.println("广度优先");
        graph.bfs(3);

    }

    public Graph(int n) {
        edges = new int[n][n];
        vertexList = new ArrayList<>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    //得到第一个邻接节点的下标
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param v1 第一个节点
     * @param v2 下一个节点
     * @return 下一个节点的兄弟节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edges[v1][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param isVisited
     * @param i         第一次就是0
     */
    public void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i) + "-》");
        //将该节点设置为已访问过
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w这个节点被访问过
            w = getNextNeighbor(i, w);
        }
    }

    //对dfs进行重载 遍历所有的节点，并进行dfs
    public void dfs(int vertex) {
        dfs(isVisited, vertex);
//        for (int i = 0; i < getNumOfVertex(); i++) {
//            if (!isVisited[i]){
//                System.out.println("i="+i);
//                dfs(isVisited,i);
//            }
//        }
    }

    //对一个节点进行广度优先遍历
    private void bfs(boolean[] isVisited, int i) {
        int u;//表示队列的头结点对应的下标
        int w;//邻接节点w
        LinkedList queue = new LinkedList();
        //访问节点，输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u = (Integer) queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {//找到
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                //以u为前驱节点，找w后面的下一个节点
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs(int i) {
        bfs(isVisited, i);
//        for (int i = 0; i < getNumOfVertex(); i++) {
//            if (!isVisited[i]) {
//                System.out.println("i=" + i);
//                bfs(isVisited, i);
//            }
//        }
    }


    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    public int getNumOfVertex() {
        return vertexList.size();
    }

    public int getNumOfEdges() {
        return numOfEdges;
    }

    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    public void showGraph() {
        for (int[] link : edges) {
            System.out.println(Arrays.toString(link));
        }
    }
}
