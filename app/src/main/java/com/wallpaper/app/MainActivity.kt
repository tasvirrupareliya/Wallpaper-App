package com.wallpaper.app

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.wallpaper.app.adapters.ViewPagerAdapter
import com.wallpaper.app.databinding.ActivityMainBinding
import com.wallpaper.app.fragments.LatestFragment
import com.wallpaper.app.fragments.fourkFragment
import com.wallpaper.app.fragments.CategoryFragment


private lateinit var binding: ActivityMainBinding
private lateinit var actionBarToggle: ActionBarDrawerToggle

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
        adapter.addFragment(LatestFragment(), "Popular")
        adapter.addFragment(fourkFragment(), "4K")
        adapter.addFragment(CategoryFragment(), "Category")

        binding.viewPager.offscreenPageLimit = 3

        binding.viewPager.adapter = adapter
        binding.tablayout.setupWithViewPager(binding.viewPager)
    }
}
