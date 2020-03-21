package com.yazid.demo.java.collection.entity;

import java.util.Objects;

/**
 * @author YazidChen
 * @date 2020/02/06 0006 17:45
 */
public class A {
    public String a;
    public String b;

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        A a1 = (A) o;
        return Objects.equals(a, a1.a) &&
                Objects.equals(b, a1.b);
    }

/*    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }*/
}
