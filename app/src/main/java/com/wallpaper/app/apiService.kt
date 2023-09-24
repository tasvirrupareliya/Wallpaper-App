package com.wallpaper.app

import com.wallpaper.app.model.ApiResponse
import com.wallpaper.app.model.ApiResponse2
import com.wallpaper.app.model.ApiResponse3
import retrofit2.Call
import retrofit2.http.GET

interface apiService {
    @GET("b50ab141adff9212c141")
    fun thumb_fetchData(): Call<ApiResponse>

/* @GET("558256a53505882d7f67")
 fun thumbHD_fetchData(): Call<ApiResponse2>

 @GE"6993799fedafd4eadc18")
 fun UHD_fetchData(): Call<>

 @GET("0b989d43a35239ba918d")
 fun fullHD_fetchData(): Call<>*/

 @GET("1d1f8147927041cf68e8")
 fun small_fetchData(): Call<ApiResponse3>
}