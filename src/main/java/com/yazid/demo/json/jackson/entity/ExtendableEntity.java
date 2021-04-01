package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Yazid
 * @date 2021/3/30 10:03
 */
public class ExtendableEntity {
    private String name;
    private Map<String, String> properties;

    public ExtendableEntity() {
    }

    public ExtendableEntity(String name) {
        this.name = name;
    }

    /**
     * put properties
     *
     * @param k key
     * @param v value
     */
    @JsonAnySetter
    public void add(String k, String v) {
        if (properties == null) {
            properties = new HashMap<>(8);
        }
        properties.put(k, v);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonAnyGetter
    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}
