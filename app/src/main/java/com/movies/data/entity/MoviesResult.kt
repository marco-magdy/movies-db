package com.movies.data.entity

import com.google.gson.annotations.SerializedName

data class MoviesResult(
        @SerializedName("page")
        var page: Int? = null,
        @SerializedName("total_pages")
        val totalPages: Int? = null,
        @SerializedName("results")
        val results: ArrayList<Movie>? = null,
        @SerializedName("total_results")
        val totalResults: Int? = null
)
