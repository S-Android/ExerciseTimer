package com.ht.exceciseinternal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.beans.MyActivity

@Database(entities = [Circuit::class, MyActivity::class], version = 1)
@TypeConverters(ExerciseTypeConverter::class)
abstract class ExerciseDataBase : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao

    companion object {
        private var instance: ExerciseDataBase? = null

        @Synchronized
        fun getInstance(context: Context): ExerciseDataBase {
            if(instance == null) {
                instance = Room.databaseBuilder(context.applicationContext, ExerciseDataBase::class.java, "exercise_database.db").build()
            }
            return instance!!
        }
    }
}