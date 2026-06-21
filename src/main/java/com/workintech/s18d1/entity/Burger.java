package com.workintech.s18d1.entity;

import jakarta.persistence.*;

@Entity
public class Burger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float price;
    private boolean isVegan;
    @Enumerated(EnumType.STRING)
private BreadType breadType;
    private String contents;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = (int) id;
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

    public boolean isVegan() {
        return isVegan;
    }

    public void setVegan(boolean vegan) {
        isVegan = vegan;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }
    public String getContents(){
        return contents;
    }
    public void setContents(String contents){
        this.contents=contents;
    }
}
