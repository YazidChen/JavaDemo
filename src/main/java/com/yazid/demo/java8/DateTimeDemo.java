package com.yazid.demo.java8;


import java.time.Instant;
import java.util.Date;

/**
 * @author YazidChen
 * @date 2017/12/08 0008 17:40
 **/
public class DateTimeDemo {


    public static void main(String[] args) {
        System.out.println(Instant.EPOCH);
        System.out.println(Instant.MAX);
        System.out.println(Instant.MIN);
        System.out.println(Instant.now());
        System.out.println(new Date());
    }
}
