package com.myrabohuche.exampractice.ui.fragment.library

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myrabohuche.exampractice.model.Book
import com.myrabohuche.exampractice.model.Chapter
import kotlinx.coroutines.SupervisorJob

class LibraryViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()

    var books: List<Book> = arrayListOf(
        Book( "Design Principles and Design Patterns",
            listOf(
                Chapter("Chapter 1 Architecture and Dependencies", "Architecture and Dependencies"),
                Chapter("Chapter 2 Symptoms of Rotting Design", "Symptoms of Rotting Design"),
                Chapter("Chapter 3 Changing Requirements", "Changing Requirements"),
                Chapter("Chapter 4 Dependency Management", "Dependency Management"))),
        Book( "Principles of Object Oriented Class Design",
            listOf(
                Chapter("Chapter 1 The Open Closed Principle (OCP)", "The Open Closed Principle (OCP)"),
                Chapter("Chapter 2 The Liskov Substitution Principle (LSP)", "The Liskov Substitution Principle (LSP)"),
                Chapter("Chapter 3 The Dependency Inversion Principle (DIP)", "The Dependency Inversion Principle (DIP)"),
                Chapter("Chapter 4 The Interface Segregation Principle (ISP)", "The Interface Segregation Principle (ISP)"),
                Chapter("Chapter 5 Principles of Package Architecture", "Principles of Package Architecture"))),
        Book( "Principles of Object Oriented Class Design",
            listOf(
                Chapter("Chapter 1 The Open Closed Principle (OCP)", "The Open Closed Principle (OCP)"),
                Chapter("Chapter 2 The Liskov Substitution Principle (LSP)", "The Liskov Substitution Principle (LSP)"),
                Chapter("Chapter 3 The Dependency Inversion Principle (DIP)", "The Dependency Inversion Principle (DIP)"),
                Chapter("Chapter 4 The Interface Segregation Principle (ISP)", "The Interface Segregation Principle (ISP)"),
                Chapter("Chapter 5 Principles of Package Architecture", "Principles of Package Architecture"))),
        Book( "Introduction to Algorithms",
            listOf(
                Chapter("Chapter 1 Introduction", "Introduction"),
                Chapter("Chapter 3 The Role of Algorithms in Computing", "The Role of Algorithms in Computing")))
       )

    private val _downloadedBooks = MutableLiveData<List<Book>>()
    val downloadedBooks: LiveData<List<Book>>
        get() = _downloadedBooks

    init {
        _downloadedBooks.postValue(books.onEach { it.title })
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}