package com.example.assignment2;
// --------------------------------------------------------------------
// Assignment 2
// Written by: Shahe Bannis 2051001
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapterGift extends RecyclerView.Adapter<RecyclerViewAdapterGift.ViewHolder> {
    ArrayList<String> itemList = new ArrayList<>();
    ArrayList<String> favorite = new ArrayList<>();
    ArrayList<String> itemDescr = new ArrayList<>();
    DataBaseHelperGift db;
    int i = 0;

    Context context;

    public RecyclerViewAdapterGift(ArrayList<String> itemList, ArrayList<String> itemDescr, ArrayList<String> favorite, Context context) {
        this.itemList = itemList;
        this.favorite = favorite;
        this.itemDescr = itemDescr;
        this.context = context;

         db = new DataBaseHelperGift(context);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView image;
        TextView name;
        TextView itemDescr;
        ImageView isFavorite, isntFavorite;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.itemImageView);
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
    public RecyclerViewAdapterGift.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterGift.ViewHolder holder, int position) {
        holder.name.setText(itemList.get(position));
        holder.itemDescr.setText(itemDescr.get(position));

        //gets the images and displays them
        i++;
        String imgName = "g" + i;
        holder.image.setImageDrawable(getImage(context, imgName));

        //changes the star depending if it's favorited or not
        if (favorite.get(position).equals("1")) {
            holder.isFavorite.setVisibility(View.VISIBLE);
            holder.isntFavorite.setVisibility(View.INVISIBLE);
        } else {
            holder.isFavorite.setVisibility(View.INVISIBLE);
            holder.isntFavorite.setVisibility(View.VISIBLE);
        }

        //changes the star picture on the click + updates the database
        holder.isFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.isFavorite.setVisibility(View.INVISIBLE);
                holder.isntFavorite.setVisibility(View.VISIBLE);
                db.updateFav(0, holder.getAdapterPosition() + 1 + "");
            }
        });

        holder.isntFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.isFavorite.setVisibility(View.VISIBLE);
                holder.isntFavorite.setVisibility(View.INVISIBLE);
                db.updateFav(1, holder.getAdapterPosition() + 1 + "");
            }
        });

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
