package com.yazid.demo.java.collection;

import java.util.Stack;

/**
 * @author YazidChen
 * @date 2019/06/23 0023 14:49
 */
public class StackDemo {
    /**
     * https://leetcode.com/problems/valid-parentheses/
     *
     * @param s
     * @return
     */
    public boolean validParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
