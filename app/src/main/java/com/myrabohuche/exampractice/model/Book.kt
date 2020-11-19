package com.myrabohuche.exampractice.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Book(val title: String, val pages: List<Chapter>): Parcelable