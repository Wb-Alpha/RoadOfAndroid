package com.example.ui_activity;

public class Item {
    private String name;
    private int imgId;

    public Item(String name, int id) {
        this.name = name;
        this.imgId = id;
    }

    public String getName() {
        return name;
    }

    public int getImgId() {
        return imgId;
    }
}
