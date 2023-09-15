package com.wallpaper.app

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import com.wallpaper.app.adapters.ViewPagerAdapter
import com.wallpaper.app.adapters.WallpaperAdapter
import com.wallpaper.app.databinding.ActivityMainBinding
import com.wallpaper.app.fragments.LatestFragment
import com.wallpaper.app.fragments.PopularFragment
import com.wallpaper.app.fragments.RandomFragment
import com.wallpaper.app.model.WallListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private lateinit var binding: ActivityMainBinding
private lateinit var actionBarToggle: ActionBarDrawerToggle
var currentPage = 1
val recordsPerPage = 100000
const val BASE_URL = "http://wallpapershaven.com/"


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBarToggle = ActionBarDrawerToggle(this, binding.drawerlayout, 0, 0)
        binding.drawerlayout.addDrawerListener(actionBarToggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarToggle.syncState()

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LatestFragment(), "Latest")
        adapter.addFragment(PopularFragment(), "Popular")
        adapter.addFragment(RandomFragment(), "Random")

        binding.viewPager.offscreenPageLimit = 3

        binding.viewPager.adapter = adapter
        binding.tablayout.setupWithViewPager(binding.viewPager)
    }
}
