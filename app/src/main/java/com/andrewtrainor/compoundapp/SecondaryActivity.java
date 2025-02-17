package com.andrewtrainor.compoundapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.andrewtrainor.compoundapp.BusinessData.MySingleton;
import com.andrewtrainor.compoundapp.UserData.Login;
import com.andrewtrainor.compoundapp.UserData.Profile;
import com.andrewtrainor.compoundapp.UserData.Register;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class SecondaryActivity extends AppCompatActivity {

    private static Profile profile;
    private static Context context;

    public static MySingleton getVolley() {
        return MySingleton.getInstance(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secondary_activity);
        context = getApplicationContext();
        profile = (Profile) getIntent().getSerializableExtra("profileobj");

        BottomNavigationView navView = findViewById(R.id.app_nav_bar);
        navView.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                new SearchFragment()).commit();

    }


    //set cases for bottom Navigation to send the user to the correct fragment. navListener
    //tells when a BottomNav Item is selected and the fragment needs to be updated.

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch(item.getItemId()){
                        case R.id.navigation_search:
                            selectedFragment = new SearchFragment();
                            break;
                        case R.id.navigation_trending:
                            selectedFragment = new PopularFragment();
                            break;
                        case R.id.navigation_favorites:
                            selectedFragment = new FavFragment();
                            break;

                        //if the user selects the NavigationItem to Logout, signout user instance using FirebaseAuth
                        //send the user back to the login screen in case they want to login again.
                        case R.id.navigation_logout:
                            FirebaseAuth.getInstance().signOut();
                            Intent i = new Intent(getApplication(), Login.class);
                            startActivity(i);
                            return true;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
                            selectedFragment).commit();

                    return true;
                }
            };

    public static Profile getProfile() {
        return profile;
    }
}
