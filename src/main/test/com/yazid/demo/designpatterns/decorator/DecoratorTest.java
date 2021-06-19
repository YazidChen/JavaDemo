package com.yazid.demo.designpatterns.decorator;

import org.junit.jupiter.api.Test;

/**
 * @author Yazid
 * @date 2021/6/19 15:32
 */
public class DecoratorTest {
    @Test
    public void testDecorator() {
        Beverage beverage = new Espresso();
        System.out.println(beverage.getDescription() + " $" + beverage.cost());

        Beverage beverage1=new HouseBlend();
        beverage1=new Mocha(beverage1);
        beverage1=new Mocha(beverage1);
        beverage1=new Whip(beverage1);
        System.out.println(beverage1.getDescription() + " $" + beverage1.cost());

    }
}
