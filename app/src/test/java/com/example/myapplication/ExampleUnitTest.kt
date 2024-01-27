package com.example.myapplication

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import android.os.Build
import okhttp3.OkHttpClient
import okhttp3.Request
import org.junit.Before
import org.robolectric.Robolectric
import org.robolectric.util.ReflectionHelpers


@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P], manifest = Config.NONE)
class MainActivityTest {

    @Before
    fun setup() {
        ReflectionHelpers.setStaticField(Build.VERSION::class.java, "SDK_INT", 30)
    }

    @Test
    fun TestSdk() {
        val currentVersion = Build.VERSION.SDK_INT
        println("Trenutna verzija $currentVersion")
        assert(currentVersion > 24) // Android verzija > 7.0 (2016)
    }

    @Test
    fun TestDrawableFile() {

        assert(R.drawable.jabuka != null && R.drawable.banana != null)
    }

    @Test
    fun TestExecutionTime() {



        val startTime = System.currentTimeMillis()

        //Robolectric.buildActivity(MainActivity::class.java).create().get()

        val query = "apple"


        val client = OkHttpClient()
        val url = "${MainActivity.BASE_URL}$query"
        val request = Request.Builder()
            .url(url)
            .addHeader("x-api-key", MainActivity.API_KEY)
            .build()

        val response = client.newCall(request).execute()

        println(response)

        val endTime = System.currentTimeMillis()
        val executionTime = endTime - startTime

        println("Vreme izvršavanja: $executionTime ms")

        assert(executionTime<5000) // do 5 sekundi
        assert(response.code == 200)

    }





}
