package com.yazid.demo.designpatterns.strategy;

import com.yazid.demo.designpatterns.strategy.behavior.FlyRocketPowered;
import com.yazid.demo.designpatterns.strategy.behavior.Squeak;
import org.junit.jupiter.api.Test;

/**
 * @author Yazid
 * @date 2021/6/9 23:43
 */
public class DuckTest {
    /**
     * 绿头鸭
     */
    @Test
    public void testMallardDuck() {
        Duck mallard = new MallardDuck();
        mallard.display();
        mallard.performFly();
        mallard.performQuack();
    }

    /**
     * 模型鸭
     */
    @Test
    public void testModelDuck() {
        Duck model = new ModelDuck();
        model.display();
        model.performFly();
        model.performQuack();

        model.setFlyBehavior(new FlyRocketPowered());
        model.setQuackBehavior(new Squeak());
        model.performFly();
        model.performQuack();
    }
}
