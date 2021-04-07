package com.yazid.demo.json.jackson.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * @author Yazid
 * @date 2021/4/6 15:46
 */
public class ZooEntity {
    private Animal animal;

    public ZooEntity() {
    }

    public ZooEntity(Animal animal) {
        this.animal = animal;
    }

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = Dog.class, name = "dog"),
            @JsonSubTypes.Type(value = Cat.class, name = "cat")
    })
    public static class Animal {
        private String name;

        public Animal() {
        }

        public Animal(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @JsonTypeName("dog")
    public static class Dog extends Animal {
        private double barkVolume;

        public Dog() {
        }

        public Dog(String name, double barkVolume) {
            super(name);
            this.barkVolume = barkVolume;
        }

        public double getBarkVolume() {
            return barkVolume;
        }

        public void setBarkVolume(double barkVolume) {
            this.barkVolume = barkVolume;
        }
    }

    @JsonTypeName("cat")
    public static class Cat extends Animal {
        private boolean likesCream;
        private int lives;

        public Cat() {
        }

        public Cat(String name, boolean likesCream, int lives) {
            super(name);
            this.likesCream = likesCream;
            this.lives = lives;
        }

        public boolean isLikesCream() {
            return likesCream;
        }

        public void setLikesCream(boolean likesCream) {
            this.likesCream = likesCream;
        }

        public int getLives() {
            return lives;
        }

        public void setLives(int lives) {
            this.lives = lives;
        }
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
