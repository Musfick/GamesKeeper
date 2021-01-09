package com.foxhole.gameskeeper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Created by Musfick Jamil on 5/6/2020$.
 */
abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: T? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = this.setBinding()
        return binding.root
    }

    abstract fun setBinding(): T

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}