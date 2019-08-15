package com.movies.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Lazy
import javax.inject.Inject

/**
 *Created by marco on 2019-08-09
 */
class ViewModelFactory<VM : ViewModel>
@Inject
constructor(
    private val viewModel: Lazy<VM>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return viewModel.get() as T
    }
}