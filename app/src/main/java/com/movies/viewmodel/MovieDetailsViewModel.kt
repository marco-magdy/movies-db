package com.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movies.data.entity.Movie
import com.movies.data.entity.Resource
import com.movies.data.interactor.MovieInteractor
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 *Created by marco on 2019-08-10
 */
class MovieDetailsViewModel
@Inject
constructor(private val compositeDisposable: CompositeDisposable,
            private val movieInteractor: MovieInteractor) : ViewModel() {

    var movieLiveData = MutableLiveData<Resource<Movie>>()

    fun movieDetails(id: String) {
        compositeDisposable.add(
                movieInteractor.details(id)
                        .doOnSubscribe { movieLiveData.value = Resource.loading() }
                        .subscribe({
                            movieLiveData.postValue(Resource.success(it))
                        }, {
                            movieLiveData.postValue(Resource.error(it.message!!))
                        })
        )
    }

}