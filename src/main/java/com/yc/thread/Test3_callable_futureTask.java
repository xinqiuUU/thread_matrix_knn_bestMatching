package com.yc.thread;

import java.util.concurrent.*;

/*
    带返回值的任务类
 */
public class Test3_callable_futureTask {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        //方法一 内部类
        FutureTask<Integer> task1 = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int count = 0;
                for (int i=0;i<10;i++){
                    Thread.sleep(1000);
                    count+=i;
//                    if (i==5){
//                        throw new RuntimeException("error");
//                    }
                }
                return count;
            }
        });
        //将任务与线程绑定
        Thread t1 = new Thread(task1);
        t1.start();

        //方法二   lambda表达式
        FutureTask<Integer> task2 = new FutureTask<>(  ()->{
            int count = 0;
            for (int i=0;i<10;i++) {
                Thread.sleep(1000);
                count += i;
            }
            return count;
        });
        //将任务与线程绑定
        Thread t2 = new Thread(task2);
        t2.start();

        //有返回值  无限等待
        System.out.println("累计和为:"+ task1.get( ) );
        System.out.println("累计和为:"+ task2.get( ) );
        //等5秒
//        System.out.println("累计和为:"+ task1.get( 5, TimeUnit.SECONDS ) );
//        System.out.println("累计和为:"+ task2.get( 5, TimeUnit.SECONDS ) );
    }
}
