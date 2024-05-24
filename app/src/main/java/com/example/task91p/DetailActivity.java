package com.example.task91p;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private TextView typeTextView, nameTextView, phoneTextView, descriptionTextView, dateTextView, addressTextView;
    private Button deleteButton;
    private DataBase db;
    private int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize TextViews
        typeTextView = findViewById(R.id.type);
        nameTextView = findViewById(R.id.name);
        phoneTextView = findViewById(R.id.phone);
        descriptionTextView = findViewById(R.id.description);
        dateTextView = findViewById(R.id.date);
        addressTextView = findViewById(R.id.address);
        deleteButton = findViewById(R.id.delete_button);

        db = new DataBase(this); // Initialize the database helper

        // Get data from the intent
        Intent intent = getIntent();
        itemId = intent.getIntExtra("id", -1);
        String type = intent.getStringExtra("type");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String description = intent.getStringExtra("description");
        String date = intent.getStringExtra("date");
        String address = intent.getStringExtra("address");

        // Set the data to the TextViews
        typeTextView.setText(type);
        nameTextView.setText(name);
        phoneTextView.setText(phone);
        descriptionTextView.setText(description);
        dateTextView.setText(date);
        addressTextView.setText(address);

        // Set up the delete button to delete the item and finish the activity
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteData(itemId);
                startActivity(new Intent().setClass(DetailActivity.this, MainActivity.class));
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finish();
    }
}
