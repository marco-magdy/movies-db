package com.movies.viewmodel

import androidx.appcompat.widget.SearchView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movies.data.entity.Movie
import com.movies.data.entity.Resource
import com.movies.data.entity.Status
import com.movies.data.interactor.DiscoverInteractor
import com.movies.data.interactor.SearchInteractor
import com.movies.widget.RxSearchObservable
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 *Created by marco on 2019-08-10
 */
class MoviesViewModel
@Inject
constructor(
        private var discoverInteractor: DiscoverInteractor,
        private var searchInteractor: SearchInteractor,
        private var compositeDisposable: CompositeDisposable
) : ViewModel() {

    var moviesLiveData = MutableLiveData<Resource<ArrayList<Movie>>>()
    var searchResultLiveData = MutableLiveData<Resource<ArrayList<Movie>>>()
    var pageIndex = 1

    fun movies() {
        compositeDisposable.add(
                discoverInteractor.discover(pageIndex)
                        .doOnSubscribe { moviesLiveData.postValue(Resource.loading()) }
                        .subscribe({
                            moviesLiveData.value = Resource.success(it.results)
                            pageIndex++
                        }, {
                            moviesLiveData.value = Resource.error(it.message!!)
                        })
        )
    }

    fun search(searchView: SearchView) {
        compositeDisposable.add(
                RxSearchObservable.fromView(searchView)
                        .debounce(300, TimeUnit.MILLISECONDS)
                        .filter { return@filter !it.isEmpty() }
                        .distinctUntilChanged()
                        .switchMap { searchInteractor.search(it) }
                        .subscribe({
                            searchResultLiveData.postValue(Resource(Status.SUCCESS, it.results))
                        }, {
                            moviesLiveData.postValue(Resource(Status.ERROR, null, it.message))
                        }))
    }
}