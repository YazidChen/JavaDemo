package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonFilter;

/**
 * @author Yazid
 * @date 2021/4/7 09:38
 */
@JsonFilter("myFilter")
public class FilterEntity {
    private Long id;
    private String name;

    public FilterEntity(Long id, String name) {
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
