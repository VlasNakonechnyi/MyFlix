package com.example.myflix.service

import android.util.Log
import com.example.SearchedMovie
import com.example.myflix.api.MovieApi
import com.example.myflix.api.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import java.io.IOException
interface MovieServiceCallback {
    fun onMoviesLoaded(movies: List<SearchedMovie>)
}
class MovieService(private val callback: MovieServiceCallback) {
    var movies: List<SearchedMovie>? = null
    private val retrofit = RetrofitClient.getClient()
    private val movieApi = retrofit.create(MovieApi::class.java)


    private suspend fun searchMovies(searchTitle: String) {
        try {
            val response = movieApi.getMoviesBySearch(searchTitle = searchTitle)
            Log.d("API_RESPONSE", response.toString())
            if (response.isSuccessful) {
                movies = response.body()?.search
                movies?.let {
                    // Handle the list of movies
                    callback.onMoviesLoaded(it)
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
    fun loadData(title: String) = runBlocking {

        coroutineScope {

            launch(Dispatchers.IO) {
                    println("[${Thread.currentThread().name}] One")
                    try {
                        searchMovies(title)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
            }
            launch(Dispatchers.IO) {
                println("[${Thread.currentThread().name}] Two")
                try {
                    searchMovies(title)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        }
        println("[${Thread.currentThread().name}] Done!")
    }
}

