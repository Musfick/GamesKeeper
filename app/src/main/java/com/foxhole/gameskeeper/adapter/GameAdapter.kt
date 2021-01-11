package com.foxhole.gameskeeper.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.foxhole.gameskeeper.R
import com.foxhole.gameskeeper.databinding.LayoutItemGameBinding
import com.foxhole.gameskeeper.model.Game
import com.foxhole.gameskeeper.utils.Constants.GAME_VIEW_TYPE
import com.foxhole.gameskeeper.utils.Constants.NETWORK_VIEW_TYPE

/**
 * Created by Musfick Jamil on 1/11/2021$.
 */
class GameAdapter(private val context: Context) :
    PagingDataAdapter<Game, RecyclerView.ViewHolder>(GameDiffCallback()) {

    var lastPosition = -1

    class GameDiffCallback : DiffUtil.ItemCallback<Game>() {
        override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val item = getItem(position)

        setAnimation(holder, position)

        (holder as GameViewHolder).binding.apply {
            imageView.load(manipulateUrl(item!!.backgroundImage))
            itemView.setOnClickListener {
                onItemClick?.invoke(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            LayoutItemGameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GameViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount) {
            NETWORK_VIEW_TYPE
        } else {
            GAME_VIEW_TYPE
        }
    }

    var onItemClick: ((game: Game) -> Unit)? = null

    private fun setAnimation(holder: RecyclerView.ViewHolder, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, R.anim.item_anim)
            holder.itemView.startAnimation(animation)
            lastPosition = position;
        }

    }

    class GameViewHolder(val binding: LayoutItemGameBinding) : RecyclerView.ViewHolder(binding.root)

    private fun manipulateUrl(s: String): String {
        // /crop/600/400
        // /resize/1280/-
        return s.substring(0, 27) + "/resize/420/-" + s.substring(27, s.length)
    }
}