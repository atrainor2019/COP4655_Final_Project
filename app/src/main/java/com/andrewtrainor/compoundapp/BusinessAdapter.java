package com.andrewtrainor.compoundapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder> {
    private Fragment frag = null;
    private LayoutInflater layoutInflater;
    private List<Business> data;

    BusinessAdapter(Context context, List<Business> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    public BusinessAdapter(Context context, List<Business> data, Fragment frag) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
        this.frag = frag;
    }

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.businesscardview, viewGroup, false);
        return new BusinessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final BusinessViewHolder viewHolder, int i) {

        final Business business = data.get(i);
        if (!business.getImgSrc().isEmpty()) {
            Picasso.with(layoutInflater.getContext()).load(business.getImgSrc()).into(viewHolder.Business_photo);
        }
        viewHolder.business = business;
        viewHolder.Business_title.setText(business.getTitle());
        viewHolder.business_lat = business.getLat();
        viewHolder.business_lon = business.getLon();

        viewHolder.setcallback(frag);

        viewHolder.toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    SecondaryActivity.getProfile().addBusinessToFavorites(business);
                    viewHolder.toggle.setChecked(true);

                Driver driver = new Driver();
                driver.update(SecondaryActivity.getProfile());

            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class BusinessViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView Business_description;
        TextView Business_title;
        private Fragment fragment = null;
        double business_lat;
        double business_lon;
        ImageView Business_photo;
        public Business business;
        private ToggleButton toggle;

        public void setcallback(Fragment fragment) {
            this.fragment = (FavFragment)fragment;
        }

        BusinessViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            Business_description = (TextView) itemView.findViewById(R.id.cardDescription);
            Business_title = (TextView) itemView.findViewById(R.id.cardTitle);
            Business_photo = (ImageView) itemView.findViewById(R.id.imageView);
            toggle = itemView.findViewById(R.id.favBtn);
        }
    }


}

