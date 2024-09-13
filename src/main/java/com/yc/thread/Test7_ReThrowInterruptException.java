package com.yc.thread;

public class Test7_ReThrowInterruptException {

    public static void main(String[] args) throws InterruptedException {
        //当前线程
        Thread thread = Thread.currentThread();
        try{
            //检测当前线程是否被中断
            thread.interrupt();//发中断信号
            //线程休眠3s
            Thread.sleep(3000);
        }catch (Exception e){
            System.out.println(thread.getName()+"抛出InterruptedException中断异常");
            System.out.println(thread.getName()+"做一些清理工作");

            //也可以将中断异常继续向上抛出
            throw e;
        }
        System.out.println("主线程还能正常运行吗?");
    }
}
