package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * @author Yazid
 * @date 2021/3/31 10:08
 */
public class SetterEntity {
    private Long id;

    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @JsonSetter("theName")
    public void setName(String name) {
        this.name = name;
    }
}
