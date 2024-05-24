package com.example.task91p;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnCreateAdvert;
    private Button btnShowLostFound;
    private Button btnShowOnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the buttons
        btnCreateAdvert = findViewById(R.id.btn_create_advert);
        btnShowLostFound = findViewById(R.id.btn_show_lost_found);
        btnShowOnMap = findViewById(R.id.btn_map);

        // Set click listener for the "CREATE A NEW ADVERT" button
        btnCreateAdvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to CreateAdvertActivity
                Intent intent = new Intent(MainActivity.this, CreateAdvertActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for the "SHOW ALL LOST & FOUND ITEMS" button
        btnShowLostFound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ShowLostFoundActivity
                Intent intent = new Intent(MainActivity.this, ShowLostFoundActivity.class);
                startActivity(intent);
            }
        });
        btnShowOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to ShowLostFoundActivity
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
