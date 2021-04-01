package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.yazid.demo.json.jackson.serializer.CustomDateDeserializer;

import java.util.Date;

/**
 * @author Yazid
 * @date 2021/3/31 10:47
 */
public class EventEntity {
    private String name;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date eventDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}
