package com.example.medialibrary.repositories

import com.example.medialibrary.models.MoviePage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object MoviesRepository {
    private var idCounter = 0
    private val _movies = MutableStateFlow(emptyList<MoviePage>())
    val movies: StateFlow<List<MoviePage>> = _movies

    fun addPage(
        title: String,
        runtime: String,
        genre: String,
        rating: String,
        format: String,
        notes: String
    ) {
        val newPage = MoviePage(
            id = ++idCounter,
            title = title,
            runtime = runtime,
            genre = genre,
            rating = rating,
            format = format,
            notes = notes
        )
        _movies.value += newPage
    }

    fun updatePage(
        id:Int,
        title: String,
        runtime: String,
        genre: String,
        rating: String,
        format: String,
        notes: String
    ) {
        val updatedPage = MoviePage(
            id = id,
            title = title,
            runtime = runtime,
            genre = genre,
            rating = rating,
            format = format,
            notes = notes
        )
        _movies.value = _movies.value.map { page ->
            if (page.id == id) updatedPage else page
        }
    }

    fun deletePage(id: Int) {
        _movies.value = _movies.value.filter { page ->
            page.id != id
        }
    }
}