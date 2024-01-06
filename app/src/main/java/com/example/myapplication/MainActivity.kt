package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {




    var responseData: String? = null
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

        foodList.add(Food(R.drawable.jabuka, "Jabuka",responseData ?: "NotFound"))
        foodList.add(Food(R.drawable.banana, "Banana",responseData ?: "NotFound"))
        foodList.add(Food(R.drawable.limun,"Limun",responseData ?: "NotFound"))
        foodList.add(Food(R.drawable.lubenica,"Lubenica",responseData ?: "NotFound"))
        foodList.add(Food(R.drawable.jagoda,"Jagoda",responseData ?: "NotFound"))
        foodList.add(Food(R.drawable.ananas,"Ananas",responseData ?: "NotFound"))

        foodAdapter = FoodAdapter(foodList)
        recycleView.adapter = foodAdapter



        foodAdapter.onItemClick = {
            val intent = Intent(this ,DetailedActivity::class.java)
            intent.putExtra("food", it)
            intent.putExtra("apidata", it.apidata)  // Dodajte podatke iz API-ja
            startActivity(intent)
        }

        // Make the network request asynchronously
        fetchData()

    }

    private fun fetchData() {
        val client = OkHttpClient()
        // Novi API link i ključ
        val url = "https://api.api-ninjas.com/v1/nutrition?query=1lb brisket and fries"
        val apiKey = "ePjBXaX16JtfTmvEuNmgMA==ZOuYxeSINJjBeLXA"

        val request = Request.Builder()
            .url(url)
            .addHeader("x-api-key", apiKey)  // Dodajte ključ kao zaglavlje zahteva
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                Log.e("MainActivity", "Poziv nije uspio", e)
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    runOnUiThread {
                        responseData = response.body?.string()
                        Log.d("MainActivity", "Odgovor je uspešan: $responseData")

                        for (food in foodList) {
                            food.apidata = responseData ?: ""
                        }

                        // Obavesti adapter da su podaci promenjeni
                        foodAdapter.notifyDataSetChanged()

                    }
                }
            }
        })
    }
}