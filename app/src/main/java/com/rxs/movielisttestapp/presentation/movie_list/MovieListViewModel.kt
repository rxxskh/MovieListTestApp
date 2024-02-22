package com.rxs.movielisttestapp.presentation.movie_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rxs.movielisttestapp.domain.common.Resource
import com.rxs.movielisttestapp.domain.model.Movie
import com.rxs.movielisttestapp.domain.usecase.GetMoviesUseCase
import com.rxs.movielisttestapp.presentation.movie_list.adapter.ListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getMoviesUseCase: com.rxs.movielisttestapp.domain.usecase.GetMoviesUseCase
) : ViewModel() {

    private var genres: List<String> = emptyList()
    private var movies: List<com.rxs.movielisttestapp.domain.model.Movie> = emptyList()
    private var selectedGenre: String = ""
    var errorMessage: String = ""


    private val _listItems = MutableLiveData<List<ListItem>>()
    val listItems: LiveData<List<ListItem>> = _listItems

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading


    init {
        loadData()
    }

    fun selectGenre(input: String) {
        selectedGenre = if (input == selectedGenre) "" else input
        setMovieListItems()
    }

    fun loadData() {
        _isLoading.value = true
        errorMessage = ""
        getMoviesUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    movies = result.data ?: emptyList()
                    genres = getGenres()
                    setMovieListItems()
                    _isLoading.value = false
                }

                is Resource.Error -> {
                    errorMessage =
                        result.message ?: "Неизвестная ошибка"
                    _isLoading.value = false
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getGenres(): List<String> {
        return movies.flatMap { it.genres }
            .distinct()
            .sorted()
            .map { it.replaceFirstChar { first -> first.uppercase() } }
    }

    private fun setMovieListItems() {
        val listItemsUpdate = mutableListOf<ListItem>()
        listItemsUpdate.add(ListItem.TitleItem(name = "Жанры"))
        listItemsUpdate.addAll(genres.map {
            ListItem.GenreItem(
                name = it,
                selected = it == selectedGenre
            )
        })
        listItemsUpdate.add(ListItem.TitleItem(name = "Фильмы", margins = listOf(0, 16, 0, 8)))
        listItemsUpdate.addAll(movies.filter {
            selectedGenre.isEmpty() || it.genres.map { genre -> genre.lowercase() }
                .contains(selectedGenre.lowercase())
        }.chunked(2) { chunk ->
            val movies = chunk.toList()
            ListItem.MovieRowItem(movies = movies)
        })
        _listItems.value = listItemsUpdate
    }
}
