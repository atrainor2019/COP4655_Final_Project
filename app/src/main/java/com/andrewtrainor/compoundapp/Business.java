package com.andrewtrainor.compoundapp;

import java.io.Serializable;

public class Business implements Serializable {
    private String name;
    private String description;
    private String business_img;

    public Business(String name, String business_img) {
        this.name = name;
        this.description = description;
        this.business_img = business_img;
    }

    public String getTitle() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgSrc() { return business_img; }
}