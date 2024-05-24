package com.example.task91p;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateAdvertActivity extends AppCompatActivity {
    private EditText nameEditText, phoneEditText, descriptionEditText, locationEditText;
    private RadioGroup radioGroup;
    private RadioButton rbLost, rbFound;
    private DatePicker datePicker;
    private Button submitButton, getLocation;
    private DataBase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        // Initialize views
        nameEditText = findViewById(R.id.name);
        phoneEditText = findViewById(R.id.phone);
        descriptionEditText = findViewById(R.id.description);
        datePicker = findViewById(R.id.datePicker);
        locationEditText = findViewById(R.id.location);
        radioGroup = findViewById(R.id.radioGroup);
        rbLost = findViewById(R.id.rb_lost);
        rbFound = findViewById(R.id.rb_found);
        submitButton = findViewById(R.id.btn_submit);
        getLocation = findViewById(R.id.btn_getLocation);

        // Initialize database
        db = new DataBase(this);
        if (getIntent().getDoubleExtra("latitude",0)==0){
        }else {
            locationEditText.setText(getIntent().getDoubleExtra("latitude",0)+","+getIntent().getDoubleExtra("longitude",0));
        }

        // Set submit button click listener
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get data from views
                String type = rbLost.isChecked() ? "Lost" : "Found";
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                String description = descriptionEditText.getText().toString();
                String location = locationEditText.getText().toString();

                // Get date from DatePicker and format it as a string
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth();
                int year = datePicker.getYear();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String date = sdf.format(calendar.getTime());

                // Insert data into database
                long result = db.insertData(type, name, phone, description, date, location);

                if (result != -1) {
                    Toast.makeText(CreateAdvertActivity.this, "Advert created successfully!", Toast.LENGTH_SHORT).show();
                    // Clear fields after successful insert
                    nameEditText.setText("");
                    phoneEditText.setText("");
                    descriptionEditText.setText("");
                    locationEditText.setText("");
                    startActivity(new Intent().setClass(CreateAdvertActivity.this, MainActivity.class));
                    finish();
                } else {
                    Toast.makeText(CreateAdvertActivity.this, "Failed to create advert.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent().setClass(CreateAdvertActivity.this, MapActivity.class));
                finish();
            }
        });
    }
}
