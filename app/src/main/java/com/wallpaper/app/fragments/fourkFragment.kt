package com.wallpaper.app.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.wallpaper.app.Data
import com.wallpaper.app.adapters.WallpaperAdapter
import com.wallpaper.app.apiService
import com.wallpaper.app.databinding.FragmentFourkBinding
import com.wallpaper.app.model.ApiResponse
import com.wallpaper.app.model.ApiResponse2
import com.wallpaper.app.retrofitHelper
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class fourkFragment : Fragment() {

    private lateinit var binding: FragmentFourkBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentFourkBinding.inflate(inflater, container, false)

        addrecyclerview()

        return binding.root
    }

    fun addrecyclerview(){
        binding.fourkRecyclerview.layoutManager = GridLayoutManager(activity, 3)
        val adapter = WallpaperAdapter(requireActivity(), Data.arrayList)
        binding.fourkRecyclerview.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            fourkFragment().apply {

            }
    }
}