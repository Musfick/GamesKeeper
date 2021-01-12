package com.foxhole.gameskeeper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import coil.load
import com.foxhole.gameskeeper.base.BaseRecyclerViewAdapter
import com.foxhole.gameskeeper.databinding.LayoutItemGameBinding
import com.foxhole.gameskeeper.databinding.LayoutItemScreenshotBinding
import com.foxhole.gameskeeper.model.Screenshot

/**
 * Created by Musfick Jamil on 1/11/2021$.
 */
class ScreenshotAdapter(private val context: Context) : BaseRecyclerViewAdapter<Screenshot, LayoutItemScreenshotBinding>() {


    override fun setBinding(parent: ViewGroup): LayoutItemScreenshotBinding = LayoutItemScreenshotBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun onBindData(holder: Companion.BaseViewHolder<LayoutItemScreenshotBinding>, item: Screenshot, position: Int) {
        holder.binding.imageView.load(manipulateUrl(item.image))
    }

    private fun manipulateUrl(s: String): String {
        // /crop/600/400
        // /resize/1280/-
        // /resize/420/-
        return s.substring(0, 27) + "/resize/420/-" + s.substring(27, s.length)
    }


}