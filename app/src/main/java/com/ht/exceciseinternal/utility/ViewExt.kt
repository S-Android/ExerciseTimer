package com.ht.exceciseinternal.utility

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build
import android.text.Html
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText

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