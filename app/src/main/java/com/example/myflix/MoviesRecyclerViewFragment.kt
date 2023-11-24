package com.example.myflix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.SearchedMovie
import com.example.myflix.adapters.MovieAdapter
import com.example.myflix.databinding.FragmentMoviesRecyclerViewBinding
import com.example.myflix.service.MovieService
import com.example.myflix.service.MovieServiceCallback

class MoviesRecyclerViewFragment : Fragment(), MovieServiceCallback {
    private lateinit var adapter: MovieAdapter
    companion object {
        var title = ""
        fun updateTitle(t: String) {
            title = t
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMoviesRecyclerViewBinding>(
            inflater, R.layout.fragment_movies_recycler_view, container, false
        )

        val recyclerView = binding.recyclerViewMovieList
        adapter = MovieAdapter()

        recyclerView.adapter = adapter
        val service = MovieService(this)
        service.loadData(title)

        return binding.root
    }

    override fun onMoviesLoaded(movies: List<SearchedMovie>) {
        adapter.movieInfoList = movies
    }

}