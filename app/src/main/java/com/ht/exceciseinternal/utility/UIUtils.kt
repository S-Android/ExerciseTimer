package com.ht.exceciseinternal.utility

import android.content.Context
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ht.exceciseinternal.ExerciseInternalApp
import java.io.IOException

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

    inline fun <reified T> getJsonDataFromAsset(context: Context, fileName: String = "exercise.json"): T? {
        return try {
            val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
            toParse(jsonString)
        } catch (ioException: IOException) {
            null
        }
    }

    inline fun <reified T> toParse(jsonString: String?): T? {
        return try {
            Gson().fromJson<T>(jsonString, object : TypeToken<T?>() {}.type)
        } catch (ex: Exception) {
            null
        }
    }
}