package com.yc.thread;

/*
      死锁
 */
public class Test_deadlock implements Runnable{

    public int flag = 1;
    //创建两个对象 ，以使用其两个对象锁
    static Object o1 = new Object(),o2 = new Object(); //创建两个对象，以使用其两个对象锁

    public static void main(String[] args) {
        Test_deadlock td1 = new Test_deadlock(); //任务一
        Test_deadlock td2 = new Test_deadlock(); //任务二
        td1.flag=1;
        td2.flag=0;
        Thread t1 = new Thread(td1);
        Thread t2 = new Thread(td2);
        t1.start();
        t2.start();

    }

    //会  造成   死锁
    @Override
    public void run() {
        System.out.println("flag="+flag);
        if (flag==1){
            synchronized (o1){
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized (o2){
                    System.out.println("1");
                }
            }
        }
        if (flag==0){
            synchronized (o2){
                try{
                    Thread.sleep(500);
                }catch (Exception e){
                    e.printStackTrace();
                }
                synchronized (o1){
                    System.out.println("0");
                }
            }
        }
    }
}
