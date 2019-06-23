package com.yazid.demo.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YazidChen
 * @ceateDate 2017/12/07 0007 16:50
 **/
public class UseMap {
    private static void incrementPageVisit(Map<String, Integer> pageVisits, String page) {
        if (!pageVisits.containsKey(page)) {
            pageVisits.put(page, 0);
        }

        pageVisits.put(page, pageVisits.get(page) + 1);
    }

    public static void incrementPageVisit1(Map<String, Integer> pageVisits, String page) {
        pageVisits.merge(page, 1, (oldValue, value) -> oldValue + value);
    }

    public static void main(String[] args) {
        Map<String, Integer> pageVisits = new HashMap<>();

        String page = "https://agiledeveloper.com";

//        incrementPageVisit(pageVisits, page);
//        incrementPageVisit(pageVisits, page);
        incrementPageVisit1(pageVisits, page);
        incrementPageVisit1(pageVisits, page);

        System.out.println(pageVisits.get(page));
    }
}
