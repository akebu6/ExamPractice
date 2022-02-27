package com.myrabohuche.exampractice.datasource.network.repo

import android.util.Log
import com.myrabohuche.exampractice.datasource.network.ApiService
import com.myrabohuche.exampractice.datasource.network.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService){

    suspend fun makeSubmission(
        firstName: String,
        otherName: String,
        phone: String,
        pk: String
    ): Result<Unit> {
        return try {
            Result.Success(
                withContext(Dispatchers.IO) {
                    apiService.makeSubmissionAsync(firstName, otherName, phone,pk)
                        .await()
                })
        } catch (ex: Exception) {
            Log.e("mistake", ex.message!!)
            return Result.Error(
                ex.message!!
            )
        }
    }
}

