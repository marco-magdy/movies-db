package com.movies.di

import android.app.Application
import com.movies.App
import com.movies.adapter.holder.MovieViewHolder
import com.movies.adapter.holder.SearchResultItem
import com.movies.di.modules.ActivitiesInjectorModule
import com.movies.di.modules.ApplicationModule
import com.movies.di.modules.NetworkModule
import com.movies.di.modules.RxModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector

/**
 *Created by marco on 2019-08-09
 */
@Component(
        modules = [
            ApplicationModule::class,
            ActivitiesInjectorModule::class,
            NetworkModule::class,
            RxModule::class]
)
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }

    companion object {
        private lateinit var component: AppComponent
        fun initialize(application: Application): AppComponent {
            component =
                    DaggerAppComponent.builder()
                            .application(application)
                            .build()
            return component
        }

        fun get(): AppComponent {
            if (this::component.isInitialized)
                return component
            else
                throw UninitializedPropertyAccessException("You cannot call get() appComponent as it's not initialized")
        }
    }

    fun inject(moviesViewHolder: MovieViewHolder)
    fun inject(searchResultItem: SearchResultItem)

}