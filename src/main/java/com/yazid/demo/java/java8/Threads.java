package com.yazid.demo.java.java8;

/**
 * @author YazidChen
 * @date 2017/12/08 0008 10:47
 **/
public class Threads {

    private static void threads() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("In another thread");
            }
        });

        thread.start();

        System.out.println("In main");
    }

    private static void threads1() {
        Thread thread = new Thread(() -> System.out.println("In another thread1"));
        thread.start();
    }

    public static void main(String[] args) {
        System.out.println("threads");
        threads();
        System.out.println("threads1");
        threads1();
    }
}
