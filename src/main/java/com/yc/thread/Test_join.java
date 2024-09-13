package com.yc.thread;

public class Test_join {
    public static void main(String[] args) throws InterruptedException {
        //当前是main线程
        LifeCircle lc = new LifeCircle();
        System.out.println( lc.isAlive() );//线程状态
        lc.start();
        System.out.println(  lc.isAlive() );

//        lc.join();  //让lc先运行完，再执行main， 挂起

        System.out.println("主程序的其它操作。。。");
        System.out.println(  lc.isAlive() );
    }
}
class LifeCircle extends Thread{
    @Override
    public void run() {
        for (int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName() + ": "+i);
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
