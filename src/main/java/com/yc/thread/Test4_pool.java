package com.yc.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/*
    线程池
 */
public class Test4_pool {
    public static void main(String[] args) {
        //核心线程池的大小
        int corePoolSize = 3;
        //核心线程池的最大线程数
        int maxPoolSize = 5;
        //线程最大空闲时间  即核心线程池中(maxPoolSize-corePoolSize)  以外  的线程空闲存在时间
        long keepAliveTime = 10 ;
        //空闲时间单位
        TimeUnit unit = TimeUnit.SECONDS; // enue枚举  常量
        //阻塞队列  容量为2  最多允许放入两个  空闲任务
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(2);
        // 线程创建工厂
        ThreadFactory threadFactory = new NameThreadFactory();
        //线程池拒绝策略
        RejectedExecutionHandler handler = new MyIgnorePolicy();

        ThreadPoolExecutor executor = null;
        try {
            //推荐的创建线程池的方式
            //不推荐使用现成的API创建线程池
            executor = new ThreadPoolExecutor(corePoolSize,maxPoolSize,keepAliveTime,unit,workQueue,threadFactory,handler);

            //预启动所有的核心线程 提高效率
            executor.prestartAllCoreThreads();
            //任务数量
            int count = 10 ;
            for (int i = 1;i<=count;i++){
                Task task = new Task(  String.valueOf(i) );
                //线程池中最多同时执行5个任务+2个队列  提交任务到线程池  还有3个任务无法执行
                executor.submit(task);
            }
        }finally {
            assert executor != null;  //断言  可开关  -ea  -da
            executor.shutdown();
        }
    }
    //线程工厂
    static class NameThreadFactory implements ThreadFactory{
        private final AtomicInteger threadId = new AtomicInteger(1);
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r,"线程-"+threadId.getAndIncrement());
            System.out.println(t.getName()+"已经被创建");
            return t;
        }
    }

    /*
        线程池的拒绝策略
     */
    static class MyIgnorePolicy implements RejectedExecutionHandler{
        //  Runnable :被拒绝的任务  ThreadPoolExecutor：线程池对象
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            doLog(r,executor);
        }
        private void doLog(Runnable r, ThreadPoolExecutor executor){
            System.out.println("线程池:"+executor.toString()+r.toString()+"被拒绝执行");
        }
    }

    //任务类
    static class Task implements Runnable{
        private String name ;
        public Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(this.toString()+"is running");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString() {
            return "Task{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
