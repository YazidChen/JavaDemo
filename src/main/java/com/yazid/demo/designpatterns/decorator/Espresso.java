package com.yazid.demo.designpatterns.decorator;

/**
 * 浓缩咖啡
 *
 * @author Yazid
 * @date 2021/6/19 15:18
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }
}
