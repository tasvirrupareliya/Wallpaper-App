package com.wallpaper.app.adapters

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.wallpaper.app.DisplayImage
import com.wallpaper.app.MainActivity
import com.wallpaper.app.R

class WallpaperAdapter(private val context: Context, private val apiArray: ArrayList<String>) :
    RecyclerView.Adapter<WallpaperAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_wallpaper, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = apiArray[position]

        Glide.with(context)
            .load(items)
            .listener(object : RequestListener<Drawable>{
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>,
                    isFirstResource: Boolean
                ): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>?,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    holder.hideLoader()
                    return false
                }
            })
            .into(holder.imageView)


        holder.imageView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {

                val intent = Intent(context, DisplayImage::class.java)

                intent.putExtra("url", apiArray.get(position))
                context.startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int {
        return apiArray.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.list_wallpaper_img)
        val loader: LottieAnimationView = itemView.findViewById(R.id.animation_view)

        fun hideLoader() {
            loader.visibility = View.GONE
        }
    }
}
