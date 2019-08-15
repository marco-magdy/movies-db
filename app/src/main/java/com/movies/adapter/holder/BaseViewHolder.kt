package com.movies.adapter.holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *Created by marco on 2019-08-09
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: T)
}