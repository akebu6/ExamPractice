package com.myrabohuche.exampractice.ui.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myrabohuche.exampractice.model.*
import kotlinx.coroutines.SupervisorJob

class HomeViewModel : ViewModel() {

    private val viewModelJob = SupervisorJob()

    var jambExam: List<Exam> = arrayListOf(
        Exam(
            listOf(
                "English Language",
                "Principles Of Accounts",
                "Agricultural Science",
                "Biology",
                "Chemistry",
                "Christian Religious Knowledge",
                "Commerce",
                "Economics",
                "Fine Arts",
                "French",
                "Geography",
                "Government",
                "History",
                "Islamic Religious Knowledge",
                "Literature In English",
                "Mathematics",
                "Music",
                "Physics",
                "Sweet Sixteen"),
            listOf( "15 minutes", "5 minutes", "10 minutes" , "20 minutes", "30 minutes", "50 minutes", "1 hour"),
            listOf(
                "All Schools"),
            listOf("40", "20", "30", "50","60", "70","80","90", "100"),
            "JAMB"
        )
    )

    private val _downloadedJamb = MutableLiveData<List<Exam>>()
    val downloadedJamb: LiveData<List<Exam>>
        get() = _downloadedJamb

    init {
        _downloadedJamb.postValue(jambExam)
        //_downloadedJamb.postValue(jambExam.onEach { it.subject })
    }
    ///////////////////////////////////////////////////////////////

    var postUtmeExam: List<Exam> = arrayListOf(
        Exam(
            listOf(
                "English Language",
                "Biology",
                "Chemistry",
                "Commerce",
                "Economics",
                "Geography",
                "General Paper",
                "Government",
                "Literature in English",
                "Mathematics",
                "Physics"),
            listOf("5 minutes", "15 minutes", "10 minutes" , "20 minutes", "30 minutes", "50 minutes", "1 hour"),

            listOf(
                "Adekunle Ajasin University",
                "Bayero University Kano",
                "Ekiti State University",
                "Federal University Of Agriculture Abeokuta",
                "Federal University Of Technology Akure",
                "Ladoke Akintola University Of Technology",
                "Nnamdi Azikiwe University",
                "Obafemi Awolowo University",
                "University Of Benin",
                "University Of Ibadan",
                "University Of Lagos",
                "University Of Nigeria"),
            listOf("15", "10", "5", "30", "20", "25"),
            "Post UTME"
        )
    )

    private val _downloadedpostUtme = MutableLiveData<List<Exam>>()
    val downloadedPostutme: LiveData<List<Exam>>
        get() = _downloadedpostUtme

    init {
        _downloadedpostUtme.postValue(postUtmeExam)
    }

    ///////////////////////////////////////////////////////////////

    var waecExam: List<Exam> = arrayListOf(
        Exam(
            listOf(
                "English Language",
                "Accounts",
                "Agricultural Science",
                "Christian Religious Knowledge",
                "Commerce",
                "Geography",
                "Biology",
                "Chemistry",
                "Economics",
                "Government",
                "Literature In English",
                "Mathematics",
                "Physics"),
            listOf( "15 minutes", "5 minutes", "10 minutes" , "20 minutes", "30 minutes", "50 minutes", "1 hour"),
            listOf("All Schools"),
            listOf("40", "20", "30", "50","60", "70","80","90", "100"),
            "WAEC"
        )
    )

    private val _downloadedWaec = MutableLiveData<List<Exam>>()
    val downloadedWaec: LiveData<List<Exam>>
        get() = _downloadedWaec

    init {
        _downloadedWaec.postValue(waecExam)
    }

    ///////////////////////////////////////////////////////////////

    var necoExam: List<Exam> = arrayListOf(
        Exam(
            listOf(
                "English Language",
                "Accounts",
                "Agricultural Science",
                "Christian Religious Knowledge",
                "Commerce",
                "Geography",
                "Biology",
                "Chemistry",
                "Economics",
                "Government",
                "Literature In English",
                "Mathematics",
                "Physics"),
            listOf( "15 minutes", "5 minutes", "10 minutes" , "20 minutes", "30 minutes", "50 minutes", "1 hour"),
            listOf("All Schools"),
            listOf("40", "20", "30", "50","60", "70","80","90", "100"),
            "NECO"
        )
    )

    private val _downloadedneco = MutableLiveData<List<Exam>>()
    val downloadedNeco: LiveData<List<Exam>>
        get() = _downloadedneco

    init {
        _downloadedneco.postValue(necoExam)
    }
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}