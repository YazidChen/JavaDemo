package com.yazid.demo.json.jackson.entity;

import java.util.Date;

/**
 * @author Yazid
 * @date 2021/4/8 11:33
 */
public class DateFormatEntity {
    private Long id;
    private String name;
    private Date createDate;

    public DateFormatEntity(Long id, String name, Date createDate) {
        this.id = id;
        this.name = name;
        this.createDate = createDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
