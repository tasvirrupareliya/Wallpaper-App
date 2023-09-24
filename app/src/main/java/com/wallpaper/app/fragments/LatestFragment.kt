package com.wallpaper.app.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.wallpaper.app.Data
import com.wallpaper.app.adapters.WallpaperAdapter
import com.wallpaper.app.apiService
import com.wallpaper.app.databinding.FragmentLatestBinding
import com.wallpaper.app.model.ApiResponse
import com.wallpaper.app.nextActivity
import com.wallpaper.app.retrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LatestFragment : Fragment() {

    private lateinit var binding: FragmentLatestBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLatestBinding.inflate(inflater, container, false)

        addrecyclerview()

        return binding.root
    }

    fun addrecyclerview(){
        binding.latestRecyclerview.layoutManager = GridLayoutManager(activity, 3)
        val adapter = WallpaperAdapter(requireActivity(), Data.arrayList2)
        binding.latestRecyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = LatestFragment().apply {}
    }
}