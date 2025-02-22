package com.example.medialibrary.ui.screens.boardgamescreens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.medialibrary.MediaLibraryApplication
import com.example.medialibrary.viewmodels.boardgameviewmodels.BoardGameModificationScreenViewModel

@Composable
fun createBoardGameModificationScreenViewModel(noteId: Int?) = viewModel<BoardGameModificationScreenViewModel>(
    factory = BoardGameModificationScreenViewModel.Factory,
    extras = MutableCreationExtras().apply {
        this[BoardGameModificationScreenViewModel.ID_KEY] = noteId
        this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
    }
)


@Composable
fun BoardGameModificationScreen(
    id: Int?,
    goBack: () -> Unit,
    viewModel: BoardGameModificationScreenViewModel = createBoardGameModificationScreenViewModel(id)
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top=100.dp),
        horizontalAlignment = Alignment.End
    ) {
        val title by viewModel.title.collectAsState()
        val minPlayers by viewModel.minPlayers.collectAsState()
        val maxPlayers by viewModel.maxPlayers.collectAsState()
        val type by viewModel.type.collectAsState()
        val notes by viewModel.notes.collectAsState()

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = { viewModel.setTitle(it) },
            label = { Text("Title") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = minPlayers.toString(),
            onValueChange = { viewModel.setMinPlayers(it.toIntOrNull() ?: 0) },
            label = { Text("Min Players") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = maxPlayers.toString(),
            onValueChange = { viewModel.setMaxPlayers(it.toIntOrNull() ?: 0) },
            label = { Text("Max Players") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = type,
            onValueChange = { viewModel.setType(it) },
            label = { Text("Type") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = notes,
            onValueChange = { viewModel.setNotes(it) },
            label = { Text("Notes") },
            singleLine = true
        )

        Button(onClick = { viewModel.saveBoardGame(); goBack() }) {
            Text("Save")
        }
    }
}