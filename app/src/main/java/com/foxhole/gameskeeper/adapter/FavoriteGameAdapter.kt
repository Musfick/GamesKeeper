package com.foxhole.gameskeeper.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import coil.load
import com.foxhole.gameskeeper.base.BaseRecyclerViewAdapter
import com.foxhole.gameskeeper.databinding.LayoutItemGameBinding
import com.foxhole.gameskeeper.model.Game

/**
 * Created by Musfick Jamil on 1/12/2021$.
 */
class FavoriteGameAdapter() : BaseRecyclerViewAdapter<Game, LayoutItemGameBinding>() {

    var onItemClick: ((game: Game) -> Unit)? = null

    override fun setBinding(parent: ViewGroup): LayoutItemGameBinding = LayoutItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun onBindData(holder: Companion.BaseViewHolder<LayoutItemGameBinding>, item: Game, position: Int) {
        holder.binding.apply {
            imageView.load(manipulateUrl(item.backgroundImage))
            itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    private fun manipulateUrl(s: String): String {
        // /crop/600/400
        // /resize/1280/-
        return s.substring(0, 27) + "/resize/420/-" + s.substring(27, s.length)
    }
}