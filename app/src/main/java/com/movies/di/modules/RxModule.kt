package com.movies.di.modules

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

/**
 *Created by marco on 2019-08-10
 */
@Module
class RxModule {

    @Provides
    fun compositeDisposable(): CompositeDisposable = CompositeDisposable()
}