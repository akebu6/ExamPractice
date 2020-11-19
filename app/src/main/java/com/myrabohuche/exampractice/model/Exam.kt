package com.myrabohuche.exampractice.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class Exam(val subject: List<String>,
                val time: List<String>,
                val school: List<String> = emptyList(),
                val numQues: List<String> = emptyList(),
                val suffix: String): Parcelable