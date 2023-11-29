package com.example.myflix.repository

import android.util.Log
import com.example.myflix.api.MovieApi
import com.example.myflix.api.RetrofitClient
import com.example.myflix.dto.MovieDetailsDto
import com.example.myflix.dto.MovieDto


object MovieRepository {
    var movies: List<MovieDto> = listOf()
    var movieDetails: MovieDetailsDto = MovieDetailsDto()
    private val retrofit = RetrofitClient.getClient()
    private val movieApi = retrofit.create(MovieApi::class.java)


    suspend fun searchMovies(searchTitle: String) {
        try {
            val response = movieApi.getMoviesBySearch(searchTitle = searchTitle)
            val moviesDtoList = response.body()?.search
            if (response.isSuccessful) {
                moviesDtoList?.let {
                    movies = it
                }
            }
        } catch (e: Exception) {
            Log.d("REPOSITORY_EXCEPTION", e.message.toString())
        }
    }
    suspend fun getMovieDetailsById(id: String) {
        try {
        val response = movieApi.getMoviesById(id = id)
        val movieDtoDetails = response.body()
        if (response.isSuccessful) {
            movieDtoDetails?.let {
                movieDetails = it
            }
        }
        }catch (e: Exception) {
                Log.d("REPOSITORY_EXCEPTION", e.message.toString())
        }

    }


}