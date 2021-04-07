package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yazid
 * @date 2021/4/6 17:21
 */
public class UserWithRefEntity implements Serializable {
    private static final long serialVersionUID = 7469923659469365336L;
    public Long id;
    public String name;
    @JsonBackReference
    public List<ItemWithRefEntity> userItems;

    public UserWithRefEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addItem(ItemWithRefEntity entity) {
        if (userItems == null) {
            userItems = new ArrayList<>(8);
        }
        userItems.add(entity);
    }
}
