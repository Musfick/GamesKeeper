package com.foxhole.gameskeeper.ui.favorite

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.foxhole.gameskeeper.base.BaseFragment
import com.foxhole.gameskeeper.databinding.FragmentFavoriteBinding

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setBinding(): FragmentFavoriteBinding = FragmentFavoriteBinding.inflate(layoutInflater)

}