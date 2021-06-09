package com.yazid.demo.designpatterns.strategy.behavior;

/**
 * 吱吱叫
 *
 * @author Yazid
 * @date 2021/6/9 23:14
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("Squeak!");
    }
}
