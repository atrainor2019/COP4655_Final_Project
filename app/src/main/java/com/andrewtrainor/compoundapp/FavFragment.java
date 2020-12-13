package com.andrewtrainor.compoundapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FavFragment extends Fragment {
    private BusinessAdapter adapter;
    private ArrayList<Business> items;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fave_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new BusinessAdapter(getActivity().getApplicationContext(), new ArrayList<Business>()));

        items = new ArrayList<Business>();

        for(Business business : SecondaryActivity.getProfile().getFavorites()) {
            items.add(business);
            adapter = new BusinessAdapter(getActivity().getApplicationContext(), items, this);
            recyclerView.setAdapter(adapter);
        }

        return root;
    }
}
