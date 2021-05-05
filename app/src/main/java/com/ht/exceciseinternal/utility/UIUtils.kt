package com.ht.exceciseinternal.utility

import android.content.Context
import android.view.View
import com.ht.exceciseinternal.ExerciseInternalApp

object UIUtils {
    fun getScreenWidth(activity: Context): Int {
        return activity.resources.displayMetrics.widthPixels
    }

    fun getScreenHeight(activity: Context): Int {
        return activity.resources.displayMetrics.heightPixels
    }

    fun dpToPx(context: Context, dp: Float): Int {
        val scale = context.resources.displayMetrics.density
        return Math.round(dp * scale)
    }

    fun getString(resourceId: Int): String = ExerciseInternalApp.instance.getString(resourceId)

    fun getVisibility(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.GONE
}