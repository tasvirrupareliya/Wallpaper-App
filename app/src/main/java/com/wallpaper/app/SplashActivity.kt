package com.wallpaper.app

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.wallpaper.app.adapters.WallpaperAdapter
import com.wallpaper.app.databinding.ActivitySplashBinding
import com.wallpaper.app.model.ApiResponse
import com.wallpaper.app.model.ApiResponse2
import com.wallpaper.app.model.ApiResponse3
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Random

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageFiles = arrayOf(
            getDrawable(R.drawable.one),
            getDrawable(R.drawable.second),
            getDrawable(R.drawable.three),
            getDrawable(R.drawable.four),
            getDrawable(R.drawable.five),
            getDrawable(R.drawable.six)
        )

        val random = Random()
        val randomIndex = random.nextInt(imageFiles.size)

        try {
            binding.img.setImageDrawable(imageFiles[randomIndex])
        } catch (e: IOException) {
            e.printStackTrace()
        }

        Data.callfirst(this)
        Data.callfirst2(this)
    }
}

object Data {
    var flag = 0
    val arrayList = ArrayList<String>()
    val arrayList2 = ArrayList<String>()
    fun callfirst(activity: Activity) {
        val response = retrofitHelper.getInstance().create(apiService::class.java)

        val result = response.thumb_fetchData()

        result.enqueue(object : Callback<ApiResponse> {
            override fun onResponse(call: Call<ApiResponse>, response: Response<ApiResponse>) {
                if (response.isSuccessful) {

                    val response = response.body()

                    val categories = response?.category

                    for (category in categories.orEmpty()) {
                        val thumbUrls = category.thumb
                        arrayList.addAll(thumbUrls)
                    }
                    arrayList.shuffle()
                    flag++
                    checkFlagAndNavigate(activity)
                }
            }

            override fun onFailure(call: Call<ApiResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun callfirst2(activity: Activity) {
        val response = retrofitHelper.getInstance().create(apiService::class.java)

        val result = response.small_fetchData()

        result.enqueue(object : Callback<ApiResponse3> {

            override fun onResponse(call: Call<ApiResponse3>, response: Response<ApiResponse3>) {
                if (response.isSuccessful) {

                    val response = response.body()

                    val categories = response?.category

                    for (category in categories.orEmpty()) {
                        val thumbUrls = category.small
                        arrayList2.addAll(thumbUrls)
                    }
                    arrayList2.shuffle()
                    flag++
                    checkFlagAndNavigate(activity)
                }
            }

            override fun onFailure(call: Call<ApiResponse3>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun checkFlagAndNavigate(activity: Activity) {
        if (flag == 2) {
            nextActivity(activity)
        }
    }
}

fun nextActivity(activity: Activity) {
    val intent = Intent(activity, MainActivity::class.java)
    activity.startActivity(intent)
    activity.finish()
}