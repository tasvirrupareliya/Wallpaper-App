package com.wallpaper.app

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager

class Utils {
    companion object {

        var base_url = "https://api.npoint.io/"

        fun checkInternet(activity: Activity): Boolean {
            val connectivityManager =
                activity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}