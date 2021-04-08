package com.yazid.demo.json.jackson.entity;

import java.util.Optional;

/**
 * @author Yazid
 * @date 2021/4/8 14:57
 */
public class BookEntity {
    private String title;
    private Optional<String> subTitle;

    public BookEntity(String title, Optional<String> subTitle) {
        this.title = title;
        this.subTitle = subTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Optional<String> getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(Optional<String> subTitle) {
        this.subTitle = subTitle;
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "title='" + title + '\'' +
                ", subTitle=" + subTitle +
                '}';
    }
}
