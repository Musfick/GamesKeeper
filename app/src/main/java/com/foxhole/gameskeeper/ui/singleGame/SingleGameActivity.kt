package com.foxhole.gameskeeper.ui.singleGame

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.foxhole.gameskeeper.R
import com.foxhole.gameskeeper.adapter.ScreenshotAdapter
import com.foxhole.gameskeeper.base.BaseActivity
import com.foxhole.gameskeeper.databinding.ActivitySingleGameBinding
import com.foxhole.gameskeeper.model.Game
import com.foxhole.gameskeeper.ui.singleGame.overview.OverviewFragment
import com.foxhole.gameskeeper.ui.singleGame.screenshot.ScreenshotFragment
import com.foxhole.gameskeeper.utils.Resource
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class SingleGameActivity : BaseActivity<ActivitySingleGameBinding>() {

    val singleGameViewModel: SingleGameViewModel by viewModels()

    var isFavorite: Boolean = false

    @Inject
    lateinit var screenshotAdapter: ScreenshotAdapter

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

        singleGameViewModel.game.observe(this, Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Timber.d("loading")
                }
                Resource.Status.SUCCESS -> {
                    Timber.d("success")
                    updateUI(it.data)
                }
                Resource.Status.ERROR -> {
                    Timber.d("error")
                }

            }
        })

        singleGameViewModel.isFavorite.observe(this, Observer {
            isFavorite = it

            if (it) binding.floatingActionButton.setColorFilter(Color.RED)
            else binding.floatingActionButton.setColorFilter(Color.WHITE)
        })

        binding.floatingActionButton.setOnClickListener {
            if (isFavorite) {
                singleGameViewModel.deleteFavoriteGame()
                lifecycleScope.launch {
                    showTostify("Removed from favorite list !")
                }
            } else {
                singleGameViewModel.addGameToFavorite()
                lifecycleScope.launch {
                    showTostify("Added to favorite list !")
                }
            }

        }


    }

    private fun updateUI(game: Game?) {
        game?.let {
            binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    it.video?.let { clip ->
                        youTubePlayer.loadVideo(clip.videoid, 0F)

                        ///UnMute for sound
                        youTubePlayer.mute()
                    } ?: youTubePlayer.loadVideo("A67ZkAd1wmI", 0F)
                }
            })
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun setBinding(): ActivitySingleGameBinding = ActivitySingleGameBinding.inflate(layoutInflater)

    suspend fun showTostify(message: String) {

        binding.include.messageTv.text = message

        binding.include.messageBg.animate().translationY(-80f).duration = 200L
        binding.include.messageTv.animate().translationY(-80f).duration = 200L

        delay(2000L)

        binding.include.messageBg.animate().translationY(80f).duration = 500L
        binding.include.messageTv.animate().translationY(80f).duration = 500L
    }

}