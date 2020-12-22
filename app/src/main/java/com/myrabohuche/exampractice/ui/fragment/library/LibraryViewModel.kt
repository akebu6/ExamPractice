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
        Book( "Sweet Sixteen",
            listOf(
                Chapter("chapter 1 the letter", "THE LETTER"),
                Chapter("chapter 2 the driver", "THE DRIVE"),
                Chapter("chapter 3 the work", "THE WORK"),
                Chapter("chapter 4 the test", "THE TEST"),
                Chapter("chapter 5 dating", "DATING"),
                Chapter("chapter 6 stereotype", "STEREOTYPE"),
                Chapter("chapter 7 beauty", "BEAUTY"))),

                Book( "JAMB Syllabus",
            listOf(
                Chapter("About JAMB", "About JAMB"),
                Chapter("English Syllabus", "English Syllabus"),
                Chapter("Mathematics Syllabus", "Mathematics Syllabus"),
                Chapter("Chemistry Syllabus", "Chemistry Syllabus"),
                Chapter("Physics Syllabus", "Physics Syllabus"),
                Chapter("Biology Syllabus", "Biology Syllabus"),
                Chapter("Account Syllabus", "Account Syllabus"),
                Chapter("Economics Syllabus", "Economics Syllabus"),
                Chapter("Commerce Syllabus", "Commerce Syllabus"),
                Chapter("CRK Syllabus", "CRK Syllabus"),
                Chapter("Geography Syllabus", "Geography Syllabus"),
                Chapter("Government Syllabus", "Government Syllabus"),
                Chapter("IRk Syllabus", "IRk Syllabus"),
                Chapter("Literature Syllabus", "Literature Syllabus"),
                Chapter("Music Syllabus", "Music Syllabus"),
                Chapter("Yoruba Syllabus", "Yoruba Syllabus"),
                Chapter("Igbo Syllabus", "Igbo Syllabus"),
                Chapter("History Syllabus", "History Syllabus"),
                Chapter("Hausa Syllabus", "Hausa Syllabus"),
                Chapter("Arabic Syllabus", "Arabic Syllabus"),
                Chapter("French Syllabus", "French Syllabus"),
                Chapter("Fine Arts Syllabus", "Fine Arts Syllabus"),
                Chapter("Agriculture Syllabus", "Agriculture Syllabus"),
                Chapter("Home Economics Syllabus", "Home Economics Syllabus")))
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