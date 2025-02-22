package com.example.medialibrary.ui.screens.bookscreens

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
import com.example.medialibrary.viewmodels.bookviewmodels.BookModificationScreenViewModel

@Composable
fun createBookModificationScreenViewModel(noteId: Int?) = viewModel<BookModificationScreenViewModel>(
    factory = BookModificationScreenViewModel.Factory,
    extras = MutableCreationExtras().apply {
        this[BookModificationScreenViewModel.ID_KEY] = noteId
        this[APPLICATION_KEY] = LocalContext.current.applicationContext as MediaLibraryApplication
    }
)


@Composable
fun BookModificationScreen(
    id: Int?,
    goBack: () -> Unit,
    viewModel: BookModificationScreenViewModel = createBookModificationScreenViewModel(id)
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(top=100.dp),
        horizontalAlignment = Alignment.End
    ) {
        val title by viewModel.title.collectAsState()
        val author by viewModel.author.collectAsState()
        val genre by viewModel.genre.collectAsState()
        val format by viewModel.format.collectAsState()
        val numPages by viewModel.numPages.collectAsState()
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
            value = author,
            onValueChange = { viewModel.setAuthor(it) },
            label = { Text("Author") }
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
            value = format,
            onValueChange = { viewModel.setFormat(it) },
            label = { Text("Format") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = numPages.toString(),
            onValueChange = { viewModel.setNumPages(it.toIntOrNull() ?: 0) },
            label = { Text("Number of Pages") }
        )

        Spacer(modifier = Modifier.height(8.dp))


        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = notes,
            onValueChange = { viewModel.setNotes(it) },
            label = { Text("Notes") },
            singleLine = true
        )

        Button(onClick = { viewModel.saveBook(); goBack() }) {
            Text("Save")
        }
    }
}