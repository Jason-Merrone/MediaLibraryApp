package com.example.medialibrary.viewmodels.boardgameviewmodels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibrary.MediaLibraryApplication
import com.example.medialibrary.repositories.BoardGamesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BoardGameModificationScreenViewModel(
    private val gameId: Int?,
    private val boardGamesRepository: BoardGamesRepository
): ViewModel() {
    private val _title = MutableStateFlow("")
    private val _minPlayers = MutableStateFlow(0)
    private val _maxPlayers = MutableStateFlow(0)
    private val _type = MutableStateFlow("")
    private val _notes = MutableStateFlow("")

    val title: StateFlow<String> = _title
    val minPlayers: StateFlow<Int> = _minPlayers
    val maxPlayers: StateFlow<Int> = _maxPlayers
    val type: StateFlow<String> = _type
    val notes: StateFlow<String> = _notes

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setMinPlayers(minPlayers: Int) {
        _minPlayers.value = minPlayers
    }

    fun setMaxPlayers(maxPlayers: Int) {
        _maxPlayers.value = maxPlayers
    }

    fun setType(type: String) {
        _type.value = type
    }

    fun setNotes(notes: String) {
        _notes.value = notes
    }

    fun saveBoardGame() {
        if (gameId != null) {
            boardGamesRepository.updatePage(
                gameId,
                _title.value,
                _minPlayers.value,
                _maxPlayers.value,
                _type.value,
                _notes.value
            )
        } else {
            boardGamesRepository.addPage(
                _title.value,
                _minPlayers.value,
                _maxPlayers.value,
                _type.value,
                _notes.value
            )
        }
    }

    init {
        if (gameId != null) {
            viewModelScope.launch {
                boardGamesRepository.boardGames.collect { games ->
                    val game = games.find { it.id == gameId }
                    if (game != null) {
                        _title.value = game.title
                        _minPlayers.value = game.minPlayers
                        _maxPlayers.value = game.maxPlayers
                        _type.value = game.type
                        _notes.value = game.notes
                    }
                }
            }
        }
    }

    companion object {
        val ID_KEY = object : CreationExtras.Key<Int?> {}

        val Factory = viewModelFactory {
            initializer {
                val gameId = this[ID_KEY]
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                BoardGameModificationScreenViewModel(
                    gameId,
                    application.boardGamesRepository
                )
            }
        }
    }
}