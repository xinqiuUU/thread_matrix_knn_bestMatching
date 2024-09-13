package com.yc.thread.pro3_BestMatching;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

// 任务  执行 类
public class BestMatchingTask implements Callable<BestMatchingData> {
    private List<String> words; //词汇表
    private String word;//要匹配的单词
    private int startIndex;
    private int endIndex;

    public BestMatchingTask(List<String> words, String word, int startIndex, int endIndex) {
        this.words = words;
        this.word = word;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    //任务执行函数
    @Override
    public BestMatchingData call() throws Exception {
        //最短路径 单词列表
        List<String>  minDistanceWords = new ArrayList<>();
        //最短路径
        int minDistance = Integer.MAX_VALUE;
        int distance; //计算的距离
        //循环words集合 从  startIndex  到  endIndex 计算  word与这些单词之间的编辑距离
        for (int i=startIndex;i<endIndex;i++){
            //记录最小的编辑距离  及 它对应的单词列表
            distance = EditDistance.calculate(  word , words.get( i ));
            if (  distance < minDistance ){
                minDistanceWords.clear(); //先清空 原来的集合
                minDistance = distance ;  //记录新的最短距离
                minDistanceWords.add(  words.get(i) );//存这个新的最短距离 对应的单词列表
            }else if (  distance == minDistance ){
                minDistanceWords.add( words.get(i)  );//如果这个单词与原来的最短距离 单词一样的距离 直接存
            }
        }
        //睡眠一秒测试是否阻塞了
        Thread.sleep(1000);
        //将最小的编辑距离  及  单词列表 包装成  BestMatchingData对象返回
        BestMatchingData result = new BestMatchingData();
        //最短路径 单词列表
        result.setWords(  minDistanceWords  );
        //最短路径
        result.setDistance( minDistance );

        return result;
    }
}
