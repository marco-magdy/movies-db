package com.movies.data.interactor.repos

/**
 *Created by marco on 3/30/19
 */
interface Repository<T> {

    fun save(obj: T)

    fun get(): T?

    fun remove(): Boolean
}