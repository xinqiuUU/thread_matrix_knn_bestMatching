package com.yc.thread.pro1_matrix;

import java.util.Random;

/*
    矩阵生成器
 */
public class MatriGenerator {

    /*
        生成rows行  cols的矩阵
     */
    public static double[][] generate( int rows,int cols){
        double[][] ret = new double[rows][cols];
        Random r = new Random();
        for (int i=0;i<rows;i++){
            for (int j=0;j<cols;j++){
                ret[i][j] = r.nextDouble()*10;
            }
        }
        return ret;
    }


}
