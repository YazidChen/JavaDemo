package com.yazid.demo.designpatterns.strategy.behavior;

/**
 * 会飞行
 * @author Yazid
 * @date 2021/6/9 23:08
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying!");
    }
}
