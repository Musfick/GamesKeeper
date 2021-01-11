package com.foxhole.gameskeeper.ui

import android.os.Bundle
import androidx.activity.viewModels
import com.foxhole.gameskeeper.R
import com.foxhole.gameskeeper.adapter.GameAdapter
import com.foxhole.gameskeeper.base.BaseActivity
import com.foxhole.gameskeeper.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import setupWithNavController
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var gameAdapter: GameAdapter

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