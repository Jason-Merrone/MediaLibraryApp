package com.example.medialibrary.ui.screens.bookscreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medialibrary.viewmodels.bookviewmodels.BooksScreenViewModel

@Composable
fun BookScreen(
    id: Int,
    viewModel: BooksScreenViewModel// Reusing BooksScreenViewModel
) {
    val books by viewModel.books.collectAsState()

    // Directly find the book in the list
    val book = books.find { it.id == id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (book != null) {
            Text(text = book.title, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Author: ${book.author ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Genre: ${book.genre ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Format: ${book.format ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Number of Pages: ${book.numPages ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Notes: ${book.notes ?: "Not available"}")
        } else {
            // Display "not found" message if book is null
            Text(
                text = "Book with ID $id not found.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}