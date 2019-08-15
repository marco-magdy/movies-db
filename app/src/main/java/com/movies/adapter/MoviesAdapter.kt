package com.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.movies.R
import com.movies.adapter.holder.BaseViewHolder
import com.movies.adapter.holder.LoaderViewHolder
import com.movies.adapter.holder.MovieViewHolder
import com.movies.data.entity.Movie
import javax.inject.Inject


/**
 *Created by marco on 2019-08-09
 */
class MoviesAdapter
@Inject
constructor() : BaseAdapter<BaseViewHolder<Movie>, Movie>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Movie> {
        val view =
                LayoutInflater.from(parent.context).inflate(getViewLayout(viewType), parent, false)
        return when (viewType) {
            TYPE_MOVIE -> MovieViewHolder(view)
            else -> LoaderViewHolder(view)
        }
    }

    private fun getViewLayout(type: Int): Int {
        return when (type) {
            TYPE_MOVIE -> R.layout.item_movie
            else -> R.layout.item_loading
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (items!![position].isLoadingItem) TYPE_LOADER else TYPE_MOVIE
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Movie>, position: Int) {
        holder.bind(items?.get(position)!!)
    }

    fun addLoading() {
        for (i in 0 until 5) {
            add(Movie(isLoadingItem = true))
        }
    }

    fun removeLoading() {
        items?.removeAll { it.isLoadingItem }
        notifyDataSetChanged()
    }


    companion object {
        private const val TYPE_MOVIE = 0
        private const val TYPE_LOADER = 1
    }
}