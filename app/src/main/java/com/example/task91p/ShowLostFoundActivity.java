package com.example.task91p;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ShowLostFoundActivity extends AppCompatActivity {
    private ListView listView; // ListView to display lost and found items
    private DataBase db; // Database helper to interact with the SQLite database
    private List<LostBean> lostItems; // List to hold the lost and found items

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_lost_found); // Set the layout for this activity

        listView = findViewById(R.id.listView); // Find the ListView in the layout
        db = new DataBase(this); // Initialize the database helper

        lostItems = db.getAllData(); // Retrieve all lost and found items from the database
        LostItemsAdapter adapter = new LostItemsAdapter(this, lostItems); // Create an adapter with the retrieved items
        listView.setAdapter(adapter); // Set the adapter to the ListView
    }
}
