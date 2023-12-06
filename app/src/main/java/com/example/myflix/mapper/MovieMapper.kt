package com.example.myflix.mapper

import com.example.myflix.dto.MovieDetailsDto
import com.example.myflix.dto.MovieDto
import com.example.myflix.local.Movie
import com.example.myflix.local.MovieDetails

object MovieMapper {
    private fun dtoToLocalMovie(dto: MovieDto): Movie {
        val local = Movie()
        local.title = dto.title
        local.imdbID = dto.imdbID
        local.poster = dto.poster
        local.type = dto.type
        local.year = dto.year
        return local
    }
//    fun dtoToLocalResponse(dto: ResponseDto): MovieResponse{
//        val local = MovieResponse()
//        local.movieList = dto.search?.let { dtoListToLocal(it) }
//        return local
//    }
    fun dtoListToLocal(dto: List<MovieDto>):List<Movie> {
        val local: List<Movie> = mutableListOf()
        for (el in dto) {
            (local as MutableList<Movie>).add(dtoToLocalMovie(el))
        }
        return local
    }
    fun dtoToLocalMovieDetails(dto: MovieDetailsDto): MovieDetails {
        val local = MovieDetails()
        with(local) {
            title = dto.title
            year = dto.year
            genre = dto.genre
            director = dto.director
            runtime = dto.runtime
            plot = dto.plot
            poster = dto.poster
        }
        return local
    }
}