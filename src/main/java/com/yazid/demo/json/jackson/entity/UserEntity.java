package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yazid.demo.json.jackson.serializer.CustomDateSerializer;

import java.util.Date;

/**
 * @author Yazid
 * @date 2021/3/30 11:12
 */
@JsonRootName(value = "user")
public class UserEntity {
    private Long id;
    private String name;
    @JsonSerialize(using = CustomDateSerializer.class)
    private Date joinTime;

    public UserEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserEntity(Long id, String name, Date joinTime) {
        this.id = id;
        this.name = name;
        this.joinTime = joinTime;
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

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }
}
