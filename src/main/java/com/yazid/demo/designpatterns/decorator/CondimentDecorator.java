package com.yazid.demo.designpatterns.decorator;

/**
 * 调味装饰者
 *
 * @author Yazid
 * @date 2021/6/19 15:16
 */
public abstract class CondimentDecorator extends Beverage {
    /**
     * 获取描述
     *
     * @return
     */
    @Override
    public abstract String getDescription();
}
