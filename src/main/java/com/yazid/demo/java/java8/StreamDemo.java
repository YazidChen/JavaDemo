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

    private void strStream() {
        IntStream streamA = "12345_abcdefg".chars();
        streamA.forEach(System.out::println);

        Stream<String> streamB = Stream.of("A,B,C,D".split(","));
        streamB.forEach(System.out::println);
    }

    private void streamGenerate() {
        Stream<Date> stream = Stream.generate(Date::new).limit(10);
        stream.forEach(System.out::println);
    }

    private void streamIterate() {
        Stream.iterate(1, i -> i + 1).limit(10).forEach(System.out::println);
    }

    private void streamConcat() {
        Stream<Integer> stream1 = Stream.of(1, 2, 3);
        Stream<Integer> stream2 = Stream.of(4, 5);
        Stream.concat(stream1, stream2).forEach(System.out::println);
    }

    private void streamBuilder() {
        Stream<Object> stringStream = Stream.builder()
                .add("1")
                .add("2")
                .build();
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
        System.out.println("--filter:");
        memberNames.stream().filter(s -> s.startsWith("A"))
                .forEach(System.out::println);

        //map()，转换
        System.out.println("--map:");
        memberNames.stream().map(String::toUpperCase)
                .forEach(System.out::println);

        //flatMap，多层级扁平化转换
        System.out.println("--flatMap:");
        Stream<List<Integer>> intStream = Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5), Arrays.asList(6, 7, 8));
        intStream.flatMap(Collection::stream).forEach(System.out::println);

        //sorted(),排序
        System.out.println("--sorted:");
        memberNames.stream().sorted()
                .forEach(System.out::println);

        //peek(),生成一个包含原Stream所有元素的新Stream，同时提供一个消费函数，与原Stream并行执行
        System.out.println("--peek:");
        long totalMatched = memberNames.stream()
                .peek(System.out::println)
                .count();
        System.out.println(totalMatched);

        /*
          Stream终端操作
         */
        //forEach()，遍历
        System.out.println("--forEach:");
        memberNames.forEach(System.out::println);

        //collect()，收集
        System.out.println("--collect:");
        List<String> memNamesInUppercase = memberNames.stream().sorted()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.print(memNamesInUppercase);

        //match()，匹配
        System.out.println("--match:");
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
        System.out.println("--count:");
        long count = memberNames.stream()
                .filter(s -> s.startsWith("A"))
                .count();
        System.out.println(count);

        //reduce()，指定计算模型生成值
        System.out.println("--reduce:");
        Optional<String> reduced = memberNames.stream()
                .reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);

        /*
          条件操作
         */
        //anyMatch()，任意匹配
        System.out.println("--anyMatch:");
        boolean matched = memberNames.stream()
                .anyMatch(s -> s.startsWith("A"));
        System.out.println(matched);

        //findFirst()，首条
        System.out.println("--findFirst:");
        String firstMatchedName = memberNames.stream()
                .filter(s -> s.startsWith("L"))
                .findFirst().get();
        System.out.println(firstMatchedName);

        //distinct()，去重
        System.out.println("--distinct:");
        Collection<String> list = Arrays.asList("A", "B", "C", "D", "A", "B", "C");
        List<String> distinctElements = list.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctElements);

        //skip()，从第N位开始取数
        System.out.println("--skip:");
        IntStream.range(1, 100).skip(10).limit(10).forEach(System.out::println);

        //max()，最大值
        System.out.println("--max:");
        Integer maxNumber = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .max(Comparator.comparing(Integer::valueOf))
                .get();
        System.out.println("maxNumber = " + maxNumber);
        String maxChar = Stream.of("H", "T", "D", "I", "J")
                .max(Comparator.comparing(String::valueOf))
                .get();
        System.out.println("maxChar = " + maxChar);

        //min()，最小值
        System.out.println("--min:");
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
        cars.add(new Car("BMW", "BMW1", 2011));
        cars.add(new Car("TSL", "TSL1", 2010));
        cars.add(new Car("BYD", "BYDTang", 2016));

        //groupingBy()，分组
        System.out.println("--groupingBy:");
        Map<Integer, List<Car>> groupCar = cars.stream().collect(Collectors.groupingBy(Car::getYear));
        System.out.println(groupCar);

        //partitioningBy()，分片
        System.out.println("--partitioningBy:");
        Map<Boolean, List<Car>> partitionCar = cars.stream().collect(Collectors.partitioningBy(c -> c.getYear() == 2016));
        System.out.println(partitionCar);

        //comparing(),比较
        System.out.println("--comparing:");
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
        Stream<Integer> parallelStream = list.parallelStream();
        Integer[] evenNumbersArr = parallelStream.toArray(Integer[]::new);
        System.out.print(Arrays.toString(evenNumbersArr));
        System.out.println();
        System.out.println(parallelStream.isParallel());
        //sequential()，并行转换为顺序
        Stream<Integer> stream = parallelStream.sequential();
        System.out.println(stream.isParallel());
    }

    public static void main(String[] args) {
        StreamDemo streamDemo = new StreamDemo();
        streamDemo.parallelStream();
    }
}
