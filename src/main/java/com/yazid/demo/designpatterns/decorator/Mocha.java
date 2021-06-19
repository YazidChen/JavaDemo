package com.yazid.demo.designpatterns.decorator;

/**
 * 摩卡咖啡
 *
 * @author Yazid
 * @date 2021/6/19 15:24
 */
public class Mocha extends CondimentDecorator {
    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return 0.20 + beverage.cost();
    }


}
