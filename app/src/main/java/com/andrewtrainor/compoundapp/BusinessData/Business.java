package com.andrewtrainor.compoundapp.BusinessData;

import java.io.Serializable;
import java.util.ArrayList;

public class Business implements Serializable {
    private String name;
    private String address;
    private String business_img;
    private String business_phone;
    private String business_type;
    private double business_lat;
    private double business_lon;
    private double business_rating;
    private String key_id;


    private ArrayList<Business> favorites;

    public Business(String name, String business_img, double business_lat, double business_lon, String address, double business_rating, String business_phone, String business_type) {
        this.name = name;
        this.business_type = business_type;
        this.business_phone = business_phone;
        this.address= address;
        this.business_img = business_img;
        this.business_lat = business_lat;
        this.business_rating = business_rating;
        favorites = new ArrayList();
        this.business_lon = business_lon;
        this.key_id = key_id;
    }

    public String getTitle() {
        return name;
    }

    public String getBusiness_type(){ return business_type;}

    public String getPhone(){return "Contact us: " + business_phone; }

    public String getDescription() {
        return address;
    }

    public String getImgSrc() { return business_img; }

    public ArrayList<Business> getFavorites() {
        return favorites;
    }

    public String getFavorite1(){return favorites.toString();}

    public double getLat() { return business_lat; }

    public double getLon() { return business_lon; }

    public String getRating(){return "Rating: " + String.valueOf(business_rating) + "/" + "5.0"; }

    public String getKey_id() {
        return key_id;
    }

    public void setFavorites(ArrayList<Business> favorites) {
        this.favorites = favorites;
    }

}