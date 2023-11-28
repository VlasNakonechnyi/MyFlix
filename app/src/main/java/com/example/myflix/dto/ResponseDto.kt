package com.example.myflix.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseDto (
    @SerializedName("Search")
    @Expose
    var search: List<MovieDto>? = null,

    @SerializedName("totalResults")
    @Expose
    var totalResults: String? = null,

    @SerializedName("Response")
    @Expose
    var response: String? = null
)