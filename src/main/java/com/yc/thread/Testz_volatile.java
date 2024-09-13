package com.yc.thread;

public class Testz_volatile {
    public static void main(String[] args) {
        Task t1 = new Task();
        Thread thread1= new Thread(t1,"线程1");
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //想控制thread1的运行，只要改  stop的值 为 true
                try {
                    Thread.sleep(1000);
                    System.out.println("在线程2中改变  stop的值 以停止thread1");
                    t1.stop = true;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread2.start();
    }
}
class Task implements Runnable{
    /*
        volatile 才能使其它线程停止工作
        不加只是改变了主空间 的 stop 值 但子线程的工作空间中的stop没改变
     */
    volatile boolean stop = false;//标量，用于标识当前线程运行的状态
    int i=0;

    @Override
    public void run() {
        long start = System.currentTimeMillis();//当前毫秒数
        while (  !stop ){
            i++;
        }
        System.out.println("线程退出，运行时间:"+(System.currentTimeMillis()-start));
    }
}