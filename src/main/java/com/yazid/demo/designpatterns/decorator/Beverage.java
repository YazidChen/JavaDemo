package com.yazid.demo.designpatterns.decorator;

/**
 * 饮料
 *
 * @author Yazid
 * @date 2021/6/19 15:14
 */
public abstract class Beverage {
    String description = "Unknown Beverage!";

    /**
     * 获取描述
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * 花费
     *
     * @return
     */
    public abstract double cost();
}
