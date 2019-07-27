package com.yazid.demo.java.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author YazidChen
 * @date 2018/11/05 0005 21:46
 */
public class ListDemo {
    /**
     * 范围取
     */
    private static void subRange() {
        List<Integer> integers = IntStream.range(0, 25).boxed().collect(Collectors.toList());
        List<Integer> rangeInt = integers.subList(10, 20);
        System.out.println("integers:" + integers);
        System.out.println("rangeInt:" + rangeInt);
        rangeInt.clear();
        System.out.println("integers:" + integers);
        System.out.println("rangeInt:" + rangeInt);
    }

    /**
     * 混排
     */
    private static void shuffle() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i <= 49; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        List<Integer> list = numbers.subList(0, 6);
        Collections.sort(list);
        System.out.println(list);
    }

    public static void main(String[] args) {
        shuffle();
    }
}
