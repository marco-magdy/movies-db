package com.movies

import android.app.Activity
import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.movies.di.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


/**
 *Created by marco on 2019-08-09
 */
class App : Application(), HasActivityInjector {

    @field:[Inject]
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun onCreate() {
        super.onCreate()
        AppComponent.initialize(this).inject(this)
        Fresco.initialize(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }
}