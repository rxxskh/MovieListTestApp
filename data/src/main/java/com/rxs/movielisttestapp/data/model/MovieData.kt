package com.rxs.movielisttestapp.data.model

import com.rxs.movielisttestapp.domain.model.Movie

data class MovieData(
    val description: String?,
    val genres: List<String>?,
    val id: Int?,
    val image_url: String?,
    val localized_name: String?,
    val name: String?,
    val rating: Double?,
    val year: Int?
)

fun MovieData.toMovie() = Movie(
    description = description ?: "",
    genres = genres ?: emptyList(),
    id = id ?: -1,
    imageUrl = image_url ?: "",
    localizedName = localized_name ?: "",
    name = name ?: "",
    rating = rating ?: 0.0,
    year = year ?: 1900
)