package com.example.myflix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.SearchedMovie
import com.example.myflix.adapters.MovieAdapter
import com.example.myflix.databinding.FragmentMoviesRecyclerViewBinding
import com.example.myflix.pojo.SearchedMovieDetails
import com.example.myflix.service.MovieService
import com.example.myflix.service.MovieServiceCallback
import com.example.myflix.utils.RecyclerViewClickListener

class MoviesRecyclerViewFragment : Fragment(), MovieServiceCallback, RecyclerViewClickListener {

    private lateinit var adapter: MovieAdapter
    private lateinit var service: MovieService
    private lateinit var binding: FragmentMoviesRecyclerViewBinding

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
        service = MovieService(this)


        // using bundle with the nav graph to pass arguments from the search bar to this fragment
        service.loadRecyclerViewOfMovies(arguments?.getString("editTextText").toString())


        return binding.root
    }

    // The callback function that provides the recycler view with the list of movies upon loading
    override fun onMoviesLoaded(movies: List<SearchedMovie>, searchedMovieDetails: SearchedMovieDetails?) {
        adapter.movieInfoList = movies
        binding.progressBar.visibility = View.INVISIBLE
        if (movies.isEmpty()) {
            nothingFound()
        }
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