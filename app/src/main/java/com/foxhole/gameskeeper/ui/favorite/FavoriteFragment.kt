package com.foxhole.gameskeeper.ui.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.foxhole.gameskeeper.adapter.FavoriteGameAdapter
import com.foxhole.gameskeeper.base.BaseFragment
import com.foxhole.gameskeeper.databinding.FragmentFavoriteBinding
import com.foxhole.gameskeeper.ui.MainActivity
import com.foxhole.gameskeeper.ui.MainViewModel
import com.foxhole.gameskeeper.ui.singleGame.SingleGameActivity
import timber.log.Timber

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    lateinit var viewModel: MainViewModel
    lateinit var favoriteGameAdapter: FavoriteGameAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (requireActivity() as MainActivity).mainViewModel
        favoriteGameAdapter = (requireActivity() as MainActivity).favoriteGameAdapter

        initView()

        viewModel.favoriteGames.observe(viewLifecycleOwner, Observer {
            favoriteGameAdapter.addItems(it.toMutableList())
        })

        favoriteGameAdapter.onItemClick = { game ->
            Intent(requireActivity(), SingleGameActivity::class.java).also {
                it.putExtra("game", game)
                startActivity(it)
            }
        }
    }

    private fun initView() {
        val gridLayoutManager = GridLayoutManager(activity, 2)

        binding.favoriteRecyclerView.apply {
            this.layoutManager = gridLayoutManager
            this.setHasFixedSize(true)
            this.adapter = favoriteGameAdapter
        }
    }

    override fun setBinding(): FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater)

}