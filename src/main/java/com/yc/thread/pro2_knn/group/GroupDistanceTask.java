package com.yc.thread.pro2_knn.group;

import com.yc.thread.pro2_knn.DistanceCalculator;
import com.yc.thread.pro2_knn.EuclideanDistanceCalculator;
import com.yc.thread.pro2_knn.bean.BankMarketing;
import com.yc.thread.pro2_knn.bean.Distance;
import com.yc.thread.pro2_knn.bean.Sample;

import java.util.List;

public class GroupDistanceTask implements Runnable{

    private Distance[] distances;
    private int startIndex;
    private int endIndex;
    private List<BankMarketing> dataSet;
    private  Sample example;

    private DistanceCalculator edc = new EuclideanDistanceCalculator();

    public GroupDistanceTask(Distance[] distances, int startIndex, int endIndex, List<BankMarketing> dataSet, Sample example) {
        this.distances = distances;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.dataSet = dataSet;
        this.example = example;
    }

    public GroupDistanceTask(Distance[] distances, int startIndex, int endIndex, List<BankMarketing> dataSet, Sample example, DistanceCalculator edc) {
        this.distances = distances;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.dataSet = dataSet;
        this.example = example;
        this.edc = edc;
    }

    public void setEdc(DistanceCalculator edc) {
        this.edc = edc;
    }

    /*
        任务类:
            计算  sample样本数据 39129条  与  一条测试数据 example 的 距离
            即每条样本数据与测试数据的距离
            存 到  Distance 封装类中
     */
    @Override
    public void run() {
        //循环startIndex到endIndex  计算  example与dataSet中各条数据的距离
        for (int index=startIndex;index<endIndex;index++){
            Sample sample = dataSet.get( index ); //样本数据 39129条
            distances[index] = new Distance();// new 39129个
            distances[index].setDistance( index );//样本数据的索引号
            distances[index].setDistance(  this.edc.calculate( sample , example )  );//每条样本数据与测试数据的距离
        }
    }
}
