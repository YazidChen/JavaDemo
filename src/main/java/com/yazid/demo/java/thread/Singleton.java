package com.yazid.demo.java.thread;

/**
 * 单例模式双重检验锁
 * @author Yazid
 * @date 2020/02/23 0023 16:28
 */
public class Singleton {
    private Singleton() {

    }

    private volatile static Singleton instance;

    public Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
