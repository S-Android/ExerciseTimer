package com.ht.exceciseinternal.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.beans.MyActivity

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuits: List<Circuit>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(circuit: Circuit)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(myActivity: MyActivity)

    @Delete
    fun delete(circuit: Circuit)

    @Query("select * from exercise_table order by circuitId")
    fun getCircuits() : LiveData<List<Circuit>>

    @Query("select * from my_activity_table order by date")
    fun getMyActivity() : LiveData<List<MyActivity>>

    @Query("select * from my_activity_table order by date")
    fun getMyActivitySync() : List<MyActivity>
}