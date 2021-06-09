package com.yazid.demo.designpatterns.strategy.behavior;

/**
 * 鸭叫声
 *
 * @author Yazid
 * @date 2021/6/9 23:12
 */
public class Quack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Quack!");
    }
}
