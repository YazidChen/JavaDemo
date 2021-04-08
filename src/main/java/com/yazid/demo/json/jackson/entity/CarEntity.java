package com.yazid.demo.json.jackson.entity;

/**
 * @author Yazid
 * @date 2021/4/7 14:37
 */
public class CarEntity {
    private String color;
    private String type;

    public CarEntity() {
    }

    public CarEntity(String color, String type) {
        this.color = color;
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CarEntity{" +
                "color='" + color + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
