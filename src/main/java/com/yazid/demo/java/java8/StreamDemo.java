package com.yazid.demo.java.java8;

import com.yazid.demo.java.java8.model.Car;

import java.util.*;
import java.util.stream.*;

/**
 * @author YazidChen
 * @date 2018/10/04 0004 17:10
 */
public class StreamDemo {

    private void streamOf() {
        Stream<Integer> streamA = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //equals
        Stream<Integer> streamB = Stream.of(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

        streamA.forEach(System.out::println);
        //equals
        streamA.forEach(p -> System.out.println(p));
    }

    private void listStream() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }

        Stream<Integer> stream = list.stream();
        stream.forEach(System.out::println);
        //equals
        list.forEach(System.out::println);
    }

    private void streamGenerate() {
        Stream<Date> stream = Stream.generate(Date::new).limit(10);
        stream.forEach(System.out::println);
    }

    private void streamStr() {
        IntStream streamA = "12345_abcdefg".chars();
        streamA.forEach(System.out::println);

        Stream<String> streamB = Stream.of("A,B,C,D".split(","));
        streamB.forEach(System.out::println);
    }

    private void streamToList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        Stream<Integer> stream = list.stream();
        List<Integer> evenNumbersList = stream.filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.print(evenNumbersList);
    }

    private void streamToArray() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        Stream<Integer> stream = list.stream();
        Integer[] evenNumbersArr = stream.filter(i -> i % 2 == 0).toArray(Integer[]::new);
        System.out.print(evenNumbersArr);
    }

    private void streamCore() {
        List<String> memberNames = new ArrayList<>();
        memberNames.add("Amitabh");
        memberNames.add("Shekhar");
        memberNames.add("Aman");
        memberNames.add("Rahul");
        memberNames.add("Shahrukh");
        memberNames.add("Salman");
        memberNames.add("Yana");
        memberNames.add("Lokesh");

        /*
          Stream中间操作
         */
        //filter()，过滤
        memberNames.stream().filter(s -> s.startsWith("A"))
                .forEach(System.out::println);

        //map()，转换
        memberNames.stream().map(String::toUpperCase)
                .forEach(System.out::println);

        //sort(),排序
        memberNames.stream().sorted()
                .forEach(System.out::println);

        /*
          Stream终端操作
         */
        //forEach()，遍历
        memberNames.forEach(System.out::println);

        //collect()，收集
        List<String> memNamesInUppercase = memberNames.stream().sorted()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.print(memNamesInUppercase);

        //match()，匹配
        boolean matchedResult = memberNames.stream()
                .anyMatch(s -> s.startsWith("A"));
        System.out.println(matchedResult);

        matchedResult = memberNames.stream()
                .allMatch(s -> s.startsWith("A"));
        System.out.println(matchedResult);

        matchedResult = memberNames.stream()
                .noneMatch(s -> s.startsWith("A"));
        System.out.println(matchedResult);

        //count()，总数
        long totalMatched = memberNames.stream()
                .filter(s -> s.startsWith("A"))
                .count();
        System.out.println(totalMatched);

        //reduce()，指定计算模型生成值
        Optional<String> reduced = memberNames.stream()
                .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);

        /*
          条件操作
         */
        //anyMatch()，任意匹配
        boolean matched = memberNames.stream()
                .anyMatch(s -> s.startsWith("A"));
        System.out.println(matched);

        //findFirst()，首条
        String firstMatchedName = memberNames.stream()
                .filter(s -> s.startsWith("L"))
                .findFirst().get();
        System.out.println(firstMatchedName);

        //distinct()，去重
        Collection<String> list = Arrays.asList("A", "B", "C", "D", "A", "B", "C");
        List<String> distinctElements = list.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctElements);

        //max()，最大值
        Integer maxNumber = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .max(Comparator.comparing(Integer::valueOf))
                .get();
        System.out.println("maxNumber = " + maxNumber);
        String maxChar = Stream.of("H", "T", "D", "I", "J")
                .max(Comparator.comparing(String::valueOf))
                .get();
        System.out.println("maxChar = " + maxChar);

        //min()，最小值
        Integer minNumber = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .min(Comparator.comparing(Integer::valueOf))
                .get();
        System.out.println("minNumber = " + minNumber);
        String minChar = Stream.of("H", "T", "D", "I", "J")
                .min(Comparator.comparing(String::valueOf))
                .get();
        System.out.println("minChar = " + minChar);

        List<Car> cars = new ArrayList<>();
        cars.add(new Car("Jeep", "Wrangler", 2011));
        cars.add(new Car("Dodge", "Avenger", 2010));
        cars.add(new Car("Buick", "Cascada", 2016));

        Comparator<Car> comparator = Comparator.comparing(Car::getYear);

        Car minObject = cars.stream().min(comparator).get();
        Car maxObject = cars.stream().max(comparator).get();

        System.out.println("minObject = " + minObject);
        System.out.println("maxObject = " + maxObject);
    }

    private void streamBoxed() {
        List<Integer> ints = IntStream.of(1, 2, 3, 4, 5)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(ints);

        List<Long> longs = LongStream.of(1L, 2L, 3L, 4L, 5L)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(longs);

        List<Double> doubles = DoubleStream.of(1d, 2d, 3d, 4d, 5d)
                .boxed()
                .collect(Collectors.toList());
        System.out.println(doubles);
    }

    private void randomStream() {
        Random random = new Random();

        //5个随机整数
        random.ints(5).sorted().forEach(System.out::println);

        //5个随机浮点数，取值范围0~0.5
        random.doubles(5, 0, 0.5).sorted().forEach(System.out::println);

        //5个长整型数
        random.longs(5).boxed().collect(Collectors.toList()).forEach(System.out::println);
    }

    private void parallelStream() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        //创建并行流
        Stream<Integer> stream = list.parallelStream();
        Integer[] evenNumbersArr = stream.toArray(Integer[]::new);
        System.out.print(Arrays.toString(evenNumbersArr));
    }

    public static void main(String[] args) {
        StreamDemo streamDemo = new StreamDemo();
        streamDemo.streamStr();
    }
}
