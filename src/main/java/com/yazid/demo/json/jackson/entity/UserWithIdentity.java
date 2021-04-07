package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yazid
 * @date 2021/4/6 17:59
 */
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class UserWithIdentity {
    public Long id;
    public String itemName;
    public List<ItemWithIdentity> userItems;

    public UserWithIdentity(Long id, String itemName) {
        this.id = id;
        this.itemName = itemName;
    }

    public void addItem(ItemWithIdentity entity) {
        if (userItems == null) {
            userItems = new ArrayList<>(8);
        }
        userItems.add(entity);
    }
}
