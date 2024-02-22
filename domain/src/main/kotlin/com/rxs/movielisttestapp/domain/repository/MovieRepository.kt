package com.rxs.movielisttestapp.domain.repository

import com.rxs.movielisttestapp.domain.model.Movie


interface MovieRepository {

    suspend fun getMovies(): List<Movie>
}