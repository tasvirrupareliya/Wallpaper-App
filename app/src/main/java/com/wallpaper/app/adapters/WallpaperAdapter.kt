package com.wallpaper.app.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wallpaper.app.DisplayImage
import com.wallpaper.app.MainActivity
import com.wallpaper.app.R
import com.wallpaper.app.model.WallListResponse

class WallpaperAdapter(val wallpapers: ArrayList<WallListResponse.Image>) :
    RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.list_wallpaper_img)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WallpaperAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_wallpaper, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: WallpaperAdapter.ViewHolder, position: Int) {
        val thumbnailUrl = wallpapers.get(position).thumbM

        Glide.with(holder.itemView)
            .load(thumbnailUrl)
            .centerCrop()
            .into(holder.imageView)

        holder.imageView.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                val name = thumbnailUrl?.substringAfterLast("/")?.substringBeforeLast(".")
                val viewImageUrl = "https://images.hdqwalls.com/download/$name-1080x2340.jpg"

                val intent = Intent(holder.itemView.context, DisplayImage::class.java)
                intent.putExtra("newImageUrl", viewImageUrl)
                holder.itemView.context.startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int {
        return wallpapers.size
    }
}
