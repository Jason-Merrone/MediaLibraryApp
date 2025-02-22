package com.example.medialibrary.viewmodels.movieviewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medialibrary.models.MoviePage
import com.example.medialibrary.repositories.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesScreenViewModel: ViewModel() {
    private val _movies = MutableStateFlow(emptyList<MoviePage>())
    val movies: StateFlow<List<MoviePage>> = _movies

    init {
        viewModelScope.launch {
            MoviesRepository.movies.collect {
                _movies.value = it
            }
        }
    }
}