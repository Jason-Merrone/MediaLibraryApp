package com.example.medialibrary.viewmodels.movieviewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibrary.MediaLibraryApplication
import com.example.medialibrary.repositories.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieModificationScreenViewModel(
    private val movieId: Int?,
    private val moviesRepository: MoviesRepository
): ViewModel() {
    private val _title = MutableStateFlow("")
    private val _genre = MutableStateFlow("")
    private val _rating = MutableStateFlow("")
    private val _runtime = MutableStateFlow(0)
    private val _format = MutableStateFlow("")
    private val _notes = MutableStateFlow("")

    val title: StateFlow<String> = _title
    val genre: StateFlow<String> = _genre
    val rating: StateFlow<String> = _rating
    val runtime: StateFlow<Int> = _runtime
    val format: StateFlow<String> = _format
    val notes: StateFlow<String> = _notes

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setGenre(genre: String) {
        _genre.value = genre
    }

    fun setRating(rating: String) {
        _rating.value = rating
    }

    fun setRuntime(runtime: Int) {
        _runtime.value = runtime
    }

    fun setFormat(format: String) {
        _format.value = format
    }

    fun setNotes(notes: String) {
        _notes.value = notes
    }

    fun saveMovie() {
        if (movieId != null) {
            moviesRepository.updatePage(
                movieId,
                _title.value,
                _runtime.value,
                _genre.value,
                _rating.value,
                _format.value,
                _notes.value
            )
        } else {
            moviesRepository.addPage(
                _title.value,
                _runtime.value,
                _genre.value,
                _rating.value,
                _format.value,
                _notes.value
            )
        }
    }

    init {
        if (movieId != null) {
            viewModelScope.launch {
                moviesRepository.movies.collect { movies ->
                    val movie = movies.find { it.id == movieId }
                    if (movie != null) {
                        _title.value = movie.title
                        _genre.value = movie.genre
                        _rating.value = movie.rating
                        _runtime.value = movie.runtime
                        _format.value = movie.format
                        _notes.value = movie.notes
                    }
                }
            }
        }
    }

    companion object {
        val ID_KEY = object : CreationExtras.Key<Int?> {}

        val Factory = viewModelFactory {
            initializer {
                val movieId = this[ID_KEY]
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                MovieModificationScreenViewModel(
                    movieId,
                    application.moviesRepository
                )
            }
        }
    }
}