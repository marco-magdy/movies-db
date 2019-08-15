package com.movies.data.interactor

import com.movies.data.entity.MoviesResult
import com.movies.network.API
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

/**
 *Created by marco on 2019-08-15
 */
class SearchInteractor
@Inject
constructor(private val retrofit: Retrofit) {
    fun search(query: String): Observable<MoviesResult> = retrofit
            .create(API.Movies::class.java)
            .search(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}