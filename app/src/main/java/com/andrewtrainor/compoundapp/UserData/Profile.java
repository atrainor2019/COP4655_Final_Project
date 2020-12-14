package com.andrewtrainor.compoundapp.UserData;

import com.andrewtrainor.compoundapp.BusinessData.Business;

import java.io.Serializable;
import java.util.ArrayList;

//Models the logged in user and structures the username and password from firebase for
//saving favorites.

public class Profile implements Serializable {

    //Username and password for the logged in profile
    private String username;
    private String password;

    //List of Favorites
    private ArrayList<Business> favorites;

    //Models the profile and stores user favorites and the username and password
    //for the profile class.
    public Profile(String username, String password) {
        favorites = new ArrayList();
        this.username = username;
        this.password = password;
    }

    //helper functions for getting username, password, and favorites.
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

    //Check if the business exists in favorites
    public boolean businessExistsInFavorites(Business business) {
        for(Business b : favorites) {
            if(b.getTitle().equals(business.getTitle())) {
                return true;
            }
        }
        return false;
    }

    //Add the business to favorites for the logged in user.
    //getFavorites() helper function can be called to access the favorites added to the favorites list for the user.
    public void addBusinessToFavorites(Business business) {
        for(Business b : favorites) {
            if(b.getTitle().equals(business.getTitle()))
                return;
        }

        //add business to favorites list.
        favorites.add(business);
    }

    //Removes an unselected business from favorites for the logged in user and Updates the UI.
    //getFavorites() helper function can be called to access the favorites after the unselected favorite has been removed.
    public void removeFromFavorites(Business business) {
        Business tmp = null;
        for(Business b : favorites) {
            if(b.getTitle().equals(business.getTitle())) {
                tmp = b;
            }
        }

        //remove from the favorites list.
        if(tmp != null) {
            favorites.remove(tmp);
        }
    }
}
