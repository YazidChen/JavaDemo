package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Yazid
 * @date 2021/4/6 17:11
 */
public class ViewItemEntity {
    @JsonView(ViewsEntity.Public.class)
    private Long id;
    @JsonView(ViewsEntity.Public.class)
    private String itemName;
    @JsonView(ViewsEntity.Internal.class)
    private String ownerName;

    public ViewItemEntity(Long id, String itemName, String ownerName) {
        this.id = id;
        this.itemName = itemName;
        this.ownerName = ownerName;
    }
}
