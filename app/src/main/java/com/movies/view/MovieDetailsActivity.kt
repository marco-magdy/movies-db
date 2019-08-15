package com.movies.view

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.movies.Constants.API.Extras.Companion.MOVIE_ID
import com.movies.R
import com.movies.data.entity.Movie
import com.movies.data.entity.Status
import com.movies.di.ViewModelFactory
import com.movies.helper.ImagesHelper
import com.movies.viewmodel.MovieDetailsViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.content_movie_details.*
import javax.inject.Inject

/**
 *Created by marco on 2019-08-09
 */
class MovieDetailsActivity : BaseActivity() {

    @field:[Inject]
    lateinit var viewModelFactory: ViewModelFactory<MovieDetailsViewModel>
    @field:[Inject]
    lateinit var imagesHelper: ImagesHelper
    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        AndroidInjection.inject(this)
        initViewModel()
        initObservables()
        intent.extras?.getString(MOVIE_ID)?.let { viewModel.movieDetails(it) }
    }


    override fun initViewModel() {
        viewModel =
                ViewModelProviders.of(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
    }

    override fun initObservables() {
        viewModel.movieLiveData.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> initViews(it.data!!)
                Status.ERROR -> showMessage(getString(R.string.error))
            }
        })
    }

    private fun initViews(movie: Movie) {
        ivPoster.setImageURI(Uri.parse(imagesHelper.poster(movie.posterPath ?: "")), this)
        movie.voteAverage?.let { progress.setProgress(it.toFloat() * 10, true) }
        movie.originalTitle?.let { tvTitle.text = it;tvToolbarTitle.text = it }
        movie.releaseDate?.let { tvReleaseDate.text = it }
        movie.overview?.let { tvOverview.text = it }
        ivDismiss.setOnClickListener { finish() }
    }

    companion object {
        fun start(context: Context, id: String) {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_ID, id)
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
}