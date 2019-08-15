package com.movies.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.movies.R
import com.movies.data.entity.Status
import com.movies.di.ViewModelFactory
import com.movies.viewmodel.SplashViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 *Created by marco on 2019-08-09
 */
class SplashActivity : BaseActivity() {

    @field:[Inject]
    lateinit var viewModelFactory: ViewModelFactory<SplashViewModel>
    private lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        AndroidInjection.inject(this)
        initViewModel()
        initObservables()
        viewModel.checkAppConfig()
    }

    override fun initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SplashViewModel::class.java)
    }

    override fun initObservables() {
        viewModel.appConfigLiveData
                .observe(this, Observer {
                    when (it.status) {
                        Status.SUCCESS -> {
                            MoviesActivity.start(this)
                            finishAffinity()
                        }
                        else -> showMessage(it.message ?: getString(R.string.error))
                    }
                })
    }
}