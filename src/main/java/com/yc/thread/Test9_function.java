package com.yc.thread;

interface Action<T>{
    void execute(T t);
}
public class Test9_function {
    public static void main(String[] args) {
        // 1  外部类
        Action action1 = new MyAction();
        action1.execute("外部类");
        // 2  匿名内部类n()
        Action action2 = new Action() {
            @Override
            public void execute(Object o) {
                System.out.println(o);
            }
        };
        action2.execute("匿名内部类");

        // 3  lambda语法
        Action action3=(t)->{
            //处理
            System.out.println( t );
        };
        action3.execute("lambda语法");

        // 4  *** 直接绑定  *** 语法糖
        Action action4 = System.out::println;
        action4.execute("直接绑定");

        //作为参数用
        test(System.out::println,"作为参数用");
    }
    static void test(Action action,String str){
        System.out.println(str);
    }
}

class MyAction implements Action{

    @Override
    public void execute(Object o) {
        System.out.println(o);
    }
}
