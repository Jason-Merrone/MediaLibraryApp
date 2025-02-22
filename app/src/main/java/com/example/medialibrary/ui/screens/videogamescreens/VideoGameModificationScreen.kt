package com.example.medialibrary.ui.screens.videogamescreens

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
import com.example.medialibrary.viewmodels.videogameviewmodels.VideoGameModificationScreenViewModel

@Composable
fun createVideoGameModificationScreenViewModel(noteId: Int?) = viewModel<VideoGameModificationScreenViewModel>(
    factory = VideoGameModificationScreenViewModel.Factory,
    extras = MutableCreationExtras().apply {
        this[VideoGameModificationScreenViewModel.ID_KEY] = noteId
        this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
    }
)


@Composable
fun VideoGameModificationScreen(
    id: Int?,
    goBack: () -> Unit,
    viewModel: VideoGameModificationScreenViewModel = createVideoGameModificationScreenViewModel(id)
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top=100.dp),
        horizontalAlignment = Alignment.End
    ) {
        val title by viewModel.title.collectAsState()
        val developer by viewModel.developer.collectAsState()
        val genre by viewModel.genre.collectAsState()
        val rating by viewModel.rating.collectAsState()
        val platform by viewModel.platform.collectAsState()
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
            value = developer,
            onValueChange = { viewModel.setDeveloper(it) },
            label = { Text("Developer") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = genre,
            onValueChange = { viewModel.setGenre(it) },
            label = { Text("Genre") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = rating,
            onValueChange = { viewModel.setRating(it) },
            label = { Text("Rating") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = platform,
            onValueChange = { viewModel.setPlatform(it) },
            label = { Text("Platform") }
        )

        Spacer(modifier = Modifier.height(8.dp))


        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = notes,
            onValueChange = { viewModel.setNotes(it) },
            label = { Text("Notes") },
            singleLine = true
        )

        Button(onClick = { viewModel.saveVideoGame(); goBack() }) {
            Text("Save")
        }
    }
}