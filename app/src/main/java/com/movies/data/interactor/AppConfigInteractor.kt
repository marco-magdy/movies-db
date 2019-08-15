package com.movies.data.interactor

import com.movies.data.entity.Configurations
import com.movies.network.API
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

/**
 *Created by marco on 2019-08-10
 */
class AppConfigInteractor
@Inject
constructor(private val retrofit: Retrofit) {
    fun config(): Observable<Configurations> {
        return retrofit.create(API.Configuration::class.java)
            .config()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}