package com.example.medialibrary.ui.screens.boardgamescreens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.medialibrary.viewmodels.boardgameviewmodels.BoardGamesScreenViewModel

@Composable
fun BoardGameScreen(
    id: Int,
    viewModel: BoardGamesScreenViewModel// Reusing BoardGamesScreenViewModel
) {
    val boardGames by viewModel.boardGames.collectAsState()

    // Directly find the board game in the list
    val boardGame = boardGames.find { it.id == id }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (boardGame != null) {
            Text(text = boardGame.title, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Minimum Players: ${boardGame.minPlayers ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Maximum Players: ${boardGame.maxPlayers ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Type: ${boardGame.type ?: "Not available"}")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Notes: ${boardGame.notes ?: "Not available"}")
        } else {
            // Display "not found" message if boardGame is null
            Text(
                text = "Board game with ID $id not found.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}