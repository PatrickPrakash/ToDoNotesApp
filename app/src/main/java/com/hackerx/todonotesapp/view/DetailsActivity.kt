package com.hackerx.todonotesapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textview.MaterialTextView
import com.hackerx.todonotesapp.Utlis.AppConstants
import com.hackerx.todonotesapp.R

class DetailsActivity : AppCompatActivity(){
    val TAG = "DetailsActivity"
    lateinit var details_title:MaterialTextView
    lateinit var details_description:MaterialTextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        bindViews()
        setupIntentData()

    }

    private fun setupIntentData() {
        val intent = intent
        val title = intent.getStringExtra(AppConstants.TITLE)
        val description = intent.getStringExtra(AppConstants.DESCRIPTION)
        details_title.text = title;
        details_description.text = description
    }

    private fun bindViews() {
        details_title = findViewById(R.id.details_title)
        details_description = findViewById(R.id.details_description)
    }
}