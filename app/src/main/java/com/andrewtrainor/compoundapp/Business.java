package com.andrewtrainor.compoundapp;

import java.io.Serializable;

public class Business implements Serializable {
    private String name;
    private String description;
    private String business_img;
    private double business_lat;
    private double business_lon;

    public Business(String name, String business_img, double business_lat, double business_lon) {
        this.name = name;
        this.description = description;
        this.business_img = business_img;
        this.business_lat = business_lat;
        this.business_lon = business_lon;
    }

    public String getTitle() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgSrc() { return business_img; }

    public double getLat() { return business_lat; }

    public double getLon() { return business_lon; }
}