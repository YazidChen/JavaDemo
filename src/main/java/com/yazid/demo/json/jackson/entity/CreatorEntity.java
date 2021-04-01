package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Yazid
 * @date 2021/3/30 14:23
 */
public class CreatorEntity {
    private Long id;
    private String name;

    public CreatorEntity() {
    }

    @JsonCreator
    public CreatorEntity(@JsonProperty("id") Long id, @JsonProperty("theName") String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
