package com.yazid.demo.designpatterns.decorator;

/**
 * 综合咖啡
 *
 * @author Yazid
 * @date 2021/6/19 15:21
 */
public class HouseBlend extends Beverage {
    public HouseBlend() {
        description = "House Blend Coffee!";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
