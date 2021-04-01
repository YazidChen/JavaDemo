package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JacksonInject;

/**
 * @author Yazid
 * @date 2021/3/31 09:07
 */
public class InjectEntity {
    @JacksonInject
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

    public void setName(String name) {
        this.name = name;
    }
}
