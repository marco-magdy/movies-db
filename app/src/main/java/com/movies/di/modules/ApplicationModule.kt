package com.movies.di.modules

import android.app.Application
import android.content.Context
import com.movies.App
import dagger.Module
import dagger.Provides

/**
 *Created by marco on 2019-08-11
 */
@Module
class ApplicationModule {

    @Provides
    fun context(application: Application): Context = application

    @Provides
    fun app(application: Application): App = application as App
}