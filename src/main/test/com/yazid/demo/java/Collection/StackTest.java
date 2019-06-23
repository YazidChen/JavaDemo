package com.yazid.demo.java.Collection;

import org.junit.jupiter.api.Test;

/**
 * @author YazidChen
 * @date 2019/06/23 0023 15:06
 */
class StackTest {
    @Test
    void validParenthesesTest() {
        String s1 = "{}()[]";
        String s2 = "{(})[]";
        String s3 = "{})[]";
        String s4 = "{}[]12";
        String s5 = "{} []";
        StackDemo stackDemo = new StackDemo();
        System.out.println("s1:" + stackDemo.validParentheses(s1));
        System.out.println("s2:" + stackDemo.validParentheses(s2));
        System.out.println("s3:" + stackDemo.validParentheses(s3));
        System.out.println("s4:" + stackDemo.validParentheses(s4));
        System.out.println("s5:" + stackDemo.validParentheses(s5));
    }
}
