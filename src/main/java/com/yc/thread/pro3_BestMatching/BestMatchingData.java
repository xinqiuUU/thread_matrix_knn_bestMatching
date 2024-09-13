package com.yc.thread.pro3_BestMatching;

import java.util.List;

/*
    编辑距离最短的单词列表
    查: sit => 1
       出现:  sid  sim sin  sis ...
 */
public class BestMatchingData {
    private int distance ; //最短距离
    private List<String> words; //这个距离  对应的单词列表

    public int getDistance() {
        return distance;
    }

    public List<String> getWords() {
        return words;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }
}
