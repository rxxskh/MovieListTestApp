package com.rxs.movielisttestapp.data

import com.rxs.movielisttestapp.data.model.MovieListData
import retrofit2.http.GET

interface MovieApi {

    @GET("films.json")
    suspend fun getMovieListData(): MovieListData
}