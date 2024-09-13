package com.yc.thread.pro2_knn.bean;

/*
    封装 类
    这个类主要用于比较  两个  Distance之间的  大小
 */
public class Distance implements Comparable<Distance>{
    private int index; // 拿测试集中的一条与  data训练集中的 39129条 计算距离  索引号 0-39129
    //第index条数据的距离
    private double distance; //
    //比较当前distance与传进来的参数  o 的大小
    @Override
    public int compareTo(Distance o) {
        if (  this.distance>o.distance ){
            return 1;
        }else if (this.distance == o.distance){
            return 0;
        }
        return -1;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getIndex() {
        return index;
    }

    public double getDistance() {
        return distance;
    }
}
