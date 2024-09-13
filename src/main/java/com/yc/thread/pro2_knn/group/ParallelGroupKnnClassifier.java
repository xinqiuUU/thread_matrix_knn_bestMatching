package com.yc.thread.pro2_knn.group;

import com.yc.thread.pro2_knn.bean.BankMarketing;
import com.yc.thread.pro2_knn.bean.Distance;
import com.yc.thread.pro2_knn.bean.Sample;

import java.util.*;

/*
    knn算法的任务调度类
 */
public class ParallelGroupKnnClassifier {
    private int k; //knn的k，表示近邻几个
    private int numThreads;//线程数
    private boolean parallelSort;//排序算法并行

    private List<BankMarketing> dataSet;//训练集  39129条  ，拿一条测试数据和它计算距离

    public ParallelGroupKnnClassifier(int k, int numThreads, boolean parallelSort, List<BankMarketing> dataSet) {
        this.k = k;
        this.numThreads = numThreads;
        this.parallelSort = parallelSort;
        this.dataSet = dataSet;
    }

    public String classify( Sample example ){
        Distance[] distances = new Distance[  dataSet.size() ];
        //1.计算  12 线程中每个线程它的计算任务  startIndex ,endIndex
        int length = dataSet.size()/numThreads; // 分段区间: 3,260
        int startIndex=0;
        int endIndex=length;
        List<Thread> list = new ArrayList<>();
        //2.根据numThread 创建任务  并绑定到线程上
        for (int i=0;i<numThreads;i++){
            //计算 example这个条测试数据与 dataSet中39129条数据的距离(只要计算 startIndex-endIndex),将这个结果存到distances
            GroupDistanceTask task = new GroupDistanceTask( distances , startIndex ,endIndex , dataSet ,example );
            Thread t = new Thread(  task );
            t.start();
            list.add(  t );
            //计算下一个线程的范围
            startIndex=endIndex;
            endIndex =  i==numThreads-2? dataSet.size()  :  endIndex+length;
//            System.out.println("第"+i+"个线程的计算范围:"+startIndex+"-"+endIndex);
        }
        //3.调用每个线程的join()  让主线程停止 好计算时间
        for (Thread t:list){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //4.排序距离
        if ( parallelSort ){
            Arrays.parallelSort( distances );
        }else {
            Arrays.sort( distances );
        }
        //5.将前k个样本的  标签存到一个Map<String,Integer>
        //                            <标签名,次数>
        Map<String,Integer> results = new HashMap<>();
        for (int i=0;i<k;i++){
            Sample sample =  dataSet.get( distances[i].getIndex() );
            // sample  getTag() -> 获取标签  getExample()->获取数据
            String tag = sample.getTag(); // 标签: yes/no 计算前k条数据的yes和no的数量
//            if (result.containsKey( tag ) ){ //判断map中是否有这个键
//                result.put(  tag , result.get(tag)+1 );
//            }else {
//                result.put( tag , 1 );
//            }
            //新写法
//            result.merge(tag,1,(a,b)->a+b);//a原来的值 ，b:1
            results.merge(tag, 1, Integer::sum); //Integer::sum 是一个方法引用，用于将现有值和新值相加
        }
        //6. 取出map中次数最多的标签名并返回
        //传统写法
//        Set<Map.Entry<String,Integer>> set = result.entrySet();
//        int max = 0;
//        String maxTag = "";
//        for (Map.Entry<String,Integer> entry: set ){
//            String tag = entry.getKey();
//            int value = entry.getValue();
//            if (value>max){
//                maxTag = tag;
//            }
//        }
//        return maxTag;
// Collections工具类 对集合进行操作(排序、查找、最值。。。)  第一个参数:集合 第二个参数:比较器,按什么比较    返回 T  T.getKey()
        return Collections.max( results.entrySet()  , Map.Entry.comparingByValue() ).getKey();
    }
}
