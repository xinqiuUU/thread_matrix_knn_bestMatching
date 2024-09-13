package com.yc.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.logging.SimpleFormatter;

//从任务角度来看
public class Test2_Runnable{

    public static void main(String[] args) {
        //创建一个线程  绑定任务
        //外部类
        Runnable task = new RunnableWai();
        Thread t = new Thread( task  );
        t.start();  //调用  task对象的 run() 方法

        //二 内部类
        Runnable taskNei = new RunnableWai();
        Thread tNei = new Thread( taskNei  );
        tNei.start();
        //三 匿名内部类
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
                Date date = null;
                while ( true ){
                    date = new Date();
                    System.out.println( Thread.currentThread().getName()+"当前时间:"+  s.format(date) );
                    try {
                        Thread.sleep( 1000 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t3.start();

        //四  因为Runnable 接口 上有 @FunctionInterface 表示这个接口是支持lambda语法
        Thread t4 = new Thread( ()->{
            SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
            Date date = null;
            while ( true ){
                date = new Date();
                System.out.println( Thread.currentThread().getName()+"当前时间:"+  s.format(date) );
                try {
                    Thread.sleep( 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } );
        t4.start();

    }
    static class RunnableNei implements Runnable{
        public void run() {
            SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
            Date date = null;
            while ( true ){
                date = new Date();
                System.out.println( Thread.currentThread().getName()+"当前时间:"+  s.format(date) );
                try {
                    Thread.sleep( 1000 );
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class RunnableWai implements Runnable{
    @Override
    public void run() {
        SimpleDateFormat s = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss");
        Date date = null;
        while ( true ){
            date = new Date();
            System.out.println( Thread.currentThread().getName()+"当前时间:"+  s.format(date) );
            try {
                Thread.sleep( 1000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
