package com.example.myflix

import android.os.Binder
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import com.example.myflix.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private lateinit var searchButton: Button
    private lateinit var searchEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        searchButton = binding.buttonSearch
        searchEditText = binding.editTextMovieTitle
    }


}
