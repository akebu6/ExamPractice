package com.myrabohuche.exampractice.ui.fragment.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myrabohuche.exampractice.datasource.network.LoadingStatus
import com.myrabohuche.exampractice.datasource.network.Result
import com.myrabohuche.exampractice.datasource.network.repo.Repository
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthenticationViewModel @Inject constructor(
    private val repository: Repository
): ViewModel(){

    private val _success = MutableLiveData<Boolean>()
    val success: LiveData<Boolean>
        get() = _success

    private val _loadingStatus = MutableLiveData<LoadingStatus>()
    val loadingStatus: LiveData<LoadingStatus>
        get() = _loadingStatus


    fun makeSubmission(firstName: String, otherName: String, phone: String,pk:String
    ) {
        _loadingStatus.value = LoadingStatus.Loading("making your Submission ...")
        viewModelScope.launch {
            when (val result = repository.makeSubmission(firstName, otherName, phone,pk)) {
                is Result.Success -> {
                    _success.value = true
                    _loadingStatus.value = LoadingStatus.Success
                }
                is Result.Error -> {
                    _success.value = false
                    _loadingStatus.value = LoadingStatus.Error(result.message)
                }
            }
        }


    }

    fun resetSuccessValue() {
        _success.value = true
    }

}