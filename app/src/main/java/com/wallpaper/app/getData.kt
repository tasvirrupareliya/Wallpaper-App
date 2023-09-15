package com.wallpaper.app

import com.wallpaper.app.model.WallListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface getData {
    @GET("public/api5")
    fun getDatawallList( @Query("req") req: String,
                         @Query("records") records: Int,
                         @Query("page") page: Int,
                         @Query("order_by") orderBy: String): Call<WallListResponse>

}