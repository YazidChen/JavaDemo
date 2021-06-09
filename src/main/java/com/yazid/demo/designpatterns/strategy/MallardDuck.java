package com.yazid.demo.designpatterns.strategy;

import com.yazid.demo.designpatterns.strategy.behavior.FlyWithWings;
import com.yazid.demo.designpatterns.strategy.behavior.Quack;

/**
 * 绿头鸭
 *
 * @author Yazid
 * @date 2021/6/9 23:36
 */
public class MallardDuck extends Duck {

    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("I'm a real Mallard duck!");
    }
}
