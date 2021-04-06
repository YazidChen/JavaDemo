package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Yazid
 * @date 2021/4/3 17:19
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class IncludeEntity {
    private Long id;
    private String name;

    public IncludeEntity(Long id, String name) {
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
