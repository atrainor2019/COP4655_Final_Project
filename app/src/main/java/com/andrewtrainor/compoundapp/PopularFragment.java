package com.andrewtrainor.compoundapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PopularFragment extends Fragment {

    private Driver Driver;

    private BusinessAdapter adapter;
    private ArrayList<Business> business_items;
    private RecyclerView recyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.popular_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new BusinessAdapter(getActivity().getApplicationContext(), new ArrayList<Business>()));

        final RadioGroup radio = (RadioGroup) root.findViewById(R.id.radioGroup);



        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);

                // Add logic here

                switch (index) {
                    case 0: // first button trendingGyms();
                        trendingHot();
                        break;

                    case 1: // secondbutton
                        trendingGyms();
                        break;

                    case 2: // secondbutton
                        getRestaurants();
                        break;

                }
            }
        });

        business_items = new ArrayList<Business>();
        Driver = new Driver();

        trendingHot();

        return root;
    }

    public void trendingGyms() {

        Driver.getGyms(new BusinessData() {
            @Override
            public void BusinessRequest(ArrayList<Business> businesses) {
                business_items.clear();
                for (Business business : businesses) {
                    business_items.add(business);
                }

                adapter = new BusinessAdapter(getActivity().getApplicationContext(), business_items);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void trendingHot() {

        Driver.getNewHot(new BusinessData() {
            @Override
            public void BusinessRequest(ArrayList<Business> businesses) {
                business_items.clear();
                for (Business business : businesses) {
                    business_items.add(business);
                }

                adapter = new BusinessAdapter(getActivity().getApplicationContext(), business_items);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void getRestaurants() {

        Driver.getRestaurants(new BusinessData() {
            @Override
            public void BusinessRequest(ArrayList<Business> businesses) {
                business_items.clear();
                for (Business business : businesses) {
                    business_items.add(business);
                }

                adapter = new BusinessAdapter(getActivity().getApplicationContext(), business_items);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}