package com.ht.exceciseinternal.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

abstract class BaseWV<Config: BaseWC> constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): ConstraintLayout(context, attrs, defStyleAttr) {

    var config: Config? = null
    open fun updateView(config: Config) {
        this.config = config
    }
}