package com.rxs.movielisttestapp.data.repository

import com.rxs.movielisttestapp.data.MovieApi
import com.rxs.movielisttestapp.data.model.toMovie
import com.rxs.movielisttestapp.domain.model.Movie
import com.rxs.movielisttestapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {

    override suspend fun getMovies(): List<Movie> {
        return movieApi.getMovieListData().films.map { it.toMovie() }.sortedBy { it.localizedName }
    }
}