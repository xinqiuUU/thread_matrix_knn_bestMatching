package com.yc.thread.pro1_matrix.group;

/*
    按固定分组(按cpu的核数)来分配任务
 */
public class GroupMultiplierTask implements Runnable{
    private final double[][] result; //final确保线程安全性 内容可变，地址不可变
    private final double[][] matrix1;
    private final double[][] matrix2;

    //行  的  范围
    private final int startIndex; // 起始行
    private final int endIndex; //终止行

    public GroupMultiplierTask(double[][] result, double[][] matrix1, double[][] matrix2, int startIndex, int endIndex) {
        this.result = result;
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    /*
        需要执行的任务
        计算 startIndex行到endIndex的矩阵的值
     */
    @Override
    public void run() {
        for (int i = startIndex;i<endIndex;i++){
            for (int j=0;j<matrix2[0].length;j++){
                result[i][j]=0;
                for (int k=0;k<matrix1[i].length;k++){
                    result[i][j]+=matrix1[i][k]*matrix2[k][j];
                }

            }

        }
    }
}
