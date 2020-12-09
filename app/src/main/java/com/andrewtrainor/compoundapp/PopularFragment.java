package com.andrewtrainor.compoundapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


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

        business_items = new ArrayList<Business>();
        Driver = new Driver();

        Driver.getNewHot(new BusinessData() {
            @Override
            public void BusinessRequest(ArrayList<Business> businesses) {
                for(Business business : businesses) {
                    business_items.add(business);
                }

                adapter = new BusinessAdapter(getActivity().getApplicationContext(), business_items);
                recyclerView.setAdapter(adapter);
            }
        });

        return root;
    }
}