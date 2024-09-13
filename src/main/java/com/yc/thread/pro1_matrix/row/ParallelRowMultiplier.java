package com.yc.thread.pro1_matrix.row;

import com.yc.thread.pro1_matrix.row.RowMultiplierTask;

import java.util.ArrayList;
import java.util.List;

/*
    并行第二版:  结果矩阵中的每一行一个任务
 */
public class ParallelRowMultiplier {

    public static void multiply( double[][] matrix1,double [][] matrix2,double[][] result){
        //用一个集合存线程
        List<Thread> threads = new ArrayList<Thread>();

        int rows1 = matrix1.length;//第一个矩阵的行
        int column1 = matrix1[0].length;//第一个矩阵的列

        int rows2 = matrix2.length;//第二个矩阵的行
        int column2 = matrix2[0].length;//第二个矩阵的列

        for (int i=0;i<rows1;i++){
                //创建  2000个线程
                Thread t = new Thread( new RowMultiplierTask(  result, matrix1, matrix2, i ) );
                t.start();
                threads.add(  t  );
                if ( threads.size()%10 == 0){
                    waitForThread(  threads );
                }
        }
    }
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
