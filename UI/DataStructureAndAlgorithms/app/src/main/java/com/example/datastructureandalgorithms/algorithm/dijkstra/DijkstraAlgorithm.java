package com.example.datastructureandalgorithms.algorithm.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {

    public static void main(String[] args) {

        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};

        int[][] matrix = new int[vertex.length][vertex.length];
        final int N = 65535;
        matrix[0] = new int[]{N, 5, 7, N, N, N, 2};
        matrix[1] = new int[]{5, N, N, 9, N, N, 3};
        matrix[2] = new int[]{7, N, N, N, 8, N, N};
        matrix[3] = new int[]{N, 9, N, N, N, 4, N};
        matrix[4] = new int[]{N, N, 8, N, N, 5, 4};
        matrix[5] = new int[]{N, N, N, 4, 5, N, 6};
        matrix[6] = new int[]{2, 3, N, N, 4, 6, N};

        Graph graph = new Graph(vertex, matrix);
        graph.showGraph();
        graph.dsj(2);
        graph.showDijkstra();
    }

}

class Graph {
    private char[] vertex;
    private int[][] matrix;//邻接矩阵
    private VisitedVertex vv;//已经访问顶点的集合

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showDijkstra() {
        vv.show();
    }

    public void showGraph() {
        for (int[] link : matrix) {
            System.out.println(Arrays.toString(link));
        }
    }

    public void dsj(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);//更新index顶点到周围顶点的距离
        for (int j = 0; j < vertex.length; j++) {
            index = vv.updateArr();//选择并返回新的访问顶点
            update(index);//更新index顶点到周围顶点的距离和前驱顶点
        }
    }


    //更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点
    private void update(int index) {
        int len;
        //根据邻接矩阵遍历matrix[index]行
        for (int i = 0; i < matrix.length; i++) {
            //出发顶点到index顶点的距离+从index顶点到i顶点的距离的和
            len = vv.getDis(index) + matrix[index][i];
            //如果j顶点没有被访问过，并且len值小于出发顶点到j顶点的距离，就需要更新
            if (!vv.in(i) && len < vv.getDis(i)) {
                vv.updatePre(i, index);
                vv.update(i, len);
            }
        }
    }
}

class VisitedVertex {
    //记录各个顶点是否访问过
    public int[] already_arr;//
    //每个下标对应的值为前一个顶点的下标，前驱顶点
    public int[] pre_visited;
    //记录出发顶点到各个顶点的距离
    public int[] dis;


    /**
     * @param length 顶点个数
     * @param index  出发顶点下标
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];


        Arrays.fill(dis, 65535);
        this.already_arr[index] = 1;
        this.dis[index] = 0;
    }

    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    public void update(int index, int len) {
        dis[index] = len;
    }

    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到下标为index的顶点的距离
     *
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择返回新的顶点
     *
     * @return
     */
    public int updateArr() {
        int min = 65536, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        already_arr[index] = 1;
        return index;
    }

    /**
     * 显示最后的结果
     */
    public void show() {
        System.out.println("=================");
        for (int i : already_arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : pre_visited) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : dis) {
            System.out.print(i + " ");
        }
        System.out.println();
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count=0;
        for (int i : dis) {
            if (i!=65535){
                System.out.print(vertex[count]+"("+i+")");
            }else {
                System.out.println("N ");
            }
            count++;
        }
        System.out.println();
    }
}