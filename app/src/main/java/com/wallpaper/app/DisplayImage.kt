package com.wallpaper.app

import android.animation.ValueAnimator
import android.app.WallpaperManager
import android.content.Context
import android.content.Intent
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.graphics.values
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.otaliastudios.zoom.ZoomSurfaceView
import com.wallpaper.app.databinding.ActivityDisplayImageBinding


private lateinit var binding: ActivityDisplayImageBinding

class DisplayImage : AppCompatActivity() {


    private var titlePost: TextView? = null
    private var taggroup: ChipGroup? = null
    private var bottomsheetarrow: ImageButton? = null
    private var loaded = false


    var blocked = false
    var Full_video: ZoomSurfaceView? = null


    //bottom sheet
    private var sheet_body: ConstraintLayout? = null

    //buttons
    private var setWallPaperButton: FloatingActionButton? = null
    private var saveWallpaperButton: FloatingActionButton? = null
    private var setfavorite: FloatingActionButton? = null
    private var blockimage: FloatingActionButton? = null
    private var backbutton: ImageButton? = null

    //bottom buttons
    private var setwallpaper_bottom_button: Button? = null
    private var goback_bottom_button: FloatingActionButton? = null
    private var container_bottom_button: ConstraintLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDisplayImageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newImageUrl = intent.getStringExtra("url")
        var modifiedUrl: String = ""

        if (newImageUrl!!.contains("small")) {
            modifiedUrl = newImageUrl.replace("/small/", "/thumb_HD/")
            Log.e("111", modifiedUrl)

        } else if (newImageUrl.contains("thumb")) {
            modifiedUrl = newImageUrl.replace("/thumb/", "/Full_HD/")
            Log.e("111", modifiedUrl)

        }

        if (newImageUrl != null) {
            Glide.with(this)
                .load(modifiedUrl)
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
                        binding.animationView.visibility = View.GONE
                        return false
                    }
                })
                .into(binding.fullImage)
        }

        /* binding.btnDownload.setOnClickListener(object : View.OnClickListener {
             override fun onClick(v: View?) {
                 val downloadManager = getSystemService(DOWNLOAD_SERVICE) as DownloadManager
                 val uri = Uri.parse(newImageUrl)
                 val request = DownloadManager.Request(uri)
                 request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                 downloadManager.enqueue(request)
             }
         })*/

        BottomSheetBehavior.from(binding.ImageInfoBottomSheet).apply {

            this.state = BottomSheetBehavior.STATE_COLLAPSED
            this.isHideable = false

            val Views = LayoutInflater.from(this@DisplayImage).inflate(R.layout.bottom_sheet, null)
            binding.ImageInfoBottomSheet.addView(Views)

            //set bottom sheet view info
            taggroup = Views.findViewById(R.id.TagGroup)
            taggroup!!.isVisible = false
            // titlePost = Views.findViewById(R.id.title_post);
            sheet_body = Views.findViewById(R.id.bottom_sheet_body)

            //buttons
            setWallPaperButton = Views.findViewById(R.id.set_bottomsheet_floatingbutton);
            saveWallpaperButton = Views.findViewById(R.id.save_imageButton);
            setfavorite = Views.findViewById(R.id.favorite_bottomsheet_floatingbutton);
            bottomsheetarrow = Views.findViewById(R.id.pullbottom);

            bottomsheetarrow!!.setOnClickListener {
                if (this.state == BottomSheetBehavior.STATE_EXPANDED)
                    this.state = BottomSheetBehavior.STATE_COLLAPSED;
                else
                    this.state = BottomSheetBehavior.STATE_EXPANDED;
            }

            /* url_post?.let{
                 var uriImage = Uri.parse(myDataLocal!!.Image_url);
                 if(uriImage.scheme == "content" || uriImage.scheme == "file"){
                     it.visibility = View.GONE;
                     (Views.findViewById(R.id.splitter_text) as TextView).visibility = View.GONE;
                 }else{
                     it.setOnClickListener {
                         val linkuri = Uri.parse("https://${myDataLocal!!.post_url}");
                         this@Image_Activity.startActivity(Intent(Intent.ACTION_VIEW,linkuri));
                     }
                 }
             }*/

            var isContainerVisible = true

            binding.fullImage.setOnClickListener(object : View.OnClickListener {
                override fun onClick(v: View?) {
                    if (isContainerVisible) {
                        binding.ImageInfoBottomSheet.visibility = View.GONE
                    } else {
                        binding.ImageInfoBottomSheet.visibility = View.VISIBLE
                    }
                    isContainerVisible = !isContainerVisible
                }
            })

            blockimage?.let {
                it.setOnClickListener {
                    it.animate().apply {
                        duration = 500
                    }
                    if (!blocked) {
                        blocked = true;
                        //tempblockdatavase.add_image_info_to_database(myDataLocal!!);
                        Toast.makeText(this@DisplayImage, "added to block list", Toast.LENGTH_SHORT)
                            .show();
                    } else {
                        Toast.makeText(
                            this@DisplayImage,
                            "removed from the block list",
                            Toast.LENGTH_SHORT
                        ).show();
                        //tempblockdatavase.remove_image_info_from_database(myDataLocal!!);
                    }
                }
            }

            /* setfavorite?.setOnClickListener {
                 val found = isOnDatabase();
                 val button = it as FloatingActionButton;

                 if(!found){
                     tempdatabase.add_image_info_to_database(myDataLocal!!);
                     button.setImageResource(R.drawable.ic_heartfull);
                     Log.d(Image_Activity::class.java.simpleName, myDataLocal!!.Image_name);
                 }
                 else{
                     tempdatabase.remove_image_info_from_database(myDataLocal!!);
                     button.setImageResource(R.drawable.ic_favorite);
                 }

                 for(i in database.imageinfo_list){
                     Log.i("database","name: ${i.Image_auther}")
                 }
             }*/

            setWallPaperButton?.setOnClickListener {
                if (!loaded) {
                    Toast.makeText(
                        this@DisplayImage,
                        "Please wait for the image to load",
                        Toast.LENGTH_LONG
                    ).show();
                    return@setOnClickListener;
                }


                // normal wallpaper
                //hide bottom sheet and replace it with set wallpaper button
                this@apply.state = BottomSheetBehavior.STATE_COLLAPSED;
                binding.ImageInfoBottomSheet.animate().apply {
                    this?.duration = 100;
                    this?.translationY(200f);
                }
                container_bottom_button?.animate().apply {
                    this?.withStartAction {
                        container_bottom_button!!.visibility = View.VISIBLE;
                    }
                    this?.duration = 200
                    this?.translationY(0f)
                }
            }
            //hide setwallpaper button and show bottomsheet
            goback_bottom_button?.setOnClickListener {
                binding.ImageInfoBottomSheet.animate().apply {
                    this?.duration = 200;
                    this?.translationY(0f);
                }
                container_bottom_button?.animate().apply {
                    this?.duration = 100;
                    this?.translationY(200f);
                    this?.withEndAction {
                        container_bottom_button?.visibility = View.INVISIBLE;
                    }
                }
            }
        }
    }
}