package com.andrewtrainor.compoundapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andrewtrainor.compoundapp.BusinessData.Business;
import com.andrewtrainor.compoundapp.BusinessData.BusinessAdapter;
import com.andrewtrainor.compoundapp.BusinessData.BusinessData;
import com.andrewtrainor.compoundapp.BusinessData.Driver;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements OnMapReadyCallback {

    private com.andrewtrainor.compoundapp.BusinessData.Driver Driver;
    private Button input_btn;
    private EditText user_input;
    private BusinessAdapter adapter;
    private ArrayList<Business> business_items;
    private RecyclerView recyclerView;

    private FragmentActivity myContext;
    private GoogleMap mMap;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.search_fragment, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(new BusinessAdapter(getActivity().getApplicationContext(), new ArrayList<Business>()));

        input_btn = root.findViewById(R.id.search_btn);
        user_input = root.findViewById(R.id.SearchBox);

        final RadioGroup radio = (RadioGroup) root.findViewById(R.id.radioGroup);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        input_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch(radio.getCheckedRadioButtonId()) {
                    case R.id.radio0:
                        business_items.clear();
                        searchByName();
                        break;

                    case R.id.radio1:
                        business_items.clear();
                        searchByName();
                        break;

                    case R.id.radio6:
                        business_items.clear();
                        searchByLoc();
                        break;
                }
            }
        });

        business_items = new ArrayList<Business>();
        Driver = new Driver();

       trendingHot();

        return root;
    }

    public void searchByName() {
        String title = user_input.getText().toString();
        Driver.getByName(title, new BusinessData() {
            @Override
            public void BusinessRequest(ArrayList<Business> businesses) {
                business_items.clear();
                mMap.clear();
                for (Business business : businesses) {
                    business_items.add(business);
                    newLocation(business.getLat(), business.getLon(), business.getTitle());
                }

                adapter = new BusinessAdapter(getActivity().getApplicationContext(), business_items);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void searchByLoc() {

        String title = user_input.getText().toString();
        String [] nameLoc = title.split("\\s*,\\s*");
        String name = nameLoc [0];
        String loc = nameLoc [1];
        Driver.getByLoc(name, loc, new BusinessData() {
            @Override
            public void BusinessRequest(ArrayList<Business> businesses) {
                business_items.clear();
                mMap.clear();
                for (Business business : businesses) {
                    business_items.add(business);
                    newLocation(business.getLat(), business.getLon(), business.getTitle());
                }

                adapter = new BusinessAdapter(getActivity().getApplicationContext(), business_items);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    //set map on ready
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng pos = new LatLng(26.3750, -80.1011);

        mMap.addMarker(new
                MarkerOptions().position(pos).title("Your Current Location"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 11));
    }

    //update the location of the map
    void newLocation(double lat, double lon, String title) {
        if (mMap != null) {
            LatLng pos = new LatLng(lat, lon); //get new position
            mMap.addMarker(new
                    MarkerOptions().position(pos).title(title));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 11));

        }
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
}

