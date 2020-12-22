package com.myrabohuche.exampractice.model

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Question(val question: String, val options: List<String>, val answerIndex: Int, val examYear: String):Parcelable {

    override fun toString(): String {
        return "Question{" +
                "mQuestion='" + question + '\''.toString() +
                ", mChoiceList=" + options +
                ", mAnswerIndex=" + answerIndex +
                '}'.toString()
    }
}
