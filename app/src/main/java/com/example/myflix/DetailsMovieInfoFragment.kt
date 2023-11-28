package com.example.myflix

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.SearchedMovie
import com.example.myflix.databinding.FragmentDetailsMovieInfoBinding
import com.example.myflix.pojo.SearchedMovieDetails
import com.example.myflix.service.MovieService
import com.example.myflix.service.MovieServiceCallback
import com.squareup.picasso.Picasso

class DetailsMovieInfoFragment : Fragment(), MovieServiceCallback {
    private lateinit var binding: FragmentDetailsMovieInfoBinding
    private var title: String = ""
    private var year: String = ""
    private var length: String = ""
    private var genre: String = ""
    private var director: String = ""
    private var posterUrl: String = ""
    private var plot: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsMovieInfoBinding.inflate(inflater)
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.animate()
        val service = MovieService(this)
        service.loadMovieDetails(arguments?.getString("id").toString())

        return binding.root
    }

    override fun onMoviesLoaded(
        movies: List<SearchedMovie>,
        searchedMovieDetails: SearchedMovieDetails?
    ) {
        Log.d("SECOND_CALLBACK", "SUCCESS")
        title = searchedMovieDetails?.title.toString()
        year = searchedMovieDetails?.year.toString()
        length = searchedMovieDetails?.runtime.toString()
        director = searchedMovieDetails?.director.toString()
        posterUrl = searchedMovieDetails?.poster.toString()
        genre = searchedMovieDetails?.genre.toString()
        plot = searchedMovieDetails?.plot.toString()
        Picasso.get().load(posterUrl).into(binding.imageViewDetailPoster)

        initFields()
        binding.progressBar.visibility = View.INVISIBLE

    }
    private fun initFields() {
        binding.textViewDetailMovieTitle.text = title
        binding.textViewDetailGenre.text = genre
        binding.textViewDetailYear.text = year
        binding.textViewDetailDirector.text = director
        binding.textView5.text = length
        binding.textViewDetailPlot.text = plot
    }

}