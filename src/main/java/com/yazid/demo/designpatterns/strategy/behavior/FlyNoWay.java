package com.yazid.demo.designpatterns.strategy.behavior;

/**
 * 不会飞行
 *
 * @author Yazid
 * @date 2021/6/9 23:10
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I can't fly!");
    }
}
