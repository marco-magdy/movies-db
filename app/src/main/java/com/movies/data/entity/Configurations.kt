package com.movies.data.entity

import com.google.gson.annotations.SerializedName

data class Configurations(
        @SerializedName("images")
        val images: Images? = null,
        @SerializedName("change_keys")
        val changeKeys: List<String?>? = null
)