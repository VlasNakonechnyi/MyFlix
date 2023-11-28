package com.example.myflix

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myflix.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMoviesBinding.inflate(inflater)
        Log.d("SETUP_MOVIES_FRAGMENT", "MOVIES Fragment")

        binding.buttonSearch.setOnClickListener {
            Log.d("CLICK_EVENT", "Search button clicked")
            val bundle = Bundle()
            bundle.putString("editTextText", binding.editTextMovieTitle.text.toString())
            Navigation.findNavController(it).navigate(R.id.action_moviesFragment3_to_moviesRecyclerViewFragment, bundle)
        }
        return binding.root
    }




}