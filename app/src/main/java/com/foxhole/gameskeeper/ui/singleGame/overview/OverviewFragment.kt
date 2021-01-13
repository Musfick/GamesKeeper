package com.foxhole.gameskeeper.ui.singleGame.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import coil.load
import com.foxhole.gameskeeper.R
import com.foxhole.gameskeeper.base.BaseFragment
import com.foxhole.gameskeeper.databinding.FragmentOverviewBinding
import com.foxhole.gameskeeper.model.Game
import com.foxhole.gameskeeper.ui.singleGame.SingleGameActivity
import com.foxhole.gameskeeper.ui.singleGame.SingleGameViewModel
import com.foxhole.gameskeeper.utils.Resource
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.jsoup.Jsoup
import timber.log.Timber

class OverviewFragment : BaseFragment<FragmentOverviewBinding>() {

    private lateinit var viewModel: SingleGameViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (requireActivity() as SingleGameActivity).singleGameViewModel

        viewModel.game.observe(requireActivity(), Observer {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Timber.d("loading")
                    binding.layoutProgress.visibility = View.VISIBLE
                    binding.layoutInfo.visibility = View.INVISIBLE
                    binding.layoutNoInternet.visibility = View.INVISIBLE
                }
                Resource.Status.SUCCESS -> {
                    Timber.d("success")
                    updateUI(it.data)

                    binding.layoutProgress.visibility = View.INVISIBLE
                    binding.layoutInfo.visibility = View.VISIBLE
                    binding.layoutNoInternet.visibility = View.INVISIBLE
                }
                Resource.Status.ERROR -> {
                    Timber.d("error")
                    binding.layoutProgress.visibility = View.INVISIBLE
                    binding.layoutInfo.visibility = View.INVISIBLE
                    binding.layoutNoInternet.visibility = View.VISIBLE
                }

            }
        })

        binding.retryBtn.setOnClickListener {
            viewModel.getGameDetails()
        }
    }

    private fun updateUI(game: Game?) {
        game?.let {
            binding.posterImageView.load(manipulateUrl(it.backgroundImage))
            binding.summeryTextView.text = Jsoup.parse(it.description).text()
            binding.ratingTextView.text = it.rating.toString()
            binding.releaseTextView.text = it.released
        }
    }

    private fun manipulateUrl(s: String): String {
        // /crop/600/400
        // /resize/1280/-
        return s.substring(0, 27) + "/resize/420/-" + s.substring(27, s.length)
    }


    override fun setBinding(): FragmentOverviewBinding = FragmentOverviewBinding.inflate(layoutInflater)

}