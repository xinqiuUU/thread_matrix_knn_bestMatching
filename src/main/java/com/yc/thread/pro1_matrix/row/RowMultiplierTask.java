package com.yc.thread.pro1_matrix.row;

/*
    基于结果矩阵行的任务类: 结果矩阵  每一行一个任务
    总共产生2000个线程
 */
public class RowMultiplierTask implements Runnable{
    private final double[][] result; //final确保线程安全性 内容可变，地址不可变
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int row;

    public RowMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int row) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row = row;
    }

    @Override
    public void run() {

        //每个 i  创建一个线程，所以总共有2000个线程
        for (int j=0;j<matrix2[0].length;j++){
            result[row][j]=0;
            //累加求和  即  一行*一列 每个相乘元素值的和
            for (int k=0;k<matrix1[row].length;k++){
                result[row][j]+=matrix1[row][k]*matrix2[k][j];
            }

        }

    }
}
