package com.example.myflix.api

import com.example.myflix.dto.ResponseDto
import com.example.myflix.dto.MovieDetailsDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun getMoviesBySearch(
        @Query("apikey") apikey: String = API_KEY,
        @Query("s") searchTitle: String = ""
    ): Response<ResponseDto>

    @GET("/")
    suspend fun getMoviesById(
        @Query("apikey") apikey: String = API_KEY,
        @Query("i") id: String = ""
    ): Response<MovieDetailsDto>

    companion object {
        private const val API_KEY = "c7d7f800"
    }

}