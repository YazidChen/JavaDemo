package com.yazid.demo.java.Collection;

import com.yazid.demo.java.Collection.model.Item;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by YazidChen on 2018/09/16 0016.
 */
public class TreeSetDemo {

    private static void sortedSet() {
        SortedSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4567));
        parts.add(new Item("Modem", 7890));
        System.out.println(parts);

        SortedSet<Item> sortByDescription = new TreeSet<>((o1, o2) -> {
            String descrA = o1.getDescription();
            String descrB = o2.getDescription();
            return descrA.compareTo(descrB);
        });
        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);
    }

    public static void main(String[] args) {
        sortedSet();
    }
}
