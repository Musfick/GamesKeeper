package com.foxhole.gameskeeper.ui.singleGame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.foxhole.gameskeeper.R
import com.foxhole.gameskeeper.base.BaseActivity
import com.foxhole.gameskeeper.databinding.ActivitySingleGameBinding
import com.foxhole.gameskeeper.ui.singleGame.overview.OverviewFragment
import com.foxhole.gameskeeper.ui.singleGame.screenshot.ScreenshotFragment
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems

class SingleGameActivity : BaseActivity<ActivitySingleGameBinding>() {

    override fun onViewReady(savedInstanceState: Bundle?) {
        super.onViewReady(savedInstanceState)

        setSupportActionBar(binding.actionBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true);
        supportActionBar!!.setDisplayShowHomeEnabled(true);
        supportActionBar!!.title = ""

        lifecycle.addObserver(binding.youtubePlayerView)

        val fragmentAdapter = FragmentPagerItemAdapter(
                supportFragmentManager, FragmentPagerItems.with(this)
                .add("Overview", OverviewFragment::class.java)
                .add("Screenshots", ScreenshotFragment::class.java)
                .create()
        )

        binding.viewPager.adapter = fragmentAdapter
        binding.tabLayout.setViewPager(binding.viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setBinding(): ActivitySingleGameBinding = ActivitySingleGameBinding.inflate(layoutInflater)

}