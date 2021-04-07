package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * @author Yazid
 * @date 2021/4/6 15:28
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PrivateEntity {

    private Long id;

    private String firstName;

    public String lastName;

    public PrivateEntity(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
