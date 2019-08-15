package com.movies.network

import com.movies.Constants
import com.movies.data.entity.Configurations
import com.movies.data.entity.Movie
import com.movies.data.entity.MoviesResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *Created by marco on 2019-08-09
 */
class API {
    interface Configuration {
        @GET(Constants.API.Configuration.CONFIG)
        fun config(): Observable<Configurations>
    }

    interface Movies {
        @GET(Constants.API.Movies.DISCOVER)
        fun discover(@Query("page") page: Int): Observable<MoviesResult>

        @GET(Constants.API.Movies.MOVIE_DETAILS)
        fun movieDetails(@Path("id") id: String): Observable<Movie>

        @GET(Constants.API.Movies.SEARCH)
        fun search(@Query("query") query: String): Observable<MoviesResult>
    }
}