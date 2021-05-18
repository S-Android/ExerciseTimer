package com.ht.exceciseinternal.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.beans.Exercise
import com.ht.exceciseinternal.beans.MyActivity

class ExerciseTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromExerciseString(value: String?): List<Exercise>? {
        return gson.fromJson<List<Exercise>>(value, object : TypeToken<List<Exercise>>() {}.type)
    }

    @TypeConverter
    fun toExerciseString(value: List<Exercise>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromCircuitString(value: String?): List<Circuit>? {
        return gson.fromJson<List<Circuit>>(value, object : TypeToken<List<Circuit>>() {}.type)
    }

    @TypeConverter
    fun toCircuitString(value: List<Circuit>?): String {
        return gson.toJson(value)
    }
}