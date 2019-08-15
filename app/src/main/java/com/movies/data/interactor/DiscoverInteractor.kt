package com.movies.data.interactor

import com.movies.data.entity.MoviesResult
import com.movies.network.API
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

/**
 *Created by marco on 2019-08-11
 */
class DiscoverInteractor
@Inject
constructor(private val retrofit: Retrofit) {
    fun discover(page: Int): Observable<MoviesResult> = retrofit
            .create(API.Movies::class.java)
            .discover(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}