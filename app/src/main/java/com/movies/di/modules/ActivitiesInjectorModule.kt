package com.movies.di.modules

import com.movies.view.MovieDetailsActivity
import com.movies.view.MoviesActivity
import com.movies.view.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 *Created by marco on 2019-08-09
 */
@Module
abstract class ActivitiesInjectorModule {

    @ContributesAndroidInjector
    abstract fun injectSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun injectMoviesActivity(): MoviesActivity

    @ContributesAndroidInjector
    abstract fun injectMovieDetailsActivity(): MovieDetailsActivity
}