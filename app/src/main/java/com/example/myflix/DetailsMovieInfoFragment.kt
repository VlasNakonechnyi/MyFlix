package com.example.myflix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.myflix.databinding.FragmentDetailsMovieInfoBinding
import com.example.myflix.local.MovieDetails
import com.example.myflix.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso

class DetailsMovieInfoFragment : Fragment() {
    private val model: MovieViewModel by viewModels()
    private lateinit var binding: FragmentDetailsMovieInfoBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsMovieInfoBinding.inflate(inflater)
        binding.progressBar.visibility = View.VISIBLE
        binding.progressBar.animate()
        model.loadMovieDetails(arguments?.getString("id").toString())
        val movieDetailsObserver = Observer<MovieDetails> {
            initFields(it)
            binding.progressBar.visibility = View.INVISIBLE

        }
        model.currentMovieDetails.observe(viewLifecycleOwner,movieDetailsObserver)
        return binding.root
    }


    private fun initFields(md: MovieDetails) {
        with(md) {
            with (binding) {
                textViewDetailMovieTitle.text = title
                textViewDetailGenre.text = genre
                textViewDetailYear.text = year
                textViewDetailDirector.text = director
                textView5.text = runtime
                textViewDetailPlot.text = plot
                Picasso.get().load(poster).into(imageViewDetailPoster)
            }
        }
    }



}