package com.yc.thread.pro1_matrix.individual;

/*
    计算  result矩阵中[i][j]的每个元素的值  matrix1中对应的列*matrix2对应的行，累加和
 */
public class IndividualMultiplierTask implements Runnable{

    private final double[][] result; //final确保线程安全性
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final int row;
    private final int column;


    /*
        result : 结果矩阵
        matrix1:矩阵A
        matrix2:矩阵B
        i  :  结果矩阵的第i行
        j  :  结果矩阵的第j列
     */
    public IndividualMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int i, int j) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.row  = i;
        this.column=j;
    }

    // 任务
    @Override
    public void run() {
        result[row][column]=0;
        //累加求和  即  一行*一列 每个相乘元素值的和
        for (int k=0;k<matrix1[row].length;k++){
            result[row][column]+=matrix1[row][k]*matrix2[k][column];
        }
    }
}
