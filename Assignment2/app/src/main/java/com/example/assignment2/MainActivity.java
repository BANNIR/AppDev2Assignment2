package com.example.assignment2;
// --------------------------------------------------------------------
// Assignment 2
// Written by: Shahe Bannis 2051001
// For Application Development 2 (Mobile) - Winter 2022
// --------------------------------------------------------------------
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button flower;
    Button gift;
    Button fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flower = findViewById(R.id.flowerList);
        gift = findViewById(R.id.giftList);
        fav = findViewById(R.id.fav);

        //opens the list of favorited items
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Favorite.class);
                startActivity(i);
            }
        });

        //opens the list of flowers
        flower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Flower.class);
                startActivity(i);
            }
        });

        //opens the list of gifts
        gift.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Gift.class);
                startActivity(i);
            }
        });
    }
}