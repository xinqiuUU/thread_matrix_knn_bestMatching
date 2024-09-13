package com.yc.thread;

public class Test6_isInterrupted {
    public static void main(String[] args) {
        //当前线程
        Thread thread =Thread.currentThread();
        //检测当前线程是否被中断
        System.out.println(thread.getName() + "线程是否中断："+thread.isInterrupted());//false
        //发一个中断信号
        thread.interrupt();
        System.out.println(thread.getName() + "线程是否中断："+thread.isInterrupted());//true
        //检测线程中断状态是否被清除
        System.out.println(thread.getName() + "线程是否中断："+thread.isInterrupted());//true

        try {
            //线程休眠2s
            Thread.sleep(2000);//本来要在main线程中休眠2s的，但因为interrupt()被调用了，所以sleep被打断
            System.out.println(thread.getName()+"线程休眠未被中断。。。");
        } catch (Exception e) {  //**** 异常捕获操作也会恢复 中断状态
            //捕获到sleep被打断异常
           e.printStackTrace();
           System.out.println(thread.getName()+"线程休眠被中断。。。");
           //判断线程是否被中断，因为异常已经处理完，所以状态恢复  扩展：在catch写处理代码
           System.out.println(thread.getName() + "线程是否中断："+thread.isInterrupted());//false
        }
        System.out.println(thread.getName() + "线程是否中断："+thread.isInterrupted());//false
    }
}
