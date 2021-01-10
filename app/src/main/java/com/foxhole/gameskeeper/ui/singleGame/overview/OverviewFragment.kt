package com.foxhole.gameskeeper.ui.singleGame.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.foxhole.gameskeeper.R
import com.foxhole.gameskeeper.base.BaseFragment
import com.foxhole.gameskeeper.databinding.FragmentOverviewBinding

class OverviewFragment : BaseFragment<FragmentOverviewBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun setBinding(): FragmentOverviewBinding = FragmentOverviewBinding.inflate(layoutInflater)

}