package com.yc.thread.pro1_matrix;

import com.yc.thread.pro1_matrix.individual.ParalleIndividualMultipliter;

/*
    结果矩阵中每个元素一个线程的版本 ： 细料度
 */
public class Test2_ParallelIndividualMain {
    public static void main(String[] args) {
        //生成两个矩阵
        double matrix1[][] = MatriGenerator.generate(2000,2000);
        double matrix2[][] = MatriGenerator.generate(2000,2000);
        //结果矩阵  在矩阵乘法中，两个矩阵 A 和 B 的乘积 C 仅在 A 的列数等于 B 的行数时定义。
        // 假设 A 是一个 m x n 矩阵，B 是一个 n x p 矩阵，则它们的乘积 C 将是一个 m x p 矩阵。
        double result[][] = new double[matrix1.length][matrix2[0].length];

        long start,end;
        start = System.currentTimeMillis();

//        SerialMultiplier.multiply(  matrix1,matrix2,result);//单线程计算矩阵乘法
        ParalleIndividualMultipliter.multiply(matrix1,matrix2,result);//结果矩阵中每个元素一个线程的版本 ： 细料度

        end = System.currentTimeMillis();
        System.out.println("细料度计算矩阵乘法的时间:" + (end-start) );


    }
}
