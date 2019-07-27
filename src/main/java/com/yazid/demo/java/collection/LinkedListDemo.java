package com.yazid.demo.java.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by YazidChen on 2018/07/29 0029.
 */
public class LinkedListDemo {

    private static void iterator() {
        List<String> staff = new LinkedList<>();
        staff.add("Amy");
        staff.add("Bob");
        staff.add("Carl");
        System.out.println("初始化：" + staff);
        Iterator iterator = staff.iterator();
        String first = (String) iterator.next();
        String second = (String) iterator.next();
        iterator.remove();//删除第二位元素
        System.out.println("删除第二位元素:" + staff);

        ListIterator<String> listIterator = staff.listIterator();
        String listFirst = listIterator.next();
        System.out.println("listFirst:" + listFirst);
        listIterator.add("Bo");//添加操作会使迭代器加1
        System.out.println("在第二位插入元素：" + staff);
        String listSecond = listIterator.previous();
        System.out.println("listSecond:" + listSecond);
        listIterator.set(second);
        System.out.println("修改第二位值：" + staff);
    }

    public static void main(String[] args) {
        iterator();
    }
}
