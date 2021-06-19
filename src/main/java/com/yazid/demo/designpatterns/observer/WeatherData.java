package com.yazid.demo.designpatterns.observer;

import java.util.ArrayList;

/**
 * @author Yazid
 * @date 2021/6/12 21:17
 */
public class WeatherData implements Subject {
    /**
     * 观察者列表
     */
    private ArrayList<Observer> observers;
    /**
     * 温度
     */
    private float temperature;
    /**
     * 湿度
     */
    private float humidity;
    /**
     * 压力
     */
    private float pressure;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer o : observers) {
            o.update(temperature, humidity, pressure);
        }
    }

    /**
     * 观测值变更
     */
    public void measurementChanged() {
        notifyObserver();
    }

    /**
     * 用于测试设置值
     *
     * @param temperature 温度
     * @param humidity    湿度
     * @param pressure    压力
     */
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementChanged();
    }
}
