package com.movies.data.interactor

import com.movies.data.entity.Movie
import com.movies.network.API
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

/**
 *Created by marco on 2019-08-13
 */
class MovieInteractor
@Inject
constructor(private val retrofit: Retrofit) {
    fun details(id: String): Observable<Movie> = retrofit
            .create(API.Movies::class.java)
            .movieDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}