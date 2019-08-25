package com.yazid.demo.java.io.entity;

import java.io.Serializable;

/**
 * @author YazidChen
 * @date 2019/07/27 0027 10:54
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 3071656825652294597L;

    private String name;

    private Integer age;

    private String birthday;

    public Employee(String name, Integer age, String birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}
