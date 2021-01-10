package com.foxhole.gameskeeper.ui.explore

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.foxhole.gameskeeper.base.BaseFragment
import com.foxhole.gameskeeper.databinding.FragmentExploreBinding
import com.foxhole.gameskeeper.ui.singleGame.SingleGameActivity

class ExploreFragment : BaseFragment<FragmentExploreBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            Intent(requireActivity(), SingleGameActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    override fun setBinding(): FragmentExploreBinding = FragmentExploreBinding.inflate(layoutInflater)
}