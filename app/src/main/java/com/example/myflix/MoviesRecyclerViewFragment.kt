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
import com.example.myflix.service.MovieServiceCallback

class MoviesRecyclerViewFragment : Fragment(), MovieServiceCallback {
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentMoviesRecyclerViewBinding>(
            inflater, R.layout.fragment_movies_recycler_view, container, false
        )

        return binding.root
    }

    override fun onMoviesLoaded(movies: List<SearchedMovie>) {
        TODO("Not yet implemented")
    }

}