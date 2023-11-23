package com.example.myflix.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.SearchedMovie
import com.example.myflix.R
import com.example.myflix.databinding.MovieInfoBinding
import com.example.myflix.service.MovieService
import com.squareup.picasso.Picasso

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var movieInfoList: List<SearchedMovie> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    inner class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = MovieInfoBinding.bind(itemView)
        val imageViewMoviePoster = binding.imageViewMoviePoster
        val textViewTitle = binding.textViewMovieTitle
        val textViewYear = binding.textViewMovieYear

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_info, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount() = movieInfoList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
            val movie = movieInfoList[position]
            with(holder) {
                with(movie) {
                    textViewTitle.text = title
                    textViewYear.text = year
                    Picasso.get().load(poster).into(imageViewMoviePoster)

                }
            }
        }
}