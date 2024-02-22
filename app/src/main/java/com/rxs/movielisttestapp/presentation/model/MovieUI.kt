package com.rxs.movielisttestapp.presentation.model

import android.os.Parcelable
import com.rxs.movielisttestapp.domain.model.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUI(
    val description: String,
    val genres: List<String>,
    val id: Int,
    val imageUrl: String,
    val localizedName: String,
    val name: String,
    val rating: Double,
    val year: Int
) : Parcelable

fun Movie.toMovieUI() = MovieUI(
    description = description,
    genres = genres,
    id = id,
    imageUrl = imageUrl,
    localizedName = localizedName,
    name = name,
    rating = rating,
    year = year
)