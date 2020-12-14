package com.andrewtrainor.compoundapp.BusinessData;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.andrewtrainor.compoundapp.FavFragment;
import com.andrewtrainor.compoundapp.R;
import com.andrewtrainor.compoundapp.SecondaryActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

//Class for handling the dynamic cardView objects and populating with business information

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder> {
    private Fragment frag = null;
    private LayoutInflater layoutInflater;
    private List<Business> data;

    //Structure the BusinessAdapter for data list
    public BusinessAdapter(Context context, List<Business> data) {
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

    //This method is used for setting and displaying the loaded content of a CardView
    //This contains the business title, location, description, rating, phone, type, etc.

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
        viewHolder.Business_description.setText(business.getDescription());
        viewHolder.business_rating.setText(business.getRating());
        viewHolder.Business_phone.setText(business.getPhone());
        viewHolder.Business_type.setText(business.getBusiness_type());

        viewHolder.setcallback(frag);

        if(SecondaryActivity.getProfile().businessExistsInFavorites(business)) {
            viewHolder.toggle.setChecked(true);
        } else {
            viewHolder.toggle.setChecked(false);
        }

        viewHolder.toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                //Handles toggling for user favorites and adding favorites on checked change.
                if(!SecondaryActivity.getProfile().businessExistsInFavorites(business)) {
                    SecondaryActivity.getProfile().addBusinessToFavorites(business);
                    viewHolder.toggle.setChecked(true);
                } else {
                    SecondaryActivity.getProfile().removeFromFavorites(business);
                    viewHolder.toggle.setChecked(false);
                }

                //New Data Extraction, get user Profile
                Driver driver = new Driver();
                driver.update(SecondaryActivity.getProfile());

                //Unfavorite a favorite if it is not favorited anymore by the user.
                if(frag!= null){
                    ((FavFragment) frag).unFavorite();
                }

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
        TextView Business_phone;
        TextView Business_type;
        private Fragment fragment = null;
        double business_lat;
        double business_lon;
        TextView business_rating;
        ImageView Business_photo;
        public Business business;
        private ToggleButton toggle;

        public void setcallback(Fragment fragment) {
            this.fragment = (FavFragment)fragment;
        }

        //This holder handles the setters for cardview content.

        BusinessViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            Business_description = (TextView) itemView.findViewById(R.id.cardDescription);
            Business_phone = (TextView) itemView.findViewById(R.id.cardPhone);
            Business_title = (TextView) itemView.findViewById(R.id.cardTitle);
            business_rating = (TextView) itemView.findViewById(R.id.cardRating);
            Business_photo = (ImageView) itemView.findViewById(R.id.imageView);
            Business_type = (TextView) itemView.findViewById(R.id.cardType);
            toggle = itemView.findViewById(R.id.favBtn);
        }
    }
}

