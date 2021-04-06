package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreType;

/**
 * @author Yazid
 * @date 2021/4/3 17:10
 */
public class IgnoreTypeEntity {

    private Long id;

    private Name name;

    public IgnoreTypeEntity(Long id, Name name) {
        this.id = id;
        this.name = name;
    }

    @JsonIgnoreType
    public static class Name {
        private String firstName;
        private String lastName;

        public Name(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
