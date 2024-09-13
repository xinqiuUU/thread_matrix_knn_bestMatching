package com.yc.thread;

public class Test_yield {
    public static void main(String[] args) {
        YieldOne y1 = new YieldOne();
        YieldOne y2 = new YieldOne();

        Thread t1 = new Thread(y1,"a");
        Thread t2 = new Thread(y2,"b");
        t1.setPriority(10);
        t1.start();
        t2.setPriority(1);
        t2.start();

    }
}
class YieldOne implements Runnable{

    @Override
    public void run() {
        if ("a".equals(Thread.currentThread().getName())){
//            Thread.yield(); // yield只会将执行权放给优先级高的线程 还在 就绪状态
            try{
                Thread.sleep(100);//sleep不管优先级 只要调用sleep，则当前线程睡，其它接过执行权 进入等待状态
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        for (int i=0;i<10;i++){
            System.out.println(Thread.currentThread().getName() + ": "+i);
        }
    }
}
