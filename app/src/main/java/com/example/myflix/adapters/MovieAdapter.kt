package com.example.myflix.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myflix.databinding.MovieInfoBinding
import com.example.myflix.local.Movie
import com.example.myflix.utils.DiffUtilCallback
import com.example.myflix.utils.RecyclerViewClickListener
import com.squareup.picasso.Picasso

class MovieAdapter(clickListener: RecyclerViewClickListener): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var movieInfoList: List<Movie> = mutableListOf()
        set(value) {
            val diffUtilCallback = DiffUtilCallback(movieInfoList, value)
            val diff = DiffUtil.calculateDiff(diffUtilCallback)
            (movieInfoList as MutableList<Movie>).clear()
            (movieInfoList as MutableList<Movie>).addAll(value)
            diff.dispatchUpdatesTo(this)
        }

    val itemListener = clickListener

    inner class MovieViewHolder(binding: MovieInfoBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }
        val imageViewMoviePoster = binding.imageViewMoviePoster
        val textViewTitle = binding.textViewMovieTitle
        val textViewYear = binding.textViewMovieYear
        override fun onClick(v: View) {
            Log.d("ON_CLICK", "WORKED")
            v.let {
                itemListener.recyclerViewItemClicked(it, layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = MovieInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
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