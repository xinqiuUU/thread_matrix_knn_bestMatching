package com.yc.thread.pro1_matrix.group;

import java.util.ArrayList;
import java.util.List;

public class ParallelGroupMultiplier {
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        //用一个集合存线程
        List<Thread> threads = new ArrayList<Thread>();

        int rows1 = matrix1.length;//第一个矩阵的行
        int column1 = matrix1[0].length;//第一个矩阵的列

        int rows2 = matrix2.length;//第二个矩阵的行
        int column2 = matrix2[0].length;//第二个矩阵的列

        //获取cpu的核数
        Runtime r = Runtime.getRuntime(); //静态单例模型 -> Runtime代表当前运行的jvm环境
        //cpu核数
        int numThreads = r.availableProcessors();
        System.out.println("总共有:"+numThreads+"个核。。。");
        //计算 每个 线程要执行运算的行的起始和终止
        int startIndex,endIndex,step;
        step = rows1/numThreads;
        startIndex = 0;
        endIndex = step;
        //循环numThreads次，每次启动一个线程，指定此线程运行的范围
        for (int i=0;i<numThreads;i++){
            //启动任务
            GroupMultiplierTask gmt = new GroupMultiplierTask(result,matrix1,matrix2,startIndex,endIndex);
            Thread  t = new Thread(gmt);
            t.start();
            threads.add(t);
            //计算下一个线程的范围
            startIndex=endIndex;
            /*
                rows1 = 2000行 直接把endIndex的值设为最后一行的下标
                i==12-2 即运行第10个值的时候为第11个值做准备
             */
            endIndex =  i==numThreads-2? rows1  :  endIndex+step;
            System.out.println("第"+i+"个线程的计算范围:"+startIndex+"-"+endIndex);
        }
        // 等待所有线程完成
        for (Thread t:threads){
            try {
                t.join(); //t 先运行，其它的线程等待
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
