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

//Fragment for Favorites
public class FavFragment extends Fragment {
    private BusinessAdapter adapter;
    private ArrayList<Business> items;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fave_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));

        //create new ArrayList for Business class as items.
        items = new ArrayList<Business>();

        //For each of the businesses in the list, get the favorited items for the logged in profile.
        for(Business business : SecondaryActivity.getProfile().getFavorites()) {

            //add the favorited business to the items list
            items.add(business);

            //display the favorited business with the help of BusinessAdapter
            adapter = new BusinessAdapter(getActivity().getApplicationContext(), items, this);
            recyclerView.setAdapter(adapter);
        }

        return root;
    }

    //Method unFavorite that removes a favorited business from the current recyclerView for the fave_fragment.
    //This will update the view and show that the item is removed.
    public void unFavorite() {
        for(Business b : SecondaryActivity.getProfile().getFavorites()) {
            System.out.println(b.getTitle());
        }

        //readd the favorited items as a result of the unFavorite event. this will not include the removed item.
        items = new ArrayList<Business>();
        for(Business business : SecondaryActivity.getProfile().getFavorites()) {
            items.add(business);
        }

        //Get the updated view and show that the card has been removed.
        adapter = new BusinessAdapter(getActivity().getApplicationContext(), items, this);
        recyclerView.setAdapter(adapter);
    }
}
