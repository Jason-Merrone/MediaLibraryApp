package com.example.medialibrary.repositories

import com.example.medialibrary.models.BookPage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object BooksRepository {
    private var idCounter = 0
    private val _books = MutableStateFlow(emptyList<BookPage>())
    val books: StateFlow<List<BookPage>> = _books

    fun addPage(
        title: String,
        author: String,
        genre: String,
        format: String,
        numPages: Int,
        notes: String
    ) {
        val newPage = BookPage(
            id = ++idCounter,
            title = title,
            author = author,
            format = format,
            numPages = numPages,
            genre = genre,
            notes = notes
        )
        _books.value += newPage
    }

    fun updatePage(
        id: Int,
        title: String,
        author: String,
        genre: String,
        format: String,
        numPages: Int,
        notes: String
    ) {
        val updatedPage = BookPage(
            id = id,
            title = title,
            author = author,
            format = format,
            numPages = numPages,
            genre = genre,
            notes = notes
        )
        _books.value = _books.value.map { page ->
            if (page.id == id) updatedPage else page
        }
    }

    fun deletePage(id: Int) {
        _books.value = _books.value.filter { page ->
            page.id != id
        }
    }
}