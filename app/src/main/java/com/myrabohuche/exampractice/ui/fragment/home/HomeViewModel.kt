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
            listOf("Cameroon Language", "Nigerian Language", "South African Language", "German Language"),
            listOf("15 minute", "5 minute", "1 minute", "1 hour", "2 hour", "30 minute"),
            listOf(),
            listOf("2", "5", "20", "30", "40", "50", "70", "100"),
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
            listOf("Cameroon Language", "Nigerian Language", "South African Language", "German Language"),
            listOf("15 minute", "5 minute", "1 minute", "1 hour", "2 hour", "30 minute"),
            listOf("University of Enugu", "University of Abuja", "University of Lagos"),
            listOf("2", "5", "20", "30", "40", "50", "70", "100"),
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
            listOf("Cameroon Language", "Nigerian Language", "South African Language", "German Language"),
            listOf("15 minute", "5 minute", "1 minute", "1 hour", "2 hour", "30 minute"),
            listOf(),
            listOf("2", "5", "20", "30", "40", "50", "70", "100"),
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
            listOf("Cameroon Language", "Nigerian Language", "South African Language", "German Language"),
            listOf("15 minute", "5 minute", "1 minute", "1 hour", "2 hour", "30 minute"),
            listOf(),
            listOf("2", "5", "20", "30", "40", "50", "70", "100"),
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