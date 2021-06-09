package com.yazid.demo.designpatterns.strategy;

import com.yazid.demo.designpatterns.strategy.behavior.FlyNoWay;
import com.yazid.demo.designpatterns.strategy.behavior.MuteQuack;
import com.yazid.demo.designpatterns.strategy.behavior.Squeak;

/**
 * 模型鸭
 *
 * @author Yazid
 * @date 2021/6/9 23:37
 */
public class ModelDuck extends Duck {
    public ModelDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new MuteQuack();
    }

    @Override
    public void display() {
        System.out.println("I'm a model duck!");
    }
}
