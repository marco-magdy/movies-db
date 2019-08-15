package com.movies.adapter

import androidx.recyclerview.widget.RecyclerView

/**
 *Created by marco on 2019-08-09
 */
abstract class BaseAdapter<VH : RecyclerView.ViewHolder, T>
    : RecyclerView.Adapter<VH>() {

    protected var items: ArrayList<T>? = ArrayList()

    fun add(item: T) {
        items?.add(item)
        notifyItemInserted(items?.size!!.minus(1))
    }

    fun addAll(items: ArrayList<T>) {
        this.items?.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun clear() {
        items?.clear()
        notifyDataSetChanged()
    }
}