package com.yc.thread;

import java.util.Objects;
import java.util.Random;

public class Test_ticket {
    public static void main(String[] args) {
        SellTicketOp sto = new SellTicketOp(240);
        for (int i=0;i<24;i++){
            Thread count1 = new Thread(  sto,"售票员--" +(i+1));
            count1.start();
        }

    }

}
class SellTicketOp implements Runnable{
    int tickets; //票数
    Random r = new Random();

    private Object o = new Object();

    public SellTicketOp(int tickets) {
        this.tickets = tickets;
    }

    //synchronized 加了一个对象锁  同时进入这段代码的人为 一个
    @Override
    public void run() {
        //循环售票
        while( true ){
            //24个线程  -》争抢  SellTicketOp类的对象锁
//            synchronized (  o  ){
            synchronized (  this  ){  //this -> SellTicketOp类的对象
                if (this.tickets>0){
                    System.out.println(Thread.currentThread().getName()+"正在售出第"+(tickets--)+"张票");
                    try {
//                        Thread.sleep( r.nextInt(40) ); //sleep()当前线程不会释放锁
//                       Object 的 wait(  时间 ) 会释放锁
                        wait( r.nextInt(50) ); //wait必须和锁一起使用(synchronized),不然报错
                        //以上程序不会结束  wait状态的线程，在等待队列 ， 没有唤醒的话，系统调试不到
                        this.notifyAll();//唤醒程序 通知所有等待的线程 从等待队列唤醒
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {
                    return;
                }
            }
        }
    }
}