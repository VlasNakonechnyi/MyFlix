package com.example.myflix.api

import com.example.ListOfSearchedMovies
import com.example.SearchedMovie
import com.example.myflix.pojo.SearchedMovieDetails
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun getMoviesBySearch(
        @Query("apikey") apikey: String = API_KEY,
        @Query("s") searchTitle: String = ""
    ): Response<ListOfSearchedMovies>

    @GET("/")
    suspend fun getMoviesById(
        @Query("apikey") apikey: String = API_KEY,
        @Query("i") id: String = ""
    ): Response<SearchedMovieDetails>

    companion object {
        private const val API_KEY = "c7d7f800"
    }

}