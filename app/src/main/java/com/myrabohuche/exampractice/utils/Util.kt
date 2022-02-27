package com.myrabohuche.exampractice.utils

import android.app.Activity
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.N
import android.text.Html
import android.text.Spanned
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import androidx.core.text.htmlEncode
import com.google.android.play.core.appupdate.AppUpdateInfo
import com.google.android.play.core.tasks.Task
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import org.w3c.dom.Text
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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
fun Boolean?.orFalse() = this ?: false


fun isAtLeast(versionCode: Int): Boolean {
    return SDK_INT >= versionCode
}

@RequiresApi(N)
fun parseHtml(description: String?): Spanned? {
    return if (description == null || description.isBlank()) {
        null
    } else {
        description.replace("\n", "<br>").let {
            if (isAtLeast(N)) {
                Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY)
            } else {
                @Suppress("DEPRECATION")
                Html.fromHtml(it)
            }
        }
    }
}
val ioScope = CoroutineScope(Dispatchers.IO)

val uiScope = CoroutineScope(Dispatchers.Main)

fun <T> CoroutineScope.catchingAsync(block: suspend () -> T): Deferred<Result<T>> =
    ioScope.async { runCatching { block() } }

suspend fun Task<AppUpdateInfo>.await(): AppUpdateInfo {
    return suspendCoroutine { continuation ->
        addOnCompleteListener { result ->
            if (result.isSuccessful) {
                continuation.resume(result.result)
            } else {
                continuation.resumeWithException(result.exception!!)
            }
        }
    }
}
