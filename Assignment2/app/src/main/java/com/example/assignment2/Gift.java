package com.example.assignment2;
// --------------------------------------------------------------------
// Assignment 2
// Written by: Shahe Bannis 2051001
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class Gift extends AppCompatActivity {

    DataBaseHelperGift db;

    RecyclerView recyclerView;
    RecyclerViewAdapterGift rAdapter;
    ArrayList<String> itemTitles = new ArrayList<>();
    ArrayList<String> itemDescr = new ArrayList<>();
    ArrayList<String> itemIsFav = new ArrayList<>();

    Button gift;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift);
        db = new DataBaseHelperGift(this);

        recyclerView = findViewById(R.id.recyclerView);

        //gets all the items from Gift databases
        Cursor data = db.getALlData();
        if(data.getCount() ==0){
            populate();
            showMessage ("error", "Nothing found. Go back and try again.");
            return;
        }
        //puts them in arraylist
        while(data.moveToNext()) {
            itemTitles.add(data.getString(1));
            itemDescr.add(data.getString(2));
            itemIsFav.add(data.getString(3));
        }

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        rAdapter = new RecyclerViewAdapterGift(itemTitles, itemDescr, itemIsFav, this);

        recyclerView.setAdapter(rAdapter);


        //opens a website if clicked
        gift = findViewById(R.id.whatGift);
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("https://en.wikipedia.org/wiki/Gift");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });

    }

    private void showMessage(String data, String toString) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(data);
        builder.setCancelable(true);
        builder.setMessage(toString);
        builder.show();
    }

    private void populate() {
        //uncomment for first time use then comment again to create and fill out the table
        db.insertData("Gift 1", "This is the first option of gifts available");
        db.insertData("Gift 2", "This is the second option of gifts available");
        db.insertData("Gift 3", "This is the third option of gifts available");
        db.insertData("Gift 4", "This is the fourth option of gifts available");
        db.insertData("Gift 5", "This is the fifth option of gifts available");
    }
}