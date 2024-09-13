package com.yc.thread.pro2_knn;

import com.yc.thread.pro2_knn.bean.BankMarketing;
import com.yc.thread.pro2_knn.bean.Sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Test_main {
    public static void main(String[] args) {
        //问题：路径：
        //System.getProperty("user.home");  用户目录: c:\\users\zy  /home/用户名
        //用System.getProperty("user.dir");  项目的执行路径
        //   输出 C:\ideaprojects\gitee_thread1
        System.out.println(  System.getProperty("user.dir")  );
        //\src\main\java\com\yc\thread\pro2_knn\data
        String path = System.getProperty("user.dir")+"\\src\\main\\java\\com\\yc\\thread\\pro2_knn\\data\\bank.data";
        System.out.println(path);
        List<BankMarketing> list =  BankMarketingLoader.load( path );
        for (BankMarketing bm:list){
            System.out.println(  bm  );
        }
        System.out.println(  list.size() );

        EuclideanDistanceCalculator edc = new EuclideanDistanceCalculator();
        System.out.println(edc.calculate(new Student(20,1.6),new Student(50,1.7)));;

        //对数组排序
        int[] x = new int[]{9,8,10,5};
//        Arrays.sort( x );  // 单线程排序
        Arrays.parallelSort( x ); //并行多核排序
        //对集合排序
        List<Integer> l = new ArrayList<>();
        l.add(1);
        l.add(59);
        l.add(12);
        Collections.sort(  l  );// 单线程排序

    }

}




//测试欧式距离计算算法
class Student extends Sample{
    private double weight;
    private double heighe;

    public Student(double weight, double heighe) {
        this.weight = weight;
        this.heighe = heighe;
    }

    @Override
    public String getTag() {
        return null;
    }

    @Override
    public double[] getExample() {
        return new double[]{ weight,heighe };
    }
}