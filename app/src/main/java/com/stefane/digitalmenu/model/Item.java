package com.stefane.digitalmenu.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id, image;
    private String name;
    private float price;

    public Item() {
    }

    public Item(int id, int image, String name, float price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public Item(int image, String name, float price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
