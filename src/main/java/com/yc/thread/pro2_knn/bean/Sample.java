package com.yc.thread.pro2_knn.bean;

/*
    样例数据的javabean
 */
public abstract class Sample {
    //标签 yes/no
    public abstract String getTag(); //data和test中的标签部分

    public abstract double[] getExample();//data和test中的数据部分

}
