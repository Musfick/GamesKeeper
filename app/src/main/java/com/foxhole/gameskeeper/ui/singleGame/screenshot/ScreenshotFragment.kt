package com.foxhole.gameskeeper.ui.singleGame.screenshot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.foxhole.gameskeeper.R
import com.foxhole.gameskeeper.adapter.GameLoadStateAdapter
import com.foxhole.gameskeeper.adapter.ScreenshotAdapter
import com.foxhole.gameskeeper.base.BaseFragment
import com.foxhole.gameskeeper.databinding.FragmentScreenshotBinding
import com.foxhole.gameskeeper.model.Game
import com.foxhole.gameskeeper.ui.singleGame.SingleGameActivity
import com.foxhole.gameskeeper.ui.singleGame.SingleGameViewModel
import com.foxhole.gameskeeper.utils.Constants
import com.foxhole.gameskeeper.utils.Resource
import timber.log.Timber

class ScreenshotFragment : BaseFragment<FragmentScreenshotBinding>() {

    lateinit var viewModel: SingleGameViewModel
    lateinit var screenshotAdapter: ScreenshotAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (requireActivity() as SingleGameActivity).singleGameViewModel
        screenshotAdapter = (requireActivity() as SingleGameActivity).screenshotAdapter

        initView()


        viewModel.game.observe(viewLifecycleOwner, Observer {
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
    }

    private fun updateUI(game: Game?) {
        game?.let {
            it.screenshots?.let { screenshots ->
                screenshotAdapter.addItems(screenshots)
            }

        }
    }

    private fun initView() {
        val gridLayoutManager = GridLayoutManager(activity, 2)

        binding.screenshotRecyclerview.apply {
            this.layoutManager = gridLayoutManager
            this.setHasFixedSize(true)
            this.adapter = screenshotAdapter
        }
    }

    override fun setBinding(): FragmentScreenshotBinding = FragmentScreenshotBinding.inflate(layoutInflater)

}