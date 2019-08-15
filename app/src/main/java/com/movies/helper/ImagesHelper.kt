package com.movies.helper

import com.movies.data.interactor.repos.AppConfigRepository
import javax.inject.Inject

/**
 *Created by marco on 2019-08-09
 */
class ImagesHelper
@Inject
constructor(appConfigRepo: AppConfigRepository) {
    private val appConfig = appConfigRepo.get()

    fun compatLogo(image: String): String {
        return appConfig?.images?.baseUrl + appConfig?.images?.logoSizes?.first() + image
    }

    fun logo(image: String): String {
        return appConfig?.images?.baseUrl + appConfig?.images?.logoSizes?.middle() + image
    }

    fun poster(image: String): String {
        return (appConfig?.images?.baseUrl + appConfig?.images?.posterSizes?.middle() + image)
    }
}

private fun <E> List<E>?.middle(): E {
    this?.let {
        return this[it.size / 2]
    }
    return this!![0]
}
