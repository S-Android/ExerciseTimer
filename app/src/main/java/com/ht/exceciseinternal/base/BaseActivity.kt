package com.ht.exceciseinternal.base

import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ht.exceciseinternal.R

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /** set status color */
        setStatusColor()
    }

    protected open fun setStatusColor() {
        setStatusColor(ContextCompat.getColor(this, R.color.white))
    }

    protected fun setStatusColor(color: Int, isLightTheme: Boolean = true) {
        val window: Window = window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                if (isLightTheme)
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                else
                    View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
}