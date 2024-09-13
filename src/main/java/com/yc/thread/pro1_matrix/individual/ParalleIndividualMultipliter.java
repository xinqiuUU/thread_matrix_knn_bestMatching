package com.yc.thread.pro1_matrix.individual;

import java.util.ArrayList;
import java.util.List;

/*
    并行的针对结果矩阵中每个元素一个线程的  乘法器
 */
public class ParalleIndividualMultipliter {
    public static void multiply( double[][] matrix1,double [][] matrix2,double[][] result){
        //用一个集合存线程
        List<Thread> threads = new ArrayList<Thread>();

        int rows1 = matrix1.length;//第一个矩阵的行
        int column1 = matrix1[0].length;//第一个矩阵的列

        int rows2 = matrix2.length;//第二个矩阵的行
        int column2 = matrix2[0].length;//第二个矩阵的列

        for (int i=0;i<rows1;i++){
            for (int j=0;j<column2;j++){
                //创建 2000 * 2000 = 4000000 个 线程
                Thread t = new Thread( new IndividualMultiplierTask(  result, matrix1, matrix2, i , j) );
                t.start();
                threads.add(  t  );
                //防止一次创建太多线程  控制线程的并发数量 一次创建10个
                if ( threads.size()%10 == 0){
                    waitForThread(  threads );
                }
            }
        }
    }
    //防止一次创建太多线程  控制线程的并发数量 一次创建10个
    private static void waitForThread(List<Thread> threads) {
        for (Thread t:threads){
            try {
                t.join(); // t 先运行，其它 的 线程等
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
