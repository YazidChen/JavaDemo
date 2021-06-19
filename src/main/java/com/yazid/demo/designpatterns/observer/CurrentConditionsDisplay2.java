package com.yazid.demo.designpatterns.observer;

import java.util.concurrent.Flow;

/**
 * @author Yazid
 * @date 2021/6/13 16:34
 */
public class CurrentConditionsDisplay2 implements Flow.Subscriber<WeatherData2> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        System.out.println("已建立订阅关系！请求一个元素");
        subscription.request(1);
    }

    @Override
    public void onNext(WeatherData2 item) {
        System.out.println("Current conditions: " + item.getTemperature() + "F degrees and " + item.getHumidity() + "% humidity");
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("异常：" + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("结束订阅！");
    }

    public void request(long n) {
        this.subscription.request(n);
    }
}
