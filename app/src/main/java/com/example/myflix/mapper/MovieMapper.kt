package com.example.myflix.mapper

import com.example.myflix.dto.MovieDto
import com.example.myflix.dto.ResponseDto
import com.example.myflix.local.Movie
import com.example.myflix.local.MovieResponse

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
    fun dtoToLocalResponse(dto: ResponseDto): MovieResponse{
        val local = MovieResponse()
        local.movieList = dto.search?.let { dtoListToLocal(it) }
        return local
    }
    private fun dtoListToLocal(dto: List<MovieDto>):List<Movie> {
        val local: List<Movie> = mutableListOf()
        for (el in dto) {
            (local as MutableList<Movie>).add(dtoToLocalMovie(el))
        }
        return local
    }

}