package com.example.medialibrary.ui.screens.videogamescreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medialibrary.viewmodels.videogameviewmodels.VideoGamesScreenViewModel

@Composable
fun VideoGameScreen(
    id: Int,
    viewModel: VideoGamesScreenViewModel// Reusing VideoGamesScreenViewModel
) {
    val videoGames by viewModel.videoGames.collectAsState()

    // Directly find the video game in the list
    val videoGame = videoGames.find { it.id == id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (videoGame != null) {
            Text(text = videoGame.title, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Developer: ${videoGame.developer ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Genre: ${videoGame.genre ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Rating: ${videoGame.rating ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Platform: ${videoGame.platform ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Notes: ${videoGame.notes ?: "Not available"}")
        } else {
            // Display "not found" message if videoGame is null
            Text(
                text = "Video Game with ID $id not found.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}