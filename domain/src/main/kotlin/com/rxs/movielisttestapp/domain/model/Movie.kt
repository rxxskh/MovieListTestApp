package com.rxs.movielisttestapp.domain.model

data class Movie(
    val description: String,
    val genres: List<String>,
    val id: Int,
    val imageUrl: String,
    val localizedName: String,
    val name: String,
    val rating: Double,
    val year: Int
)