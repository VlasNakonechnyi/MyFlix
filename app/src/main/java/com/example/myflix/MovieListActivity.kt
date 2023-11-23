package com.example.myflix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.SearchedMovie
import com.example.myflix.adapters.MovieAdapter
import com.example.myflix.service.MovieService
import com.example.myflix.service.MovieServiceCallback

class MovieListActivity : ComponentActivity(), MovieServiceCallback {

    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.movie_list)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewMovieList)
        adapter = MovieAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val movieService = MovieService(this)
        val thisIntent = intent
        val title = thisIntent.getStringExtra("title")

            movieService.loadData(title!!)


    }

    override fun onMoviesLoaded(movies: List<SearchedMovie>) {
        adapter.movieInfoList = movies
    }

}