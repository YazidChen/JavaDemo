package com.yazid.demo.java.java8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author YazidChen
 * @date 2017/12/08 0008 15:55
 **/
public class IteratorDemo {

    private static void no() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
        System.out.println(list);
    }

    private static void yes() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("2".equals(item)) {
                iterator.remove();
            }
        }
        System.out.println(list);
    }

    private static void yes1() {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.removeIf("2"::equals);
        System.out.println(list);
    }

    public static void main(String[] args) {
        yes();
        yes1();
        no();
    }
}
