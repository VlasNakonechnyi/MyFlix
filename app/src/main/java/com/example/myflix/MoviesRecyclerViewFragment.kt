package com.example.myflix

import android.os.Bundle
import android.util.Log
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
import com.example.myflix.utils.RecyclerViewClickListener

class MoviesRecyclerViewFragment : Fragment(), MovieServiceCallback, RecyclerViewClickListener {
    private lateinit var adapter: MovieAdapter
    private lateinit var service: MovieService

    // the implementation of passing editText text to this fragment
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
        // Created the adapter with the clickListener as an argument
        adapter = MovieAdapter(this)

        recyclerView.adapter = adapter
        service = MovieService(this)
        service.loadRecyclerViewOfMovies(title)

        return binding.root
    }

    override fun onMoviesLoaded(movies: List<SearchedMovie>) {
        adapter.movieInfoList = movies
    }

    override fun recyclerViewItemClicked(v: View, pos: Int) {
        val id = adapter.movieInfoList[pos].imdbID
        id?.let {
            service.loadMovieDetails(it)
            Log.d("BY_ID_LOAD_TEST", "Success")
        }
        Log.d("BY_ID_LOAD_TEST", "FAILED")
    }

}