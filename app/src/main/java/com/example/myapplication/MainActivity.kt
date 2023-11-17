package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recycleView: RecyclerView
    private lateinit var foodList : ArrayList<Food>
    private lateinit var foodAdapter : FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycleView = findViewById(R.id.recyclerView)
        recycleView.setHasFixedSize(true)
        recycleView.layoutManager = LinearLayoutManager(this)

        foodList = ArrayList()

        foodList.add(Food(R.drawable.jabuka, "Jabuka"))
        foodList.add(Food(R.drawable.banana, "Banana"))
        foodList.add(Food(R.drawable.limun,"Limun"))
        foodList.add(Food(R.drawable.lubenica,"Lubenica"))
        foodList.add(Food(R.drawable.jagoda,"Jagoda"))
        foodList.add(Food(R.drawable.ananas,"Ananas"))

        foodAdapter = FoodAdapter(foodList)
        recycleView.adapter = foodAdapter
    }
}