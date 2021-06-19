package com.yazid.demo.designpatterns.observer;


/**
 * @author Yazid
 * @date 2021/6/12 12:58
 */
public interface Subject {
    /**
     * 注册观察者
     *
     * @param o
     */
    public void registerObserver(Observer o);

    /**
     * 移除观察者
     *
     * @param o
     */
    public void removeObserver(Observer o);

    /**
     * 通知事件给观察者
     */
    public void notifyObserver();
}
