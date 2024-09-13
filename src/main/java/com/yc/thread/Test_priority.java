package com.yc.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
    priority 优先级 （1-10）
    只是理论上调度的机会多一些，不保证
 */
public class Test_priority {
    public static void main(String[] args) {
        ShowTime2 task = new ShowTime2();

        Thread s1 = new Thread(task);
        s1.setPriority(10);
        s1.setName("线程1");//线程名
        s1.start();

        Thread s2 = new Thread(task,"线程二");
        s2.setPriority(1);
        s2.start();


    }
}
class ShowTime2 implements Runnable{

    @Override
    public void run() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Date d = null;
        while (true){
            d = new Date();
            System.out.println(Thread.currentThread().getName() + "当前的时间为:"+s.format(d));
            try {
                Thread.sleep(1000);//1s
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
