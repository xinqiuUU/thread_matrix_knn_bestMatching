package com.yc.thread.pro1_matrix.single;
/*
    串行的乘法器
 */
public class SerialMultiplier {
    public static void multiply( double[][] matrix1,double [][] matrix2,double[][] result){
        int rows1 = matrix1.length;//第一个矩阵的行
        int column1 = matrix1[0].length;//第一个矩阵的列

        int rows2 = matrix2.length;//第二个矩阵的行
        int column2 = matrix2[0].length;//第二个矩阵的列

        //  row1:2000行
        for (int i=0;i<rows1;i++){

            for (int j=0;j<column2;j++){
                result[i][j]=0;
                for (int k=0;k<column1;k++){
                    result[i][j]+=matrix1[i][k]*matrix2[k][j];
                }
            }

        }
    }
}
