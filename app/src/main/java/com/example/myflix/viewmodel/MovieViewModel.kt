package com.example.myflix.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myflix.local.Movie
import com.example.myflix.local.MovieDetails
import com.example.myflix.mapper.MovieMapper
import com.example.myflix.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MovieViewModel: ViewModel(){
    private val movieRepository= MovieRepository()
    val currentList: MutableLiveData<List<Movie>> by lazy {
        MutableLiveData<List<Movie>>()
    }
    val currentMovieDetails: MutableLiveData<MovieDetails> by lazy {
        MutableLiveData<MovieDetails>()
    }
    fun loadRecyclerViewOfMovies(title: String)  {

            CoroutineScope(Dispatchers.Main).launch {
                with(movieRepository) {
                    searchMovies(title)
                    currentList.value = MovieMapper.dtoListToLocal(movies)
                }
            }

    }
    fun loadMovieDetails(id: String) {

           CoroutineScope(Dispatchers.Main).launch {
               with(movieRepository) {
                   getMovieDetailsById(id)
                   currentMovieDetails.value = MovieMapper.dtoToLocalMovieDetails(movieDetails)
               }

           }

    }
}

