package com.example.bookrepository.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bookrepository.data.Book
import com.example.bookrepository.data.BookRepository

class BookViewModel {
    private val _bookList = MutableLiveData<List<Book>>()
    val bookList: LiveData<List<Book>> = _bookList

    init {
        _bookList.value = BookRepository.listOfBooks
    }

    fun addBook(title: String, writer: String, genre: String, synopsis: String){
        val book = Book(title, writer, genre, synopsis)
        BookRepository.addBook(book)
        val updatedList = _bookList.value.orEmpty().toMutableList()
        updatedList.add(book)
        _bookList.value = updatedList
    }

    fun deleteBook(book: Book){
        BookRepository.deleteBook(book.title)
        val updatedList = _bookList.value.orEmpty().toMutableList()
        updatedList.remove(book)
        _bookList.value = updatedList
    }
}