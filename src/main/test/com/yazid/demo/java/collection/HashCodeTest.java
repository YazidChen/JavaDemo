package com.yazid.demo.java.collection;

import com.yazid.demo.java.collection.entity.A;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author YazidChen
 * @date 2020/02/06 0006 17:46
 */
public class HashCodeTest {
    @Test
    public void hashCodeTest(){
        A aEntity = new A();
        aEntity.setA("a");
        aEntity.setB("b");
        A bEntity = new A();
        bEntity.setA("a");
        bEntity.setB("b");

        Map m = new HashMap();
        m.put(aEntity,"aEntity");
        m.put(bEntity,"bEntity");

        Set s = new HashSet();
        s.add(aEntity);
        s.add(bEntity);
        System.out.println(m.size());
        System.out.println(s.size());
    }
}
