package com.andrewtrainor.compoundapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {
    private String username;
    private String password;

    private ArrayList<Business> favorites;

    public Profile(String username, String password) {
        favorites = new ArrayList();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<Business> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Business> favorites) {
        this.favorites = favorites;
    }

    public boolean businessExistsInFavorites(Business business) {
        for(Business m : favorites) {
            if(m.getTitle().equals(business.getTitle())) {
                return true;
            }
        }
        return false;
    }

    public void addBusinessToFavorites(Business business) {
        for(Business m : favorites) {
            if(m.getTitle().equals(business.getTitle()))
                return;
        }

        favorites.add(business);
    }

    public void removeFromFavorites(Business business) {
        Business tmp = null;
        for(Business m : favorites) {
            if(m.getTitle().equals(business.getTitle())) {
                tmp = m;
            }
        }

        if(tmp != null) {
            favorites.remove(tmp);
        }
    }
}
