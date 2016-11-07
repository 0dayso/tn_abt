package com.tuniu.aop.unittest.vendor;

/**
 * Created by chengyao on 2016/2/1.
 */
public class Test2 {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("1");

        System.out.println(threadLocal.get());



        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocal.get());
            }
        });

        thread.start();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(threadLocal.get());
            }
        });

        thread2.start();



        thread.join();
        thread2.join();
    }
}
