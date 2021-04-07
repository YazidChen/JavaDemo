package com.yazid.demo.json.jackson.entity;

/**
 * @author Yazid
 * @date 2021/4/7 10:32
 */
public class ItemEntity {
    private Long id;
    private String itemName;
    private UserEntity owner;

    public ItemEntity(Long id, String itemName, UserEntity owner) {
        this.id = id;
        this.itemName = itemName;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }
}
