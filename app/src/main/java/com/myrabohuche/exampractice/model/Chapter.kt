package com.myrabohuche.exampractice.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Chapter(val chapIndex: String, val text: String): Parcelable