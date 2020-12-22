package com.myrabohuche.exampractice.utils

import android.app.Activity
import android.os.Build
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.text.HtmlCompat
import androidx.core.text.htmlEncode
import org.w3c.dom.Text

fun View.hideKeyboard(view: View){
    val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken,0)
}

fun minuteToMilliSecond(str: String): Long {
    return when(str){
        "5 minutes" -> 300000
        "10 minutes" -> 600000
        "15 minutes" -> 900000
        "20 minutes" -> 1200000
        "30 minutes" -> 1800000
        "50 minutes" -> 3000000
        "1 hour" -> 3600000
        "2 hours" -> 7200000
        else -> 900000
    }
}

fun suffixConversion(str: String): String {
    return when(str){
        "NECO" -> "WAEC"
        else -> str
    }
}

fun htmlToText(sp: String): Spanned {

    //val sb = StringBuilder()

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        return Html.fromHtml(sp.trim().toRegex().toString(), Html.FROM_HTML_MODE_LEGACY)
    } else {
        return HtmlCompat.fromHtml(sp.trim().toRegex().toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    }
}