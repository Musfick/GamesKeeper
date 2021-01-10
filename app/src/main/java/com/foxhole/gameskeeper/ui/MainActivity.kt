package com.foxhole.gameskeeper.ui

import android.os.Bundle
import com.foxhole.gameskeeper.R
import com.foxhole.gameskeeper.base.BaseActivity
import com.foxhole.gameskeeper.databinding.ActivityMainBinding
import setupWithNavController

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun onViewReady(savedInstanceState: Bundle?) {
        super.onViewReady(savedInstanceState)
        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        }
    }

    override fun setBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {

        val navGraphIds = listOf(
                R.navigation.explore,
                R.navigation.favorite
        )

        binding.bottomNav.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = supportFragmentManager,
                containerId = R.id.nav_host_container,
                intent = intent
        )
    }

}