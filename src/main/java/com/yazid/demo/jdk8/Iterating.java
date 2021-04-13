package com.yazid.demo.jdk8;

import com.yazid.demo.jdk8.model.Car;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author YazidChen
 * @ceateDate 2017/12/07 0007 17:40
 **/
public class Iterating {
    public static List<Car> createCars() {
        return Arrays.asList(
                new Car("Jeep", "Wrangler", 2011),
                new Car("Jeep", "Comanche", 1990),
                new Car("Dodge", "Avenger", 2010),
                new Car("Buick", "Cascada", 2016),
                new Car("Ford", "Focus", 2012),
                new Car("Chevrolet", "Geo Metro", 1992)
        );
    }

    public static List<String> getModelsAfter2000UsingFor(List<Car> cars) {
        List<Car> carsSortedByYear = new ArrayList<>();

        for (Car car : cars) {
            if (car.getYear() > 2000) {
                carsSortedByYear.add(car);
            }
        }

        Collections.sort(carsSortedByYear, new Comparator<Car>() {
            @Override
            public int compare(Car car1, Car car2) {
                return new Integer(car1.getYear()).compareTo(car2.getYear());
            }
        });

        List<String> models = new ArrayList<>();
        for (Car car : carsSortedByYear) {
            models.add(car.getModel());
        }

        return models;
    }

    public static List<String> getModelsAfter2000UsingPipeline(List<Car> cars) {
        return cars.stream()
                .filter(car -> car.getYear() > 2000)
                .sorted(Comparator.comparing(Car::getYear))
                .map(Car::getModel)
                .collect(toList());
    }


    private void join() {
        List<String> names = Arrays.asList("Jack", "Jill", "Nate", "Kara", "Kim", "Jullie", "Paul", "Peter");

        List<String> subList = new ArrayList<>();
        for (String name : names) {
            if (name.length() == 4) {
                subList.add(name);
            }
        }

        StringBuilder namesOfLength4 = new StringBuilder();
        for (int i = 0; i < subList.size() - 1; i++) {
            namesOfLength4.append(subList.get(i));
            namesOfLength4.append(", ");
        }

        if (subList.size() > 1) {
            namesOfLength4.append(subList.get(subList.size() - 1));
        }
        System.out.println(namesOfLength4);
    }

    private void join1() {
        List<String> names = Arrays.asList("Jack", "Jill", "Nate", "Kara", "Kim", "Jullie", "Paul", "Peter");

        System.out.println(
                names.stream()
                        .filter(name -> name.length() == 4)
                        .collect(Collectors.joining(", ")));
    }

    public static void main(String[] args) {
        List<Car> carList = Iterating.createCars();
        List<String> stringList = Iterating.getModelsAfter2000UsingFor(carList);
        List<String> stringList1 = Iterating.getModelsAfter2000UsingPipeline(carList);
        System.out.println(stringList);
        System.out.println(stringList1);
    }
}
