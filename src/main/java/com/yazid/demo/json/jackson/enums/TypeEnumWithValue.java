package com.yazid.demo.json.jackson.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Yazid
 * @date 2021/3/30 11:03
 */
public enum TypeEnumWithValue {
    /**
     * 类型1
     */
    TYPE1(1, "Type A"),
    /**
     * 类型2
     */
    TYPE2(2, "Type 2");

    private Integer id;
    private String name;

    TypeEnumWithValue(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
