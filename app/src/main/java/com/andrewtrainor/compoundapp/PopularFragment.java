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

import com.andrewtrainor.compoundapp.BusinessData.Business;
import com.andrewtrainor.compoundapp.BusinessData.BusinessAdapter;
import com.andrewtrainor.compoundapp.BusinessData.BusinessData;
import com.andrewtrainor.compoundapp.BusinessData.Driver;

import java.util.ArrayList;

public class PopularFragment extends Fragment {

    private com.andrewtrainor.compoundapp.BusinessData.Driver Driver;

    //Adapter, List for business items, and RecyclerView declaration
    private BusinessAdapter adapter;
    private ArrayList<Business> business_items;
    private RecyclerView recyclerView;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.popular_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new BusinessAdapter(getActivity().getApplicationContext(), new ArrayList<Business>()));

        final RadioGroup radio = (RadioGroup) root.findViewById(R.id.radioGroup);

        //When the user switches to another section, check which section the user wishes to process.
        //call the resulting trendingHot(), trendingGyms, or getRestaurants() to display the data.

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                //Get the index of the selected radio button ID to tell which button has been selected.

                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);

                //Cases for selection for displaying the resulting popular data.

                switch (index) {
                    case 0: // first button trendingHot();
                        trendingHot();
                        break;

                    case 1: // second button trendingGyms();
                        trendingGyms();
                        break;

                    case 2: // third button popularRestaurants();
                        getRestaurants();
                        break;
                }
            }
        });

        business_items = new ArrayList<Business>();
        Driver = new Driver();

        //Initial population so the UI does not look blank to the user at application load.
        trendingHot();

        return root;
    }

    //Method to return the trendingGyms for the user area. This is called when the user selects the
    //"Gyms" radio button under the popular fragment.
    public void trendingGyms() {

        Driver.getGyms(new BusinessData() {
            @Override
            public void BusinessRequest(ArrayList<Business> businesses) {
                business_items.clear();
                for (Business business : businesses) {
                    business_items.add(business);
                }

                //populate the recyclerView with the help of BusinessAdapter
                adapter = new BusinessAdapter(getActivity().getApplicationContext(), business_items);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    //Method to return the New and Hot trending businesses for the user area. This is called when the user selects the
    //"Trending" radio button under the popular fragment.
    public void trendingHot() {

        Driver.getNewHot(new BusinessData() {
            @Override
            public void BusinessRequest(ArrayList<Business> businesses) {
                business_items.clear();
                for (Business business : businesses) {
                    business_items.add(business);
                }

                //populate the recyclerView with the help of BusinessAdapter
                adapter = new BusinessAdapter(getActivity().getApplicationContext(), business_items);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    //Method to return the Trending Restaurants for the user area. This is called when the user selects the
    //"Restaurants" radio button under the popular fragment.
    public void getRestaurants() {

        Driver.getRestaurants(new BusinessData() {
            @Override
            public void BusinessRequest(ArrayList<Business> businesses) {
                business_items.clear();
                for (Business business : businesses) {
                    business_items.add(business);
                }

                //populate the recyclerView with the help of BusinessAdapter
                adapter = new BusinessAdapter(getActivity().getApplicationContext(), business_items);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}