package com.yazid.demo.java.collection;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author YazidChen
 * @date 2020/02/07 0007 16:56
 */
public class CollectionsTest {
    @Test
    public void sortTest() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        System.out.println("原始数组:");
        System.out.println(arrayList);

        ArrayList<Integer> arrayList1 = (ArrayList<Integer>) arrayList.clone();
        // void reverse(List list)：反转
        Collections.reverse(arrayList1);
        System.out.println("Collections.reverse(arrayList1):");
        System.out.println(arrayList1);

        ArrayList<Integer> arrayList2 = (ArrayList<Integer>) arrayList.clone();
        //void rotate(List list, int distance)//旋转。当distance为正数时，将list后distance个元素整体移到前面。当distance为负数时，将 list的前distance个元素整体移到后面。
        Collections.rotate(arrayList2, 4);
        System.out.println("Collections.rotate(arrayList2, 4):");
        System.out.println(arrayList2);

        ArrayList<Integer> arrayList3 = (ArrayList<Integer>) arrayList.clone();
        // void sort(List list),按自然排序的升序排序
        Collections.sort(arrayList3);
        System.out.println("Collections.sort(arrayList3):");
        System.out.println(arrayList3);

        ArrayList<Integer> arrayList4 = (ArrayList<Integer>) arrayList.clone();
        // void shuffle(List list),随机排序
        Collections.shuffle(arrayList4);
        System.out.println("Collections.shuffle(arrayList4):");
        System.out.println(arrayList4);

        ArrayList<Integer> arrayList5 = (ArrayList<Integer>) arrayList.clone();
        // void swap(List list, int i , int j),交换两个索引位置的元素
        Collections.swap(arrayList5, 2, 5);
        System.out.println("Collections.swap(arrayList5, 2, 5):");
        System.out.println(arrayList5);

        ArrayList<Integer> arrayList6 = (ArrayList<Integer>) arrayList.clone();
        // 定制排序的用法
        Collections.sort(arrayList6, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        System.out.println("定制排序后：");
        System.out.println(arrayList6);
    }

    @Test
    public void searchReplaceTest() {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        arrayList.add(8);
        arrayList.add(9);
        System.out.println("原始数组:");
        System.out.println(arrayList);

        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        arrayList2.add(-3);
        arrayList2.add(4);
        arrayList2.add(5);

        //最大值
        System.out.println("Collections.max(arrayList):");
        System.out.println(Collections.max(arrayList));
        //最小值
        System.out.println("Collections.min(arrayList):");
        System.out.println(Collections.min(arrayList));
        //全部替换
        System.out.println("Collections.replaceAll(arrayList, 3, -3):");
        Collections.replaceAll(arrayList, 3, -3);
        System.out.println(arrayList);
        //统计元素出现次数
        System.out.println("Collections.frequency(arrayList, -3):");
        System.out.println(Collections.frequency(arrayList, -3));
        //统计target在list中第一次出现的索引，找不到则返回-1
        System.out.println("Collections.indexOfSubList(arrayList, arrayList2):");
        System.out.println(Collections.indexOfSubList(arrayList, arrayList2));

        System.out.println("Collections.binarySearch(arrayList, 7):");
        // 对List进行二分查找，返回索引，List必须是有序的
        Collections.sort(arrayList);
        System.out.println(Collections.binarySearch(arrayList, 7));
    }
}
