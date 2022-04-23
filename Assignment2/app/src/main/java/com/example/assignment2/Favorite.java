package com.example.assignment2;

// --------------------------------------------------------------------
// Assignment 2
// Written by: Shahe Bannis 2051001
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;

public class Favorite extends AppCompatActivity {


    DataBaseHelperFlower dbf;
    DataBaseHelperGift dbg;

    RecyclerView recyclerView;
    RecyclerViewAdapterFav rAdapter;
    ArrayList<String> itemTitles = new ArrayList<>();
    ArrayList<String> itemDescr = new ArrayList<>();
    ArrayList<String> itemIsFav = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        dbf = new DataBaseHelperFlower(this);
        dbg = new DataBaseHelperGift(this);

        recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //gets all the favorited items from both databases
        Cursor datag = dbg.getALlFav();
        Cursor dataf = dbf.getALlFav();
        //puts them in arraylist
        while(datag.moveToNext()) {
            itemTitles.add(datag.getString(1));
            itemDescr.add(datag.getString(2));
            itemIsFav.add(datag.getString(3));
        }
        while(dataf.moveToNext()) {
            itemTitles.add(dataf.getString(1));
            itemDescr.add(dataf.getString(2));
            itemIsFav.add(dataf.getString(3));
        }

        System.out.println(itemTitles);

        rAdapter = new RecyclerViewAdapterFav(itemTitles, itemDescr, itemIsFav, this);

        recyclerView.setAdapter(rAdapter);
    }



}