package com.yc.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test8_ReInterrupted extends Thread{
    public static void main(String[] args) throws InterruptedException {
        //当前线程main
        String threadName = Thread.currentThread().getName();

        Test8_ReInterrupted reInterrupt = new Test8_ReInterrupted();//创建线程
        System.out.println(printDate() + threadName + "线程启动");
        //启动新线程
        reInterrupt.start();

        //main主线程休眠3秒
        Thread.sleep(3000);
        System.out.println(printDate() + threadName + "发出中断信号，设置子线程中断");
        //对新线程设置线程中断
        reInterrupt.interrupt();

        //主线程休眠3秒
        System.out.println(printDate() + threadName + "运行结束");

    }

    @Override
    public void run() {
        //当前线程
        String threadName = Thread.currentThread().getName();
        int i=0;
        //while循环  等待线程中断  只要当前线程不是中断状态 则继续  是中断则退出当前线程
        while (  !Thread.currentThread().isInterrupted() ){
            System.out.println(  printDate() + threadName + "线程正在执行第:"+(++i)+"次");
            try{
                //应该会执行3次
                //线程阻塞，如果线程收到中断操作信号将抛出异常
                Thread.sleep(1000);
            }catch (Exception e){ //因为catch 所以中断信号又恢复成  false
                System.out.println(  printDate() + threadName + "线程正在执行，收到中断信号，进入catch快处理");
                //检测线程是否中断
                System.out.println(  printDate() + threadName + "的状态:"+this.isInterrupted());//false
                //如果需要维护中断状态，则需要重新设置中断状态
                Thread.currentThread().interrupt();//true
            }

        }
        System.out.println(printDate() + threadName + "线程是否被中断："+this.isInterrupted());//true
        System.out.println(printDate() + threadName + "线程退出" );
    }

    private static String printDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date())+" ";
    }
}
