package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;

/**
 * @author Yazid
 * @date 2021/3/30 10:52
 */
public class RawEntity {
    private String name;
    @JsonRawValue
    private String json;

    public RawEntity(String name, String json) {
        this.name = name;
        this.json = json;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
