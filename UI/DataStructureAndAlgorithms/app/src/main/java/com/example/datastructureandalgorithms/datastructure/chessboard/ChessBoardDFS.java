//package com.example.datastructureandalgorithms.datastructure.chessboard;
//
//import java.util.ArrayList;
//
//public class ChessBoardDFS {
//
//    /**
//     * 骑士周游问题
//     * 1.创建chessboard,一个二维数组
//     * 2.将当前位置设置已经访问过，然后根据当前位置，计算马儿还能走到哪些位置，并放到一个集合，最多有8个位置，每走异步，就是要step+1
//     * 3.遍历ArrayList中存放的所有位置，看看那个可以走通，如果可以走通，就继续，走不通，就回溯
//     * 4.判断马儿是否完成了任务，step和应该走的步数比较，如果没有达到数量，则表示没有完成任务，将整个棋盘置0
//     * <p>
//     * 注意：马儿不同的走法，会得到不同的结果，效率也会有影响（优化）
//     *
//     * @param args
//     */
//
//    private static int X;//列
//    private static int Y;//行
//    //创建一个数组，记录棋盘的各个位置是否被访问过
//    private static boolean visited[];
//    //使用一个属性，标记是否棋盘的所有位置都被访问过
//    private static boolean finished;
//
//    public static void main(String[] args) {
//
//        X = 8;
//        Y = 8;
//        //马儿出事位置的行、列
//        int row = 1;
//        int column = 1;
//        //创建棋盘
//        int[][] chessboard = new int[X][Y];
//        visited=new boolean[X*Y];
//        long start = System.currentTimeMillis();
//        traversalChessBoard(chessboard,row-1,column-1,1);
//        long end = System.currentTimeMillis();
//        System.out.println("共耗时："+(end-start)+" 毫秒");
//        //输出棋盘的最后情况
//        for (int[] rows : chessboard) {
//            for (int step : rows) {
//                System.out.println(step+"\t");
//            }
//            System.out.println();
//        }
//    }
//
//    /**
//     * @param chessboard
//     * @param row        马儿当前的行
//     * @param column     马儿当前的列
//     * @param step       是第几步，出事位置就是第1步
//     */
//    public static void traversalChessBoard(int[][] chessboard, int row, int column, int step) {
//        chessboard[row][column] = step;
//        visited[row * X + column] = true;
//        //获取当前位置可以走的下一个位置的集合
//        ArrayList<Point> ps = next(new Point(column, row));
//        while (!ps.isEmpty()) {
//            Point p = ps.remove(0);
//            if (!visited[p.y * X + p.x]) {
//                traversalChessBoard(chessboard, p.y, p.x, step + 1);
//            }
//        }
//        //1.棋盘到目前位置，仍然没有走完
//        if (step < X * Y && !finished) {
//            chessboard[row][column] = 0;
//            visited[row * X + column] = false;
//        } else {
//            finished = true;
//        }
//    }
//
//    /**
//     * 根据当前的位置 计算马儿还能走哪些位置
//     *
//     * @param curPoint
//     * @return
//     */
//    public static ArrayList<Point> next(Point curPoint) {
//        ArrayList<Point> points = new ArrayList<>();
//
//        Point point = new Point();
//        //表示马儿可以走5这个位置
//        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
//            points.add(new Point(point));
//        }
//        //表示马儿可以走6这个位置
//        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
//            points.add(new Point(point));
//        }
//        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0) {
//            points.add(new Point(point));
//        }
//        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0) {
//            points.add(new Point(point));
//        }
//        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y) {
//            points.add(new Point(point));
//        }
//        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y) {
//            points.add(new Point(point));
//        }
//        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y) {
//            points.add(new Point(point));
//        }
//        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y) {
//            points.add(new Point(point));
//        }
//        return points;
//    }
//}
