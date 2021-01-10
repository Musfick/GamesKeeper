package com.foxhole.gameskeeper.ui.singleGame.screenshot

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.foxhole.gameskeeper.R
import com.foxhole.gameskeeper.base.BaseFragment
import com.foxhole.gameskeeper.databinding.FragmentScreenshotBinding

class ScreenshotFragment : BaseFragment<FragmentScreenshotBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun setBinding(): FragmentScreenshotBinding = FragmentScreenshotBinding.inflate(layoutInflater)

}