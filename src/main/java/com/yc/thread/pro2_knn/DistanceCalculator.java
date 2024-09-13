package com.yc.thread.pro2_knn;

import com.yc.thread.pro2_knn.bean.Sample;

/*
    距离计算接口
 */
public interface DistanceCalculator {
    /*
        计算两个样本之间的距离
     */
    public  double calculate(Sample sample1,Sample sample2);


}
