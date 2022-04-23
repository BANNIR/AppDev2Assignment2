package com.example.assignment2;
// --------------------------------------------------------------------
// Assignment 2
// Written by: Shahe Bannis 2051001
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterFav extends RecyclerView.Adapter<RecyclerViewAdapterFav.ViewHolder> {
    ArrayList<String> itemList = new ArrayList<>();
    ArrayList<String> favorite = new ArrayList<>();
    ArrayList<String> itemDescr = new ArrayList<>();

    Context context;

    public RecyclerViewAdapterFav(ArrayList<String> itemList, ArrayList<String> itemDescr, ArrayList<String> favorite, Context context) {
        this.itemList = itemList;
        this.itemDescr = itemDescr;
        this.favorite = favorite;
        this.context = context;

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView name;
        TextView itemDescr;
        ImageView isFavorite, isntFavorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.itemImageView);
            itemDescr = itemView.findViewById(R.id.descriptionText);
            name = itemView.findViewById(R.id.titleText);
            isFavorite = itemView.findViewById(R.id.isntFavorite);
            isntFavorite = itemView.findViewById(R.id.isFavorite);
        }

        @Override
        public void onClick(View view) {
            Intent i = new Intent(context, Checkout.class);
            context.startActivity(i);
        }
    }

    @NonNull
    @Override
    public RecyclerViewAdapterFav.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false);
        return new RecyclerViewAdapterFav.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.name.setText(itemList.get(position));
        holder.itemDescr.setText(itemDescr.get(position));

        //changes the star depending if it's favorited or not
        if (favorite.get(position).equals("1")) {
            holder.isFavorite.setVisibility(View.VISIBLE);
            holder.isntFavorite.setVisibility(View.INVISIBLE);
        } else {
            holder.isFavorite.setVisibility(View.INVISIBLE);
            holder.isntFavorite.setVisibility(View.VISIBLE);
        }

        //continues to checkout
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Checkout.class);
                context.startActivity(i);
            }
        });
        holder.itemDescr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Checkout.class);
                context.startActivity(i);
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Checkout.class);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static Drawable getImage(Context context, String name) {
        return context.getResources().getDrawable(context.getResources().getIdentifier(name, "drawable", context.getPackageName()));
    }
}