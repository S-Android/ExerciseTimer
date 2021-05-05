package com.ht.exceciseinternal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ht.exceciseinternal.beans.Circuit

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuits: List<Circuit>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuit: Circuit)

    @Query("select * from exercise_table order by circuitId")
    fun getCircuits() : LiveData<List<Circuit>>
}