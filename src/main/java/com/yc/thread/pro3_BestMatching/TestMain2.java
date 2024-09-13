package com.yc.thread.pro3_BestMatching;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TestMain2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("当前项目路径:"+ System.getProperty("user.dir"));
        String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\yc\\thread\\pro3_BestMatching\\UK Advanced Cryptics Dictionary.txt";
        System.out.println("词汇表路径:"+path);
        List<String> dataList =  WordsLoader.load(path);
        System.out.println( "词汇表总量:" + dataList.size() );

        Date start = new Date();
        //测试单词
        String word = "sunday";
        System.out.println("要查询的单词:"+word);

        BestMatchingData result = BestMatchingConcurrentCalculation.getBestMatchingWords( word , dataList);
        Date end = new Date();
        int minDistance = result.getDistance();
        List<String> wordList = result.getWords();
        System.out.println("最短距离:"+minDistance);
        for ( String s:wordList){
            System.out.print( s + "\t");
        }
        System.out.println();
        System.out.println("最长执行时长为:"+(end.getTime()-start.getTime()));//1073毫秒
    }
}
