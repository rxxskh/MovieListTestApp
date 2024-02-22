package com.rxs.movielisttestapp.presentation.movie_list.adapter

import com.rxs.movielisttestapp.domain.model.Movie

sealed class ListItem(val viewType: Int) {
    class TitleItem(val name: String, val margins: List<Int> = listOf(0, 0, 0, 0)) :
        ListItem(viewType = ViewType.TITLE_VIEW)

    class GenreItem(val name: String, val selected: Boolean) :
        ListItem(viewType = ViewType.GENRE_VIEW)

    class MovieRowItem(val movies: List<com.rxs.movielisttestapp.domain.model.Movie>) :
        ListItem(viewType = ViewType.MOVIE_VIEW)
}