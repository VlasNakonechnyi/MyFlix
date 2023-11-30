package com.example.myflix

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myflix.databinding.FragmentDetailsMovieInfoBinding
import com.example.myflix.local.Movie
import com.example.myflix.local.MovieDetails
import com.squareup.picasso.Picasso

class DetailsMovieInfoFragment : Fragment() {
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

        return binding.root
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