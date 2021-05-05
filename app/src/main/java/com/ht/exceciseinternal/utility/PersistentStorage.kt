package com.ht.exceciseinternal.utility

import android.content.Context
import android.content.SharedPreferences
import com.ht.exceciseinternal.ExerciseInternalApp

private val preferences: SharedPreferences by lazy {
    ExerciseInternalApp.instance.getSharedPreferences("exercise_preference", Context.MODE_PRIVATE)
}

var uniqueId: Long
    get() {
        val value = preferences.getLong("exercise_unique_id", 0L)
        uniqueId = (value + 1) % Long.MAX_VALUE
        return value
    }
    private set(value) {
        preferences.edit()?.putLong("exercise_unique_id", value)?.apply()
    }