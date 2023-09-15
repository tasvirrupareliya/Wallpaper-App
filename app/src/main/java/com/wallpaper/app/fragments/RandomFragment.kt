package com.wallpaper.app.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.wallpaper.app.BASE_URL
import com.wallpaper.app.MainActivity
import com.wallpaper.app.R
import com.wallpaper.app.adapters.WallpaperAdapter
import com.wallpaper.app.currentPage
import com.wallpaper.app.databinding.FragmentRandomBinding
import com.wallpaper.app.getData
import com.wallpaper.app.model.WallListResponse
import com.wallpaper.app.recordsPerPage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RandomFragment : Fragment() {

    private lateinit var binding: FragmentRandomBinding
    var wallpaperList: ArrayList<WallListResponse.Image> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRandomBinding.inflate(inflater, container, false)

        getwalls()

        binding.randomRecyclerview.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = binding.randomRecyclerview.layoutManager?.itemCount
                val lastVisibleItem =
                    (binding.randomRecyclerview.layoutManager as GridLayoutManager).findLastVisibleItemPosition()

                // Load more items when the user is near the end of the list
                if (totalItemCount != null) {
                    if (totalItemCount - lastVisibleItem <= 6) {
                        currentPage++
                        getwalls()
                    }
                }
            }
        })

        return binding.root
    }

    fun getwalls(){
        val retrofitbuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        retrofitbuilder.create(getData::class.java)
        val retrofitData = retrofitbuilder.create(getData::class.java)

        val call = retrofitData.getDatawallList("images", recordsPerPage, currentPage, "Random")

        call.enqueue(object : Callback<WallListResponse> {
            override fun onResponse(
                call: Call<WallListResponse>,
                response: Response<WallListResponse>
            ) {
                if (response.isSuccessful) {

                    val jsonResponse = Gson().toJson(response.body())

                    val wallpaperResponse =
                        Gson().fromJson(jsonResponse, WallListResponse::class.java)

                    /* wallpaperResponse?.let {
                         val responseList = listOf(it)
                         wallpaperList.addAll(responseList)
                     }

                     wallpaperAdapter = WallpaperAdapter(wallpaperList)
                     recyclerview.adapter = wallpaperAdapter

                     wallpaperAdapter.notifyDataSetChanged()*/

                    binding.randomRecyclerview.layoutManager = GridLayoutManager(activity, 3)

                    wallpaperResponse.images?.map { image ->
                        WallListResponse().apply {
                            wallpaperList.add(image)
                        }
                    }

                    var wallpaperAdapter = WallpaperAdapter(wallpaperList)

                    binding.randomRecyclerview.adapter = wallpaperAdapter
                    wallpaperAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<WallListResponse>, t: Throwable) {
                Log.e("data2", t.toString())
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RandomFragment().apply {
            }
    }
}