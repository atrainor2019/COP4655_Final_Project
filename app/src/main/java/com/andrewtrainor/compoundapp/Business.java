package com.andrewtrainor.compoundapp;

import android.content.Context;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Business implements Serializable {
    private String name;
    private String description;
    private String business_img;
    private double business_lat;
    private double business_lon;
    private String key_id;


    private ArrayList<Business> favorites;

    public Business(String name, String business_img, double business_lat, double business_lon) {
        this.name = name;
        this.description = description;
        this.business_img = business_img;
        this.business_lat = business_lat;
        favorites = new ArrayList();
        this.business_lon = business_lon;
        this.key_id = key_id;
    }

    public String getTitle() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImgSrc() { return business_img; }

    public ArrayList<Business> getFavorites() {
        return favorites;
    }

    public String getFavorite1(){return favorites.toString();}

    public double getLat() { return business_lat; }

    public double getLon() { return business_lon; }

    public String getKey_id() {
        return key_id;
    }

    public void setFavorites(ArrayList<Business> favorites) {
        this.favorites = favorites;
    }

}