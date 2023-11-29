package com.example.myflix.service

import android.util.Log
import com.example.myflix.api.MovieApi
import com.example.myflix.api.RetrofitClient
import com.example.myflix.dto.MovieDetailsDto
import com.example.myflix.local.Movie
import com.example.myflix.local.MovieDetails
import com.example.myflix.mapper.MovieMapper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

interface MovieServiceCallback {
    fun onMoviesLoaded(movies: List<Movie>? = listOf(), movieDetails: MovieDetails? = null)
}
class MovieService(private val callback: MovieServiceCallback) {

    private var movies: List<Movie>? = null
    private var movieDetails: MovieDetailsDto? = null
    private val retrofit = RetrofitClient.getClient()
    private val movieApi = retrofit.create(MovieApi::class.java)


    private suspend fun searchMovies(searchTitle: String) {
        try {

            val response = movieApi.getMoviesBySearch(searchTitle = searchTitle)
            val moviesDto = response.body()
            Log.d("API_RESPONSE", response.toString())
            if (response.isSuccessful) {
                movies = moviesDto?.let { MovieMapper.dtoToLocalResponse(it).movieList }
                if (movies != null) {

                    callback.onMoviesLoaded(movies = movies)
                } else {
                    callback.onMoviesLoaded(movies = listOf())
                }


            } else {
                // Handle unsuccessful response
                Log.e("MOVIE_SERVICE", "Error: ${response.code()}, ${response.message()}")
                callback.onMoviesLoaded(movies = listOf())
            }

        } catch (e: HttpException) {
            // Handle HTTP exceptions (e.g., non-2xx status code)
            Log.e("MOVIE_SERVICE", "HTTP Exception: ${e.code()}, ${e.message()}")
            callback.onMoviesLoaded(movies = listOf())

        } catch (e: IOException) {
            // Handle network issues or general I/O errors
            Log.e("MOVIE_SERVICE", "IOException: ${e.message}")
            callback.onMoviesLoaded(movies = listOf())

        }
    }
    private suspend fun getMovieDetailsById(id: String) {
        try {
            val response = movieApi.getMoviesById(id = id)
            Log.d("API_RESPONSE_MOVIES_BY_ID", response.toString())
            if (response.isSuccessful) {
                movieDetails = response.body()
                movieDetails?.let {
                    callback.onMoviesLoaded(movieDetails = MovieMapper.dtoToLocalMovieDetails(it))

                    Log.d("MOVIE_SERVICE_BY_ID", it.toString())
                } ?: Log.d("MOVIE_SERVICE_BY_ID", "Response body is null")

            } else {
                // Handle unsuccessful response
                Log.e("MOVIE_SERVICE_BY_ID", "Error: ${response.code()}, ${response.message()}")
            }

        } catch (e: HttpException) {
            // Handle HTTP exceptions (e.g., non-2xx status code)
            Log.e("MOVIE_SERVICE_BY_ID", "HTTP Exception: ${e.code()}, ${e.message()}")

        } catch (e: IOException) {
            // Handle network issues or general I/O errors
            Log.e("MOVIE_SERVICE_BY_ID", "IOException: ${e.message}")
        }
    }
    fun loadRecyclerViewOfMovies(title: String)  {
            CoroutineScope(Dispatchers.Main).launch {
                println("[${Thread.currentThread().name}] One")
                searchMovies(title)
            }
        println("[${Thread.currentThread().name}] Done!")
    }
    fun loadMovieDetails(id: String) {

           CoroutineScope(Dispatchers.Main).launch {
                println("[${Thread.currentThread().name}] One")
                getMovieDetailsById(id)
            }

        println("[${Thread.currentThread().name}] Done!")
    }
}

