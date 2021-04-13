package com.yazid.demo.jdk8;

import java.util.Arrays;
import java.util.List;

/**
 * @author YazidChen
 * @ceateDate 2017/12/07 0007 16:38
 **/
public class FindNemo {
    /**
     * 命令式格式
     *
     * @param names
     */
    private static void findNemo(List<String> names) {
        boolean found = false;
        for (String name : names) {
            if ("Nemo".equals(name)) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Found Nemo");
        } else {
            System.out.println("Sorry, Nemo not found");
        }
    }

    /**
     * 声明式格式
     *
     * @param names
     */
    public static void findNemo1(List<String> names) {
        if (names.contains("Nemo")) {
            System.out.println("Found Nemo");
        } else {
            System.out.println("Sorry, Nemo not found");
        }
    }

    public static void main(String[] args) {
        List<String> names =
                Arrays.asList("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");

        findNemo(names);
    }
}
