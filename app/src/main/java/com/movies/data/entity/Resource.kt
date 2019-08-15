package com.movies.data.entity


/**
 *Created by marco on 3/25/19
 */

data class Resource<out T>
constructor(val status: Status, val data: T? = null, val message: String? = "") {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, null)
        }
    }
}