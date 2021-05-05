package com.ht.exceciseinternal.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ht.exceciseinternal.beans.Exercise

class ExerciseTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromExerciseString(value: String?): List<Exercise>? {
        return gson.fromJson<List<Exercise>>(value, object : TypeToken<List<Exercise>>() {}.type)
//        return try {
//            gson.fromJson<List<Exercise>>(value, object : TypeToken<List<Exercise>>() {}.type)
//        } catch (ex: Exception) {
//            null
//        }
    }

    @TypeConverter
    fun toExerciseString(value: List<Exercise>?): String {
        return gson.toJson(value)
    }
}