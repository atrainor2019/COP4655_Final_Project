package com.andrewtrainor.compoundapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private Driver Driver;
    private Button input_btn;
    private EditText user_input;
    private BusinessAdapter adapter;
    private ArrayList<Business> business_items;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.search_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new BusinessAdapter(getActivity().getApplicationContext(), new ArrayList<Business>()));

        input_btn = root.findViewById(R.id.search_btn);
        user_input = root.findViewById(R.id.SearchBox);

        final RadioGroup radio = (RadioGroup) root.findViewById(R.id.radioGroup);

        input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(radio.getCheckedRadioButtonId()) {
                    case R.id.radio0: // movie title
                        business_items.clear();
                        searchByName();
                        break;

                    case R.id.radio1: // director
                        business_items.clear();
                        break;
                }
            }
        });

        business_items = new ArrayList<Business>();
        Driver = new Driver();

        return root;
    }

    public void searchByCategory() {

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

    public void searchByName() {
        String title = user_input.getText().toString();
        Driver.getByName(title, new BusinessData() {
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

    public void searchByLoc() {

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

