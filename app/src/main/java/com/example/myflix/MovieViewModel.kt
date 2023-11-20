package com.example.myflix

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.myflix.service.MovieService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MovieViewModel(){


    init {
        loadData()
    }

    private fun loadData() = runBlocking {
        val service = MovieService()
        coroutineScope {
            launch(Dispatchers.IO) {
                delay(10_000)
                println("[${Thread.currentThread().name}] One")
                try {
                    service.searchMovies("superman")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            launch(Dispatchers.IO) {
                println("[${Thread.currentThread().name}] Two")
                try {
                    service.searchMovies("superman")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
        println("[${Thread.currentThread().name}] Done!")
    }

}