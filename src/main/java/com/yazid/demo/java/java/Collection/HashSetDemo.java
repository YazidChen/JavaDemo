package com.yazid.demo.java.java.Collection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author YazidChen
 * @date 2018/11/05 0005 22:07
 */
public class HashSetDemo {
    /**
     * 取交集
     */
    private static void intersection() {
        String[] a = {"a", "b", "c", "d"};
        String[] b = {"c", "d", "e"};
        Set<String> result = new HashSet<>(Arrays.asList(a));
        result.retainAll(Arrays.asList(b));
        System.out.println(result);
        //集合转数组
        String[] c = result.toArray(new String[0]);
        System.out.println(Arrays.toString(c));
    }

    public static void main(String[] args) {
        intersection();
    }
}
