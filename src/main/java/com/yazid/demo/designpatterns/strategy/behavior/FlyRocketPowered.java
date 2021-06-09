package com.yazid.demo.designpatterns.strategy.behavior;

/**
 * 火箭动力飞行
 *
 * @author Yazid
 * @date 2021/6/9 23:40
 */
public class FlyRocketPowered implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("I'm flying with a rocket!");
    }
}
