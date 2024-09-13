package com.yc.thread;

public class Test_threadgroup {
    public static void main(String[] args) {
        TestThread task1 = new TestThread();
        TestThread task2 = new TestThread();

        ThreadGroup threadGroup = new ThreadGroup("线程组一");

        Thread t1 = new Thread(threadGroup,task1);
        Thread t2 = new Thread(threadGroup,task2);
        t1.start();
        t2.start();
        //通过线程组来管理线程
        System.out.println("活动的线程数为:"+threadGroup.activeCount());
        System.out.println("线程组的名称:"+threadGroup.getName());
        //线程组中断 ，则这个组中所有的线程全部中断
        threadGroup.interrupt(); //发中断信号

    }
}
class TestThread implements Runnable{

    @Override
    public void run() {
        try{
            while(  !Thread.currentThread().isInterrupted() ){ // 发中断信号  interrupt()
                System.out.println("线程名:"+Thread.currentThread().getName());
                Thread.sleep(3000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}