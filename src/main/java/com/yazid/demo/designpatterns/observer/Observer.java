package com.yazid.demo.designpatterns.observer;

/**
 * @author Yazid
 * @date 2021/6/12 21:02
 */
public interface Observer {
    /**
     * 更新
     *
     * @param temp     温度
     * @param humidity 湿度
     * @param pressure 压力
     */
    public void update(float temp, float humidity, float pressure);
}
