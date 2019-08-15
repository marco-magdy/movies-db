package com.movies.adapter.holder


import android.view.View
import com.movies.data.entity.Movie
import com.movies.di.AppComponent
import com.movies.helper.ImagesHelper
import com.movies.view.MovieDetailsActivity
import kotlinx.android.synthetic.main.item_search_result.view.*
import javax.inject.Inject

/**
 *Created by marco on 2019-08-15
 */
class SearchResultItem(private val view: View) {

    private val ivLogo = view.ivLogo
    private val tvTitle = view.tvTitle
    private val tvSubtitle = view.tvSubtitle

    @field:[Inject]
    lateinit var imagesHelper: ImagesHelper

    init {
        AppComponent.get().inject(this)
    }

    fun bind(movie: Movie) {
        movie.posterPath?.let { ivLogo.setImageURI(imagesHelper.compatLogo(it)) }
        movie.title?.let { tvTitle.text = it }
        movie.originalTitle?.let { tvSubtitle.text = it }
        movie.id?.let { movieId ->
            view.setOnClickListener { MovieDetailsActivity.start(view.context, movieId.toString()) }
        }
    }
}