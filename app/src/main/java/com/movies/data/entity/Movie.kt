package com.movies.data.entity

import com.google.gson.annotations.SerializedName

/**
 *Created by marco on 2019-08-09
 */
data class Movie(
    @SerializedName("overview")
    val overview: String? = null,
    @SerializedName("original_language")
    val originalLanguage: String? = null,
    @SerializedName("original_title")
    val originalTitle: String? = null,
    @SerializedName("video")
    val video: Boolean? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,
    @SerializedName("poster_path")
    val posterPath: String? = null,
    @SerializedName("backdrop_path")
    val backdropPath: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("vote_average")
    val voteAverage: Double? = null,
    @SerializedName("popularity")
    val popularity: Double? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("adult")
    val adult: Boolean? = null,
    @SerializedName("vote_count")
    val voteCount: Int? = null,
    var isLoadingItem: Boolean = false
)