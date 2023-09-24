package com.wallpaper.app

import com.wallpaper.app.Utils.Companion.base_url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object retrofitHelper {
    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}