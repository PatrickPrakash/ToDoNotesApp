package com.hackerx.todonotesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textview.MaterialTextView;
import com.hackerx.todonotesapp.Adapter.AppConstants;

public class DetailsActivity extends AppCompatActivity {
    MaterialTextView details_title,details_description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        bindViews();
        setupIntentData();
    }

    private void setupIntentData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra(AppConstants.TITLE);
        String description = intent.getStringExtra(AppConstants.DESCRIPTION);
        details_title.setText(title);
        details_description.setText(description);
    }

    public void bindViews(){
        details_title = findViewById(R.id.details_title);
        details_description = findViewById(R.id.details_description);
    }
}