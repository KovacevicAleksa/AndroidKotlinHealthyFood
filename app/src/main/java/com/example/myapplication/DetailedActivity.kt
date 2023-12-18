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
        if (food != null ){
            val textView : TextView = findViewById(R.id.DetailedMain)
            val textView1 : TextView = findViewById(R.id.DetailedTxt)
            val imageView : ImageView = findViewById(R.id.Detailedimg)

            textView.text = food.name
            textView1.text = food.name
            imageView.setImageResource(food.image)
        }
    }
}