package com.example.medialibrary.viewmodels.videogameviewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medialibrary.models.VideoGamePage
import com.example.medialibrary.repositories.VideoGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoGamesScreenViewModel: ViewModel() {
    private val _videoGames = MutableStateFlow(emptyList<VideoGamePage>())
    val videoGames: StateFlow<List<VideoGamePage>> = _videoGames

    init {
        viewModelScope.launch {
            VideoGamesRepository.videoGames.collect {
                _videoGames.value = it
            }
        }
    }
}