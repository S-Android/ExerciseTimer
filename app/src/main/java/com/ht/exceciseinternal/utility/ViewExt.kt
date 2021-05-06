package com.ht.exceciseinternal.utility

import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.ht.exceciseinternal.ExerciseInternalApp

fun TextView.setHtmlText(text: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
    } else {
        setText(Html.fromHtml(text));
    }
}

fun AppCompatEditText.onDone(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback.invoke()
        }
        false
    }
}

fun View.hideKeyboard() {
    val inputMethodManager = context.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}

fun String?.getDrawable(): Drawable? {
    return try {
        val context = ExerciseInternalApp.instance
        val drawableId = context.resources.getIdentifier(this, "mipmap", context.packageName)
        ContextCompat.getDrawable(context, drawableId)
    } catch (ex: Exception) {
        null
    }
}