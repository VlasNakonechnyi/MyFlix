package com.example

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ListOfSearchedMovies (
    @SerializedName("Search")
    @Expose
    var search: List<SearchedMovie>? = null,

    @SerializedName("totalResults")
    @Expose
    var totalResults: String? = null,

    @SerializedName("Response")
    @Expose
    var response: String? = null
)