package com.movies.widget

import androidx.appcompat.widget.SearchView

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Created by marco on 2019-08-15
 */
object RxSearchObservable {

    fun fromView(searchView: SearchView): Observable<String> {

        val subject = PublishSubject.create<String>()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                subject.onComplete()
                return true
            }

            override fun onQueryTextChange(text: String): Boolean {
                subject.onNext(text)
                return true
            }
        })
        return subject
    }
}