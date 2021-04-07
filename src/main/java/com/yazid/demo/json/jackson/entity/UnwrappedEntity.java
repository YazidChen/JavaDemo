package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * @author Yazid
 * @date 2021/4/6 16:24
 */
public class UnwrappedEntity {
    private Long id;
    @JsonUnwrapped
    private Name name;

    public UnwrappedEntity(Long id, Name name) {
        this.id = id;
        this.name = name;
    }

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
}
