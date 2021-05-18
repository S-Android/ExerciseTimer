package com.ht.exceciseinternal.utility

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

object Utils {
    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SuppressLint("SimpleDateFormat")
    fun getCurrentDateMillis(): Long? {
        return try {
            SimpleDateFormat("dd/MM/yyyy").parse(SimpleDateFormat("dd/MM/yyyy").format(Date())).time
        } catch (ex: Exception) {
            null
        }
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    @SuppressLint("SimpleDateFormat")
    fun getFormattedTime(time: Long, timeFormat: String = "dd-MM-yy"): String? {
        return try {
            SimpleDateFormat(timeFormat).format(Date(time))
        } catch (ex: Exception) {
            null
        }
    }
}