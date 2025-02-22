package com.example.medialibrary.ui.screens.moviescreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medialibrary.viewmodels.movieviewmodels.MoviesScreenViewModel

@Composable
fun MovieScreen(
    id: Int,
    viewModel: MoviesScreenViewModel// Reusing MoviesScreenViewModel
) {
    val movies by viewModel.movies.collectAsState()

    // Directly find the movie in the list
    val movie = movies.find { it.id == id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (movie != null) {
            Text(text = movie.title, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Genre: ${movie.genre ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rating: ${movie.rating ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Runtime: ${movie.runtime ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Format: ${movie.format ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Notes: ${movie.notes ?: "Not available"}")
        } else {
            // Display "not found" message if movie is null
            Text(
                text = "Movie with ID $id not found.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}