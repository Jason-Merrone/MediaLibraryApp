package com.example.medialibrary.viewmodels.bookviewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.medialibrary.MediaLibraryApplication
import com.example.medialibrary.repositories.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BookModificationScreenViewModel(
    private val bookId: Int?,
    private val booksRepository: BooksRepository
): ViewModel() {
    private val _title = MutableStateFlow("")
    private val _author = MutableStateFlow("")
    private val _genre = MutableStateFlow("")
    private val _format = MutableStateFlow("")
    private val _numPages = MutableStateFlow(0)
    private val _notes = MutableStateFlow("")

    val title: StateFlow<String> = _title
    val author: StateFlow<String> = _author
    val genre: StateFlow<String> = _genre
    val format: StateFlow<String> = _format
    val numPages: StateFlow<Int> = _numPages
    val notes: StateFlow<String> = _notes

    fun setTitle(title: String) {
        _title.value = title
    }

    fun setAuthor(author: String) {
        _author.value = author
    }

    fun setGenre(genre: String) {
        _genre.value = genre
    }

    fun setFormat(format: String) {
        _format.value = format
    }

    fun setNumPages(numPages: Int) {
        _numPages.value = numPages
    }

    fun setNotes(notes: String) {
        _notes.value = notes
    }

    fun saveBook() {
        if (bookId != null) {
            booksRepository.updatePage(
                bookId,
                _title.value,
                _author.value,
                _genre.value,
                _format.value,
                _numPages.value,
                _notes.value
            )
        } else {
            booksRepository.addPage(
                _title.value,
                _author.value,
                _genre.value,
                _format.value,
                _numPages.value,
                _notes.value
            )
        }
    }

    init {
        if (bookId != null) {
            viewModelScope.launch {
                booksRepository.books.collect { books ->
                    val book = books.find { it.id == bookId }
                    if (book != null) {
                        _title.value = book.title
                        _author.value = book.author
                        _genre.value = book.genre
                        _format.value = book.format
                        _numPages.value = book.numPages
                        _notes.value = book.notes
                    }
                }
            }
        }
    }

    companion object {
        val ID_KEY = object : CreationExtras.Key<Int?> {}

        val Factory = viewModelFactory {
            initializer {
                val bookId = this[ID_KEY]
                val application = this[APPLICATION_KEY] as MediaLibraryApplication
                BookModificationScreenViewModel(
                    bookId,
                    application.booksRepository
                )
            }
        }
    }
}