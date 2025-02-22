package com.example.medialibrary.repositories

import com.example.medialibrary.models.VideoGamePage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object VideoGamesRepository {
    private var idCounter = 0
    private val _videoGames = MutableStateFlow(emptyList<VideoGamePage>())
    val videoGames: StateFlow<List<VideoGamePage>> = _videoGames

    fun addPage(
        title: String,
        developer: String,
        genre: String,
        rating: String,
        platform: String,
        notes: String
    ) {
        val newPage = VideoGamePage(
            id = ++idCounter,
            title = title,
            developer = developer,
            genre = genre,
            rating = rating,
            platform = platform,
            notes = notes
        )
        _videoGames.value += newPage
    }

    fun updatePage(
        id: Int,
        title: String,
        developer: String,
        genre: String,
        rating: String,
        platform: String,
        notes: String
    ) {
        val updatedPage = VideoGamePage(
            id = id,
            title = title,
            developer = developer,
            genre = genre,
            rating = rating,
            platform = platform,
            notes = notes
        )
        _videoGames.value = _videoGames.value.map { page ->
            if (page.id == id) updatedPage else page
        }
    }

    fun deletePage(id: Int) {
        _videoGames.value = _videoGames.value.filter { page ->
            page.id != id
        }
    }
}