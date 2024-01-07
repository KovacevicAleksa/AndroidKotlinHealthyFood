package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodList: ArrayList<Food>
    private lateinit var foodAdapter: FoodAdapter

    companion object {
        private const val BASE_URL = "https://api.api-ninjas.com/v1/nutrition?query="
        private const val API_KEY = "ePjBXaX16JtfTmvEuNmgMA==ZOuYxeSINJjBeLXA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)

        foodList = ArrayList()

        foodList.add(Food(R.drawable.jabuka, "Jabuka"))
        foodList.add(Food(R.drawable.banana, "Banana"))
        foodList.add(Food(R.drawable.limun, "Limun"))
        foodList.add(Food(R.drawable.lubenica, "Lubenica"))
        foodList.add(Food(R.drawable.jagoda, "Jagoda"))
        foodList.add(Food(R.drawable.ananas, "Ananas"))

        foodAdapter = FoodAdapter(foodList)
        recyclerView.adapter = foodAdapter

        foodAdapter.onItemClick = {
            val intent = Intent(this, DetailedActivity::class.java)
            intent.putExtra("food", it)
            intent.putExtra("apidata", it.apidata)
            startActivity(intent)
        }

        // Use a coroutine to fetch data for each food item
        CoroutineScope(Dispatchers.Main).launch {
            fetchData("apple", 0)
            fetchData("banana", 1)
            fetchData("lemon", 2)
            fetchData("watermelon", 3)
            fetchData("strawberry", 4)
            fetchData("pineapple", 5)
        }
    }

    private suspend fun fetchData(query: String, index: Int) {
        val client = OkHttpClient()
        val url = "$BASE_URL$query"
        val request = Request.Builder()
            .url(url)
            .addHeader("x-api-key", API_KEY)
            .build()

        withContext(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()

                response.use {
                    val responseData = it.body?.string()
                    Log.d("MainActivity", "Response successful: $responseData")

                    // Set the response data for the correct food item
                    foodList[index].apidata = responseData ?: "NotFound"
                    withContext(Dispatchers.Main) {
                        foodAdapter.notifyItemChanged(index)
                    }
                }
            } catch (e: IOException) {
                e.printStackTrace()
                Log.e("MainActivity", "API call failed", e)
            }
        }
    }
}
