package com.wallpaper.app.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class WallListResponse {
    @SerializedName("status")
    @Expose
    val status: Int? = null

    @SerializedName("images")
    @Expose
    val images: List<Image>? = null

     class Image{
        @SerializedName("picid")
        @Expose
         val picid: String? = null

        @SerializedName("thumbM")
        @Expose
         val thumbM: String? = null
    }
}