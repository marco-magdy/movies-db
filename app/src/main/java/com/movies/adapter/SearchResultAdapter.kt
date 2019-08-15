package com.movies.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.LayoutRes
import com.movies.R
import com.movies.adapter.holder.SearchResultItem
import com.movies.data.entity.Movie
import java.util.*

/**
 * Created by marco on 2019-08-15
 */
class SearchResultAdapter(private val mContext: Context, @LayoutRes list: ArrayList<Movie>)
    : ArrayAdapter<Movie>(mContext, 0, list) {

    private var moviesList = ArrayList<Movie>()

    init {
        moviesList = list
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        var mView = convertView
        if (mView == null)
            mView = LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.item_search_result, parent, false)
        val searchItem = SearchResultItem(mView!!)
        searchItem.bind(moviesList[position])
        return mView
    }
}