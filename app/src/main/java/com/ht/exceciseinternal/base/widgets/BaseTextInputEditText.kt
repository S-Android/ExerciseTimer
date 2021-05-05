package com.ht.exceciseinternal.base.widgets

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textfield.TextInputEditText

open class BaseTextInputEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : TextInputEditText(context, attrs) {
    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        super.onSelectionChanged(selStart, selEnd)
        this.setSelection(text?.length ?: 0)
    }
}