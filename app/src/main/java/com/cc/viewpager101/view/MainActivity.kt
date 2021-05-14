package com.cc.viewpager101.view

import android.Manifest.permission.ACCESS_FINE_LOCATION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_DENIED
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.cc.viewpager101.R
import com.cc.viewpager101.databinding.ActivityMainBinding
import com.cc.viewpager101.model.Case
import com.cc.viewpager101.model.GoogleMapDatabase
import com.cc.viewpager101.util.LogToConsole
import com.cc.viewpager101.view.adapters.ViewPagerAdapter
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    private lateinit var viewPager: ViewPager2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)

        viewPager = binding.pager

        val pagerAdapter =
            ViewPagerAdapter(this)
        viewPager.adapter = pagerAdapter

        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                LogToConsole.log("onPageScrollStateChanged, state : $state")
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                LogToConsole.log("onPageScrolled, $position")
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                LogToConsole.log("onPageSelected, $position")
                //here
            }

        })

        binding.bottomNav.setOnNavigationItemSelectedListener {
            LogToConsole.log("item selector")
            LogToConsole.log("menu item id : ${it.itemId}")
            LogToConsole.log("menu item icon : ${it.icon}")
            when (it.itemId) {
                R.id.solved_cases_item -> {
                    LogToConsole.log("0")
                    viewPager.currentItem = 0
                }
                R.id.open_cases -> {
                    LogToConsole.log("1")
                    viewPager.currentItem = 1
                }
                R.id.locations_item -> {
                    LogToConsole.log("2")

                    viewPager.currentItem = 2
                }
            }

            true

        }

        if (savedInstanceState == null) {
            val initialData = arrayOf(
                Case("missing pen", 1, LatLng(20.0, 30.0), false),
                Case("missing cat", 2, LatLng(40.0, 50.0), false),
                Case("missing lamp", 3, LatLng(60.0, 70.0), true),
                Case("missing paper", 4, LatLng(80.0, 90.0), true)
            )
            lifecycleScope.launch {
                GoogleMapDatabase.getInstance(applicationContext).caseDao().insertAllCases(*initialData)
            }

        }

    }

    override fun onStart() {
        super.onStart()

        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION), 700)
        }

    }
}