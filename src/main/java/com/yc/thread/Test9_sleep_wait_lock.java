package com.yc.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
    sleep 和 wait 方法在锁上的区别
 */
public class Test9_sleep_wait_lock {

    public static void main(String[] args) {
        final Test9_sleep_wait_lock test = new Test9_sleep_wait_lock();
        for (int i=0;i<5;i++){
            new Thread( test::sleepMethod ).start();
        }
    }

    // synchronized  wait 方法  释放锁
//    public synchronized void sleepMethod(){
//        System.out.println(printDate()+Thread.currentThread().getName()+"等待1秒");
//        synchronized (this){  //细粒度  同步快
//            try {
//                this.wait(1000);// 释放锁
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        System.out.println(printDate()+Thread.currentThread().getName()+"等待结束");
//    }

    // synchronized  sleep 方法  不释放锁
    public synchronized void sleepMethod(){
        System.out.println(printDate()+Thread.currentThread().getName()+"等待1秒");
//        synchronized (this){  //细粒度  同步快
            try {
                Thread.sleep(1000);// 释放锁
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//        }
        System.out.println(printDate()+Thread.currentThread().getName()+"等待结束");
    }

    private static String printDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date())+" ";
    }

}
