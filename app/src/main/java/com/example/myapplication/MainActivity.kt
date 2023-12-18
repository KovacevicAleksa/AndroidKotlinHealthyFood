package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import android.util.Log
import com.google.gson.JsonArray
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream


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

        val query = "1lb brisket and fries"
        val apiKey = "ePjBXaX16JtfTmvEuNmgMA==ZOuYxeSINJjBeLXA"
        val apiUrl = "https://api.api-ninjas.com/v1/nutrition?query=$query"

        apiUrl
            .httpGet()
            .header("X-Api-Key" to apiKey)
            .response { request, response, result ->
                when (result) {
                    is Result.Success -> {
                        // Prikazi status koristeći response.status.value
                        Log.d("Tag", "Status code: ${response.statusCode}")

                        val data = String(response.data)
                        var data1 = response.data

                        // Prikazi telo odgovora kao string
                        Log.d("Tag", "Response body: ${JSONArray(String(response.data)).getJSONObject(2)}")
                        Log.d("Tag", "Response body data1: ${data1}")
                        Log.d("Tag", "Response body data: ${data}")




                    }
                    is Result.Failure -> {
                        val error = String(response.data)
                        Log.d("Tag", "Error: $error")
                    }
                }
            }







        foodList.add(Food(R.drawable.jabuka, "Jabuka"))
        foodList.add(Food(R.drawable.banana, "Banana"))
        foodList.add(Food(R.drawable.limun,"Limun"))
        foodList.add(Food(R.drawable.lubenica,"Lubenica"))
        foodList.add(Food(R.drawable.jagoda,"Jagoda"))
        foodList.add(Food(R.drawable.ananas,"Ananas"))
        foodList.add(Food(R.drawable.jabuka, "Jabuka"))
        foodList.add(Food(R.drawable.banana, "Banana"))
        foodList.add(Food(R.drawable.limun,"Limun"))
        foodList.add(Food(R.drawable.lubenica,"Lubenica"))
        foodList.add(Food(R.drawable.jagoda,"Jagoda"))
        foodList.add(Food(R.drawable.ananas,"Ananas"))

        foodAdapter = FoodAdapter(foodList)
        recycleView.adapter = foodAdapter

        foodAdapter.onItemClick = {
            val intent = Intent(this ,DetailedActivity::class.java)
            intent.putExtra("food", it)
            startActivity(intent)
        }
    }
}