package com.yc.thread;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

//生产者  消费者
public class Test_produceConsumer {
    public static void main(String[] args) throws InterruptedException {
        AppleBox ab = new AppleBox();

        //创建生产者
        Producer p1 = new Producer( ab );
        Producer p2 = new Producer( ab );

        //创建消费者
        Consumer c1 = new Consumer( ab );

        //以上是任务  要用线程绑定
        new Thread( p1 ).start();
        new Thread( p2 ).start();
        Thread.sleep(1000);
        new Thread( c1 ).start();

    }


}
class Apple{
    int id;
    Apple( int id ){
        this.id = id;
    }


    @Override
    public String toString() {
        return "Apple{" +
                ", id=" + id +
                '}';
    }
}
//消息中间件：存消息(apple)
class AppleBox{
    Apple[] apples = new Apple[5];  //有界 =》队列
    int index=0;//index表示有几个消息
    //生成后存消息
    public synchronized void deposite( Apple apple){
        while ( index == apples.length){
            try {
                //  Thread.sleep(1000);//不会释放锁
                this.wait(); //会释放锁  // notifyAll()  notify()
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        apples[index]=apple;
        this.notifyAll();//因为生产了一条消息，所以 notifyAll()其它的线程(包括消费线程)可以运行了
        index++;
    }
    //消费消息
    public synchronized Apple withdraw(){
        while (index==0){
            try {
//                Thread.sleep(1000);//不会释放锁
                this.wait();//会释放锁
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        index--;
        this.notifyAll();
        return apples[index];
    }
}
//生产消息的任务
class Producer implements Runnable{
    AppleBox ab = null; //生产完的消息要存放到  appleBox中
    Random r = new Random();
    Producer(AppleBox ab ){
        this.ab = ab;
    }
    @Override
    public void run() {
        for (int i=0;i<5;i++){
            Apple a = new Apple(  i  );
            ab.deposite( a );//存消息
            System.out.println(Thread.currentThread().getName()+"生产了消息:" + a);//a.toString()
            try {
                Thread.sleep( r.nextInt(500) );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
class Consumer implements Runnable{
    AppleBox ab = null; //生产完的消息要存放到
    Random r = new Random();
    Consumer(AppleBox ab ){
        this.ab = ab;
    }
    @Override
    public void run() {

        //从appleBox中取消息出来消费
//        while(  ab.index>0  ){ //有概率阻塞 即消费者跳出循环
        while (true){ //出不去
            Apple a = ab.withdraw();
            System.out.println(Thread.currentThread().getName()+"消费为消息:" + a);//a.toString()
            try {
                Thread.sleep( r.nextInt(500) );
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}