package com.example.myflix.service

import android.util.Log
import com.example.myflix.api.MovieApi
import com.example.myflix.api.RetrofitClient
import retrofit2.HttpException
import java.io.IOException

class MovieService {
    private val retrofit = RetrofitClient.getClient()
    private val movieApi = retrofit.create(MovieApi::class.java)
    suspend fun searchMovies(searchTitle: String) {
        try {
            val response = movieApi.getMoviesBySearch(searchTitle = searchTitle)
            Log.d("API_RESPONSE", response.toString())
            if (response.isSuccessful) {
                val movies = response.body()
                movies?.let {
                    // Handle the list of movies
                    Log.d("MOVIE_SERVICE", it.toString())
                } ?: Log.d("MOVIE_SERVICE", "Response body is null")

            } else {
                // Handle unsuccessful response
                Log.e("MOVIE_SERVICE", "Error: ${response.code()}, ${response.message()}")
            }

        } catch (e: HttpException) {
            // Handle HTTP exceptions (e.g., non-2xx status code)
            Log.e("MOVIE_SERVICE", "HTTP Exception: ${e.code()}, ${e.message()}")

        } catch (e: IOException) {
            // Handle network issues or general I/O errors
            Log.e("MOVIE_SERVICE", "IOException: ${e.message}")
        }
    }
}

