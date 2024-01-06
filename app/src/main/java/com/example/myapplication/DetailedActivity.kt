package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed)

        val food = intent.getParcelableExtra<Food>("food")
        val apidata = intent.getStringExtra("apidata")  // Dobijanje podataka iz Intent-a

        if (food != null ){
            val textView : TextView = findViewById(R.id.DetailedMain)
            val imageView : ImageView = findViewById(R.id.Detailedimg)
            val apidataTextView : TextView = findViewById(R.id.data)

            textView.text = food.name
            imageView.setImageResource(food.image)
            apidataTextView.text = apidata  // Postavljanje podataka iz API-ja

        }
    }
}