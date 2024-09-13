package com.yc.thread;

public class Test1_Thread {
    public static void main(String[] args) {

        MyThread myThread = new MyThread();
        myThread.start();  //线程启动用 start() 调用new谁的run()
        // new MyThread().start()

        //内部类
        InnerThread it = new InnerThread();
        it.start();  //
    }

    // 方案二  内部类
    static class InnerThread extends Thread{
        @Override
        public void run() {
            for (int i=0;i<=100;i++){
                System.out.println("内部类中的i的值为:"+i);
                try {
                    Thread.sleep(1000);//1s
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
/*
    方案一：外部类 写一个类继承自Thread  重写run()方法 在这个方法中加入耗时的操作或阻塞操作
 */
class MyThread extends Thread{
    //run()加入耗时的操作或阻塞操作
    @Override
    public void run() {
        for (int i=0;i<=100;i++){
            System.out.println("外部类中的i的值为:"+i);
            try {
                Thread.sleep(1000);//1s
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}