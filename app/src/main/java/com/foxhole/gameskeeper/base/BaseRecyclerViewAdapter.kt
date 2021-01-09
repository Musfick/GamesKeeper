package com.foxhole.gameskeeper.base

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.foxhole.gameskeeper.R


/**
 * Created by Musfick Jamil on 4/29/2020$.
 */
abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = mutableListOf<T>()
    private var lastPosition = -1

    fun addItems(items: MutableList<T>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size


    abstract fun getLayoutId(): Int
    abstract fun getContext(): Context

    abstract fun setViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder

    abstract fun onBindData(holder: RecyclerView.ViewHolder?, item: T, position: Int)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(getLayoutId(), parent, false)
        return setViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position != items.size) {
            onBindData(holder, items[position], position)
            setAnimation(holder, position)
        }


    }

    private fun setAnimation(holder: RecyclerView.ViewHolder, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(getContext(), R.anim.item_anim)
            holder.itemView.startAnimation(animation)
            lastPosition = position;
        }

    }
}