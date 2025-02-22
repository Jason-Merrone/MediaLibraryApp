package com.example.medialibrary.repositories

import com.example.medialibrary.models.BoardGamePage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object BoardGamesRepository {
    private var idCounter = 0
    private var num = 0;
    private val _boardGames = MutableStateFlow(emptyList<BoardGamePage>())
    val boardGames: StateFlow<List<BoardGamePage>> = _boardGames

    fun addPage(
        title: String,
        minPlayers: Int,
        maxPlayers: Int,
        type: String,
        notes: String
    ) {
        val newPage = BoardGamePage(
            id = ++idCounter,
            title = title,
            minPlayers = minPlayers,
            maxPlayers = maxPlayers,
            type = type,
            notes = notes
        )
        _boardGames.value += newPage
        num++
    }

    fun updatePage(
        id: Int,
        title: String,
        minPlayers: Int,
        maxPlayers: Int,
        type: String,
        notes: String
    ) {
        val updatedPage = BoardGamePage(
            id = id,
            title = title,
            minPlayers = minPlayers,
            maxPlayers = maxPlayers,
            type = type,
            notes = notes
        )
        _boardGames.value = _boardGames.value.map { page ->
            if (page.id == id) updatedPage else page
        }
    }

    fun deletePage(id: Int) {
        _boardGames.value = _boardGames.value.filter { page ->
            page.id != id
        }
        num--
    }
}