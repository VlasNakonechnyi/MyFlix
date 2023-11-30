package com.example.myflix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.myflix.adapters.MovieAdapter
import com.example.myflix.databinding.FragmentMoviesRecyclerViewBinding
import com.example.myflix.local.Movie
import com.example.myflix.utils.RecyclerViewClickListener
import com.example.myflix.viewmodel.MovieViewModel

class MoviesRecyclerViewFragment : Fragment(), RecyclerViewClickListener {
    private lateinit var adapter: MovieAdapter
    private lateinit var binding: FragmentMoviesRecyclerViewBinding

    private val model: MovieViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Changed from DataBinding to ViewBinding
        binding = FragmentMoviesRecyclerViewBinding.inflate(inflater)
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.animate()
        binding.textViewNothingFound.visibility = View.INVISIBLE

        val recyclerView = binding.recyclerViewMovieList

        // Created the adapter with the clickListener as an argument
        adapter = MovieAdapter(this)
        recyclerView.adapter = adapter

        model.loadRecyclerViewOfMovies(arguments?.getString("title").toString())
        val movieListObserver = Observer<List<Movie>> {
            adapter.movieInfoList = it
        }
        model.currentList.observe(viewLifecycleOwner, movieListObserver)
        return binding.root
    }

    private fun nothingFound() {
        binding.textViewNothingFound.visibility = View.VISIBLE
    }

    override fun recyclerViewItemClicked(v: View, pos: Int) {
        val id = adapter.movieInfoList[pos].imdbID
        id?.let {
            val bundle = Bundle()
            bundle.putString("id", id)
            Navigation.findNavController(v).navigate(R.id.action_moviesRecyclerViewFragment_to_detailsMovieInfoFragment, bundle)
        }


    }



}