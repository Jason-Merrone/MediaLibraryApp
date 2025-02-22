package com.example.medialibrary.ui.screens.bookscreens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.medialibrary.viewmodels.bookviewmodels.BooksScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BooksScreen(
    gotToBookModification: (id: Int?) -> Unit,
    goToBook: (id: Int) -> Unit,
    viewModel: BooksScreenViewModel
) {
    val books by viewModel.books.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("My Books") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { gotToBookModification(null) }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add Book")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (books.isEmpty()) {
                Text(
                    text = "No books yet. Click the + button to add some!",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    textAlign = TextAlign.Center
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    items(books) { book ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            onClick = { goToBook(book.id) }
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = book.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}