package com.example.myflix

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.myflix.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentMoviesBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movies, container, false
        )
        binding.buttonSearch.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_moviesFragment3_to_moviesRecyclerViewFragment)
        }
        return binding.root
    }




}