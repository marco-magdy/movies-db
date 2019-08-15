package com.movies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.movies.data.entity.Configurations
import com.movies.data.entity.Resource
import com.movies.data.entity.Status
import com.movies.data.interactor.AppConfigInteractor
import com.movies.data.interactor.repos.AppConfigRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 *Created by marco on 2019-08-09
 */
class SplashViewModel
@Inject
constructor(
        private val appConfigInteractor: AppConfigInteractor,
        private val appConfigRepository: AppConfigRepository,
        private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    val appConfigLiveData = MutableLiveData<Resource<Configurations>>()

    fun checkAppConfig() {
        if (appConfigRepository.get() != null) {
            compositeDisposable.add(
                    Observable
                            .timer(3, TimeUnit.SECONDS)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe { appConfigLiveData.value = Resource(Status.SUCCESS) }
            )
        } else {
            compositeDisposable.add(
                    appConfigInteractor.config()
                            .subscribe({
                                appConfigRepository.save(it)
                                appConfigLiveData.value = Resource(Status.SUCCESS)
                            }, {
                                appConfigLiveData.value = Resource.error(it.message!!)
                            })
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}