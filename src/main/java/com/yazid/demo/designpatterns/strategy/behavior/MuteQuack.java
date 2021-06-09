package com.yazid.demo.designpatterns.strategy.behavior;

/**
 * 无声
 *
 * @author Yazid
 * @date 2021/6/9 23:13
 */
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("<< Silence >>");
    }
}
