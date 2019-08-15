package com.movies.adapter.holder

import android.content.Context
import android.net.Uri
import android.view.View
import com.movies.data.entity.Movie
import com.movies.di.AppComponent
import com.movies.helper.ImagesHelper
import com.movies.view.MovieDetailsActivity
import kotlinx.android.synthetic.main.item_movie.view.*
import javax.inject.Inject

/**
 *Created by marco on 2019-08-09
 */
class MovieViewHolder(private val view: View) : BaseViewHolder<Movie>(view) {

    @field:[Inject]
    lateinit var imagesHelper: ImagesHelper
    @field:[Inject]
    lateinit var context: Context

    private val ivPoster = view.ivPoster
    private val tvTitle = view.tvTitle
    private val tvReleaseDate = view.tvReleaseDate
    private val tvOverview = view.tvOverview

    init {
        AppComponent.get().inject(this)
    }

    override fun bind(item: Movie) {
        ivPoster.setImageURI(Uri.parse(imagesHelper.logo(item.posterPath ?: "")), context)
        item.title?.let { tvTitle.text = it }
        item.releaseDate?.let { tvReleaseDate.text = it }
        item.overview?.let { tvOverview.text = it }
        view.setOnClickListener { MovieDetailsActivity.start(context, item.id.toString()) }
    }
}
