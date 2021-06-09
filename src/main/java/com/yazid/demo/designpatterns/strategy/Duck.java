package com.yazid.demo.designpatterns.strategy;

import com.yazid.demo.designpatterns.strategy.behavior.FlyBehavior;
import com.yazid.demo.designpatterns.strategy.behavior.QuackBehavior;

/**
 * 鸭子
 *
 * @author Yazid
 * @date 2021/6/9 23:02
 */
public abstract class Duck {
    /**
     * 飞行行为
     */
    FlyBehavior flyBehavior;
    /**
     * 鸭叫行为
     */
    QuackBehavior quackBehavior;

    /**
     * 表现
     */
    public abstract void display();

    /**
     * 执行飞行
     */
    public void performFly() {
        flyBehavior.fly();
    }

    /**
     * 执行鸭叫
     */
    public void performQuack() {
        quackBehavior.quack();
    }

    public void swim() {
        System.out.println("All duck can swim!");
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }
}
