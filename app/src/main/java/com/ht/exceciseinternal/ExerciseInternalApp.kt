package com.ht.exceciseinternal

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class ExerciseInternalApp: Application() {
    companion object {
        lateinit var instance: ExerciseInternalApp
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}