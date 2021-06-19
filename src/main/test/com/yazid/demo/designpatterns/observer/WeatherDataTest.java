package com.yazid.demo.designpatterns.observer;

import org.junit.jupiter.api.Test;

import java.util.concurrent.SubmissionPublisher;

/**
 * @author Yazid
 * @date 2021/6/12 22:02
 */
public class WeatherDataTest {

    @Test
    public void testCurrentConditionDisplay() {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay c = new CurrentConditionsDisplay(weatherData);
        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
    }

    @Test
    public void testCurrentConditionDisplay2() {
        CurrentConditionsDisplay2 c = new CurrentConditionsDisplay2();
        SubmissionPublisher<WeatherData2> publisher = new SubmissionPublisher<>();

        publisher.subscribe(c);
        publisher.submit(new WeatherData2(80, 65, 30.4f));
        c.onNext(new WeatherData2(80, 65, 30.4f));
        publisher.close();
        System.out.println("--");
    }
}
