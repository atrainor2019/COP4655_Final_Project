package com.andrewtrainor.compoundapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andrewtrainor.compoundapp.BusinessData.Business;
import com.andrewtrainor.compoundapp.BusinessData.BusinessAdapter;

import java.util.ArrayList;

public class FavFragment extends Fragment {
    private BusinessAdapter adapter;
    private ArrayList<Business> items;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fave_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        items = new ArrayList<Business>();

        for(Business business : SecondaryActivity.getProfile().getFavorites()) {
            items.add(business);
            adapter = new BusinessAdapter(getActivity().getApplicationContext(), items, this);
            recyclerView.setAdapter(adapter);
        }

        return root;
    }

    public void unFavorite() {
        for(Business b : SecondaryActivity.getProfile().getFavorites()) {
            System.out.println(b.getTitle());
        }

        items = new ArrayList<Business>();
        for(Business business : SecondaryActivity.getProfile().getFavorites()) {
            items.add(business);
        }

        adapter = new BusinessAdapter(getActivity().getApplicationContext(), items, this);
        recyclerView.setAdapter(adapter);
    }
}
