package com.yc.thread.pro3_BestMatching;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("当前项目路径:"+ System.getProperty("user.dir"));
        String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\yc\\thread\\pro3_BestMatching\\UK Advanced Cryptics Dictionary.txt";
        System.out.println("词汇表路径:"+path);
        List<String> dataList =  WordsLoader.load(path);
        System.out.println( dataList.size() );

        String word = "sou";

        //带返回值的任务类
        BestMatchingTask task = new BestMatchingTask( dataList , word ,0, dataList.size());
        FutureTask<BestMatchingData> futureTask = new FutureTask<>( task );
        Thread t = new Thread(  futureTask );
        t.start();
        //get()阻塞式方法，只有任务执行完了，才可以得到结果，程序继续向下
        BestMatchingData bestMatchingData = futureTask.get();
        System.out.println( bestMatchingData.getDistance() + "  "+ bestMatchingData.getWords() );

    }
}
