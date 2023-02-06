package com.example.datastructureandalgorithms.datastructure.arrays;

public class SparseArray {
    public static void main(String[] args) {

        int[][] arrTwo = {
                {0,0,0,0},
                {0,1,0,0},
                {0,0,2,0},
                {0,0,0,0},
        };

        int row = arrTwo.length;
        int column = arrTwo[0].length;
        int valueCount = 0;
        for (int i = 0; i < arrTwo.length; i++) {
            for (int j = 0; j < arrTwo[i].length; j++) {
                if (arrTwo[i][j] != 0) {
                    valueCount++;
                }
            }
        }

        int[][] newArr = new int[valueCount+1][3];
        newArr[0][0] = row;
        newArr[0][1] = column;
        newArr[0][2] = valueCount;
        int newRow = 1;
        for (int i = 0; i < arrTwo.length; i++) {
            for (int j = 0; j < arrTwo[i].length; j++) {
                if (arrTwo[i][j] != 0) {
                    newArr[newRow][0] = i;
                    newArr[newRow][1] = j;
                    newArr[newRow][2] = arrTwo[i][j];
                    newRow++;
                }
            }
        }
        //printArray(newArr);
        chess();
    }

    public static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]+" ");
            }
            System.out.println("");
        }
    }

    public static void chess(){
        //创建一个原始的二维数组 11*11
        //0表示没有棋子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        //输出原始二维数组
        System.out.println("原始数组输出：");
        for (int[] row:chessArr1) {
            for (int data:row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        //将二维数组转稀疏数组
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        //3.遍历二维数组，将非0数据放到稀疏数组
        int count = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        for (int i = 0; i < sparseArr.length; i++) {
            for (int j = 0; j < sparseArr[i].length; j++) {
                System.out.printf("%d\t", sparseArr[i][j]);
            }
            System.out.println();
        }

        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        System.out.println("回复后的数组：");

        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        for (int[] row:chessArr2) {
            for (int data:row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
