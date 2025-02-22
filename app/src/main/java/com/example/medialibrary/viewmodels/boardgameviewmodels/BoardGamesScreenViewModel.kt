package com.example.medialibrary.viewmodels.boardgameviewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medialibrary.models.BoardGamePage
import com.example.medialibrary.repositories.BoardGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BoardGamesScreenViewModel: ViewModel() {
    private val _boardGames = MutableStateFlow(emptyList<BoardGamePage>())
    val boardGames: StateFlow<List<BoardGamePage>> = _boardGames

    init {
        viewModelScope.launch {
            BoardGamesRepository.boardGames.collect {
                _boardGames.value = it
            }
        }
    }
}