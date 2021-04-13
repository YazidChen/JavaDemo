package com.yazid.demo.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author YazidChen
 * @ceateDate 2017/12/07 0007 18:00
 **/
public class ForReplace {

    public static void main(String[] args) {
        System.out.println("Get set...");
        for (int i = 1; i < 4; i++) {
            System.out.print(i + "...");
        }
        System.out.println();
        System.out.println("Get set...");
        IntStream.range(1, 4)
                .forEach(i -> System.out.print(i + "..."));

        System.out.println();
        System.out.println("Get set...");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + "...");
        }
        System.out.println();
        System.out.println("Get set...");
        IntStream.rangeClosed(1, 5)
                .forEach(i -> System.out.print(i + "..."));


        System.out.println();
        System.out.println("Get set...");
        for (int i = 7; i > 0; i--) {
            System.out.print(i + "...");
        }
        System.out.println();
        System.out.println("Get set...");
        IntStream.iterate(7, e -> e - 1)
                .limit(7).forEach(i -> System.out.print(i + "..."));

        System.out.println();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        int result = 0;
        for (int e : numbers) {
            if (e > 3 && e % 2 == 0 && e < 8) {
                result += e * 2;
            }
        }
        System.out.print(result + "..");
        System.out.println();
        System.out.println(
                numbers.stream()
                        .filter(e -> e > 3)
                        .filter(e -> e % 2 == 0)
                        .filter(e -> e < 8)
                        .mapToInt(e -> e * 2)
                        .sum());

        System.out.println();
        //方法引用
        numbers.stream()
                .filter(e -> e % 2 == 0)
                .forEach(System.out::println);

        System.out.println(
                numbers.stream()
                        .reduce(0, Integer::sum)
        );
    }
}
