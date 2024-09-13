package com.yc.thread.pro3_BestMatching;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/*
    线程池的任务分配及调度  结果汇总
 */
public class BestMatchingConcurrentCalculation {

    //线程池的任务分配及调度  结果汇总
    public static BestMatchingData getBestMatchingWords(String word, List<String> dictionary) throws ExecutionException, InterruptedException {
        BestMatchingData result = new BestMatchingData();
        //1.任务数
        int numCores = Runtime.getRuntime().availableProcessors();
        //创建线程池                                                     固定线程数的线程池
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool( numCores );
        //2.计算每个任务对应的词汇量  startIndex  endIndex
        int size = dictionary.size();
        int step = size/numCores; //任务的分区大小
        int startIndex , endIndex;
        //3.创建 BestMatchingTask
        //  任务 集  来进行阻塞 防止 主程序结束 等任务执行完毕
        List<BestMatchingTask> tasks = new ArrayList<>();
        for ( int i=0 ; i<numCores ; i++ ){
            startIndex = i * step;
            //endIndex
            if ( i == numCores-1){
                endIndex=size;
            }else {
                endIndex=(i+1)*step;
            }
            BestMatchingTask task = new BestMatchingTask( dictionary , word , startIndex,endIndex);
            tasks.add( task );
        }
        //4.将上面创建的BestMatchingTask 提交给 Executor线程池执行器运行
        //invokeAll()一次性提交所有任务 如果任务没有执行完毕，会阻塞此处
        // ****** 返回每个任务执行完的结果  并阻塞主线程
        List<Future<BestMatchingData>> futureList =  executor.invokeAll( tasks );//一次性提交所有任务并获取结果
        System.out.println("如果任务没有执行完毕，invokeAll阻塞此处");
        executor.shutdown();
        //5.通过 FutureTask.get()获取以上的任务的执行结果  BestMatchingData 汇总( 找最小距离 及 对应的词汇表 )
        int minDistance = Integer.MAX_VALUE;
        //最短距离 对应的单词
        List<String> words = new ArrayList<>();
        for (Future<BestMatchingData> future:futureList){
            BestMatchingData bestMatchingData = future.get();
            if ( bestMatchingData.getDistance() < minDistance){
                words.clear();
                minDistance = bestMatchingData.getDistance();
                words.addAll(  bestMatchingData.getWords() );
            }else if ( bestMatchingData.getDistance() == minDistance){
                words.addAll(  bestMatchingData.getWords() );
            }
        }
        //6.包装成BestMatchingData
        result.setDistance( minDistance );
        result.setWords( words );
        return  result;
    }

}
