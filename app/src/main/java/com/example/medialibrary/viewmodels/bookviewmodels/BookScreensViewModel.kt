package com.example.medialibrary.viewmodels.bookviewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.medialibrary.models.BookPage
import com.example.medialibrary.repositories.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BooksScreenViewModel: ViewModel() {
    private val _books = MutableStateFlow(emptyList<BookPage>())
    val books: StateFlow<List<BookPage>> = _books

    init {
        viewModelScope.launch {
            BooksRepository.books.collect {
                _books.value = it
            }
        }
    }
}