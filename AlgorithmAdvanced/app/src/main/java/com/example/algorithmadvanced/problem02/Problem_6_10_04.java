package com.example.algorithmadvanced.problem02;

import java.util.HashMap;
import java.util.HashSet;

public class Problem_6_10_04 {
  public static void main(String[] args) {
    /**
     * 扫地机器人
     */
  }

  interface Robot{
    boolean move();

    void turnLeft();

    void turnRight();

    void clean();
  }

  /**  (0,0) 表示是机器人开始所在的位置 上，右，下，左
   *        -1,0
   *   0,-1  0,0  0,1
   *         1,0
   */
  private static final int[][] ds = {{-1,0},{0,1},{1,0},{0,-1}};

  public static void cleanRoom(Robot robot){
    clean(robot,0,0,0,new HashSet<>());
  }

  /**
   *
   * @param robot 机器人
   * @param x 当前来到的位置 (x,y)
   * @param y 当前来到的位置 (x,y)
   * @param d 机器人脸冲什么方向d, 0 1 2 3
   * @param visited 记录了机器人走过哪些位置
   *                函数的功能：不重复走visited里面的位置，把剩下的位置，都打扫干净
   */
  private static void clean(Robot robot, int x, int y, int d, HashSet<String> visited) {
    //打扫当前位置
    robot.clean();
    visited.add(x+"_"+y);
    for (int i = 0; i < 4; i++) {
      //下一步的方向
      int nd = (i+d)%4;
      //当下一步的方向定了，下一步的位置在哪？(nx,ny)
      int nx = ds[nd][0];
      int ny = ds[nd][1];
      if(!visited.contains(nx+"_"+ny)&&robot.move()){
        clean(robot,nx,ny,nd,visited);
      }
      //所有方向都走一边
      //最后一次for循环回到初始方向
      robot.turnRight();
    }
    //然后顺时针转两次，和初始方向相反，往相反方向走，回到上一步的位置，再调整回上一次位置的初始方向
    robot.turnRight();
    robot.turnRight();
    robot.move();
    robot.turnRight();
    robot.turnRight();
  }
}
