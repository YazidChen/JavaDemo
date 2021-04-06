package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author Yazid
 * @date 2021/4/3 16:44
 */
public class AliasEntity {
    @JsonAlias({"fName", "f_name"})
    private String firstName;

    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
