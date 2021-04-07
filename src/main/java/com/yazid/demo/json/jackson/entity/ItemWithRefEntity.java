package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;

/**
 * @author Yazid
 * @date 2021/4/6 17:21
 */
public class ItemWithRefEntity implements Serializable {
    private static final long serialVersionUID = 314555878497899318L;
    public Long id;
    public String itemName;
    @JsonManagedReference
    public UserWithRefEntity owner;

    public ItemWithRefEntity(Long id, String itemName, UserWithRefEntity owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }
}
