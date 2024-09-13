package com.yc.thread;

public class Test5_Interrupt {
    public static void main(String[] args) {

        //通过 interrupted()方法检测线程是否被中断
        // Thread.currentThread() 主线程
        System.out.println(Thread.currentThread().getName() + "线程是否中断:");

        //设置 main 线程  中断信号
        Thread.currentThread().interrupt();

        //main 线程
//        Thread.currentThread().stop(); //不能用stop() 中断线程

        //通过interrupted()方法检测线程是否被中断 interrupted()也可以检测中断状态，同时还可以清除中断状态
//        System.out.println(Thread.currentThread().getName()+"线程是否中断："+Thread.interrupted());//返回true 后 回复状态
//        //检测interrupted()是否会清除线程状态
//        System.out.println(Thread.currentThread().getName()+"线程是否中断："+Thread.interrupted());//输出 false

        //通过isInterrupted()方法检测线程是否被中断 isInterrupted()也可以检测中断状态，同时还可以清除中断状态
        System.out.println(Thread.currentThread().getName()+"线程是否中断："+Thread.currentThread().isInterrupted());//输出 true
        //检测interrupted()是否会清除线程状态
        System.out.println(Thread.currentThread().getName()+"线程是否中断："+Thread.currentThread().isInterrupted());//输出 true


    }
}
