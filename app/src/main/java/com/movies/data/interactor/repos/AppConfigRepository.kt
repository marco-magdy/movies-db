package com.movies.data.interactor.repos

import com.movies.Constants.API.Preferences.Companion.KEY_APP_CONFIG
import com.movies.data.entity.Configurations
import com.movies.helper.PreferencesHelper
import javax.inject.Inject

/**
 *Created by marco on 2019-08-09
 */
class AppConfigRepository
@Inject
constructor(private val preferencesHelper: PreferencesHelper) : Repository<Configurations> {

    override fun save(obj: Configurations) = preferencesHelper.putObject(KEY_APP_CONFIG, obj)

    override fun get(): Configurations? = preferencesHelper.getObject(KEY_APP_CONFIG, Configurations::class.java)

    override fun remove() = preferencesHelper.remove(KEY_APP_CONFIG)
}