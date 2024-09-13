package com.yc.thread.pro2_knn;

import com.yc.thread.pro2_knn.bean.BankMarketing;
import com.yc.thread.pro2_knn.group.ParallelGroupKnnClassifier;

import java.util.List;

public class Test2_main {
    public static void main(String[] args) {
        String dataPath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\yc\\thread\\pro2_knn\\data\\bank.data";
        List<BankMarketing> dataList =  BankMarketingLoader.load( dataPath );//训练集数据
        System.out.println("训练集大小:"+dataList.size());

        String testPath = System.getProperty("user.dir")+"\\src\\main\\java\\com\\yc\\thread\\pro2_knn\\data\\bank.test";
        List<BankMarketing> testList =  BankMarketingLoader.load( testPath );//测试集数据
        System.out.println("测试集大小:"+testList.size());

        //knn的k的确定
        int k=10;
        //通过命令行运行这个程序，并传递参数来设置 k 的值 把k的值更新为 25
        // java  Test_main 25
        if (  args!=null && args.length>0){
            k = Integer.parseInt( args[0] );
        }

        //定义两个变量存测试集  通过模型预测准确率
        int success=0,mistaks=0;
        int numThreads = Runtime.getRuntime().availableProcessors();//线程数
        ParallelGroupKnnClassifier classifier = new ParallelGroupKnnClassifier( k,numThreads,true,dataList);

        long start,end;
        start = System.currentTimeMillis();
        //循环测试集中的每一条，调用 这个模型进行预测
        for ( BankMarketing test:testList ){
            //tag就是模型预测的类别 yes/no
            String tag =  classifier.classify(  test  );
            if ( tag.equals( test.getTag() ) ){
                success++;
            }else {
                mistaks++;
            }
        }
        end = System.currentTimeMillis();
        System.out.println("按cpu的核数生产任务的版本，knn算法计算距离:"+(end-start));//16654毫秒
        System.out.println("正确数为:"+success+"错误数为:"+mistaks+",正确率为:"+ ((double)success/(success+mistaks)) );
    }
}
