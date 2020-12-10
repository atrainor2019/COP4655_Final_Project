package com.andrewtrainor.compoundapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.BusinessViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Business> data;


    BusinessAdapter(Context context, List<Business> data){
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @Override
    public BusinessViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.businesscardview,viewGroup,false);
        return new BusinessViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusinessViewHolder viewHolder, int i) {

        // bind the textview with data received

        final Business business = data.get(i);
        if (!business.getImgSrc().isEmpty()) {
            Picasso.with(layoutInflater.getContext()).load(business.getImgSrc()).into(viewHolder.Business_photo);
        }
        viewHolder.business = business;
        viewHolder.Business_title.setText(business.getTitle());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public static class BusinessViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView Business_description;
        TextView Business_title;
        ImageView Business_photo;
        public Business business;

        BusinessViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            Business_description = (TextView)itemView.findViewById(R.id.cardDescription);
            Business_title = (TextView)itemView.findViewById(R.id.cardTitle);
            Business_photo= (ImageView)itemView.findViewById(R.id.imageView);
        }
    }

}

