package com.movies.helper

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.google.gson.Gson
import com.movies.Constants.API.Preferences.Companion.SHARED_PREFERENCE_DEFAULT_NAME
import javax.inject.Inject

class PreferencesHelper
@Inject
constructor(context: Context) {

    private val sharedPreferences: SharedPreferences
    private val gson: Gson
    private val editor: SharedPreferences.Editor

    init {
        val name = SHARED_PREFERENCE_DEFAULT_NAME
        gson = Gson()
        sharedPreferences = context.getSharedPreferences(name, MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    fun remove(key: String) = editor.remove(key).commit()

    fun putObject(key: String, `object`: Any) {
        with(editor) {
            putString(key, gson.toJson(`object`))
            commit()
        }
    }

    fun <T> getObject(key: String, a: Class<T>): T? {
        val json = sharedPreferences.getString(key, null)
        return if (json == null) {
            null
        } else {
            try {
                gson.fromJson(json, a)
            } catch (e: Exception) {
                throw IllegalArgumentException("Object stored with key "
                        + key + " is instance of other class")
            }

        }
    }
}
