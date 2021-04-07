package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * @author Yazid
 * @date 2021/4/6 17:58
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class ItemWithIdentity {
    public Long id;
    public String itemName;
    public UserWithIdentity owner;

    public ItemWithIdentity(Long id, String itemName, UserWithIdentity owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }
}
