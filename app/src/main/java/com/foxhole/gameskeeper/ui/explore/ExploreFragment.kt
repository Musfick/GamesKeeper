package com.foxhole.gameskeeper.ui.explore

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.foxhole.gameskeeper.adapter.GameAdapter
import com.foxhole.gameskeeper.adapter.GameLoadStateAdapter
import com.foxhole.gameskeeper.base.BaseFragment
import com.foxhole.gameskeeper.databinding.FragmentExploreBinding
import com.foxhole.gameskeeper.model.Game
import com.foxhole.gameskeeper.ui.MainActivity
import com.foxhole.gameskeeper.ui.MainViewModel
import com.foxhole.gameskeeper.ui.singleGame.SingleGameActivity
import com.foxhole.gameskeeper.utils.Constants.GAME_VIEW_TYPE
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.observeOn
import timber.log.Timber


class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    lateinit var viewModel: MainViewModel
    lateinit var gameAdapter: GameAdapter
    var isSwipeRefreshing = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        gameAdapter = (requireActivity() as MainActivity).gameAdapter
        viewModel = (requireActivity() as MainActivity).mainViewModel

        initView()

        lifecycleScope.launchWhenCreated {
            viewModel.explore.collectLatest {
                gameAdapter.submitData(lifecycle, it)
            }
        }

        binding.swipeRefresh.setOnRefreshListener {

        }

        lifecycleScope.launchWhenStarted {
            gameAdapter.loadStateFlow.collectLatest { loadStates ->

                val isError = loadStates.refresh is LoadState.Error

                if (isError) {
                    showTostify("No Internet !", "#d9534f", false)
                } else {
                    dismissTostify()
                }

                binding.swipeRefresh.isRefreshing = loadStates.refresh is LoadState.Loading

                if (!binding.swipeRefresh.isRefreshing && isSwipeRefreshing && !isError) {
                    showTostify("Refresh completed !", "#4BB543", true)
                    isSwipeRefreshing = false
                }


            }
        }

        binding.swipeRefresh.setOnRefreshListener {
            isSwipeRefreshing = true
            gameAdapter.refresh()
        }

        gameAdapter.onItemClick = { game ->
            Intent(requireActivity(), SingleGameActivity::class.java).also {
                it.putExtra("game", game)
                startActivity(it)
            }
        }

    }

    private fun initView() {
        val gridLayoutManager = GridLayoutManager(activity, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                val viewType = gameAdapter.getItemViewType(position)
                return if (viewType == GAME_VIEW_TYPE) 1
                else 2
            }
        }

        binding.exploreRecyclerView.apply {
            this.layoutManager = gridLayoutManager
            this.setHasFixedSize(true)
            this.adapter = gameAdapter.withLoadStateFooter(GameLoadStateAdapter(gameAdapter::retry))
        }
    }

    override fun setBinding(): FragmentExploreBinding =
            FragmentExploreBinding.inflate(layoutInflater)

    suspend fun showTostify(message: String, color: String, autoDismiss: Boolean) {

        binding.include.messageBg.setBackgroundColor(Color.parseColor(color))
        binding.include.messageTv.text = message

        binding.include.messageBg.animate().translationY(-80f).duration = 200L
        binding.include.messageTv.animate().translationY(-80f).duration = 200L

        delay(2000L)

        if (autoDismiss) {
            binding.include.messageBg.animate().translationY(80f).duration = 500L
            binding.include.messageTv.animate().translationY(80f).duration = 500L
        }

    }

    fun dismissTostify() {
        //Clear animation
        binding.include.messageBg.animate().translationY(0f)
        binding.include.messageTv.animate().translationY(0f)
    }

}