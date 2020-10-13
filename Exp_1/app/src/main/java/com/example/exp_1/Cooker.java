package com.example.exp_1;

public class Cooker {
    private int imgId;
    private String name;

    public Cooker(String name, int imgId){
        this.name = name;
        this.imgId = imgId;
    }

    public String getName(){
        return name;
    }

    public int getImgId(){
        return imgId;
    }
}
