package com.example.myflix

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.example.myflix.adapters.MovieAdapter
import com.example.myflix.databinding.ActivityMainBinding
import com.example.myflix.service.MovieService

class MainActivity : ComponentActivity() {
    private lateinit var searchButton: Button
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchButton = binding.buttonSearch
        searchEditText = binding.editTextMovieTitle


        searchButton.setOnClickListener {
            val movieListIntent = Intent(this, MovieListActivity::class.java)
            var title = searchEditText.text.toString()
            title = title.trim()
            if (title.isBlank()) {
                actionWrongInput(searchEditText)
                return@setOnClickListener
            }
            Log.d(("ENTERED_TEXT"), title)
            movieListIntent.putExtra("title", title)
            startActivity(movieListIntent)
        }

    }
    private fun actionWrongInput(editText: EditText) {
        editText.error = "title cannot be blank"
    }



}
