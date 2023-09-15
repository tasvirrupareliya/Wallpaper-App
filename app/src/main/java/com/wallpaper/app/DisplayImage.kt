package com.wallpaper.app

import android.app.DownloadManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.wallpaper.app.databinding.ActivityDisplayImageBinding

private lateinit var binding: ActivityDisplayImageBinding

class DisplayImage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newImageUrl = intent.getStringExtra("newImageUrl")

        if (newImageUrl != null) {
            Glide.with(this)
                .load(newImageUrl)
                .into(binding.viewImage)
        }

        binding.btnDownload.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                val uri = Uri.parse(newImageUrl)
                val request = DownloadManager.Request(uri)
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                downloadManager.enqueue(request)
            }
        })
    }
}