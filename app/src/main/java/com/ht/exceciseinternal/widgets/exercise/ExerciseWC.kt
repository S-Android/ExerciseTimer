package com.ht.exceciseinternal.widgets.exercise

import android.os.Parcelable
import com.ht.exceciseinternal.beans.Exercise
import com.ht.exceciseinternal.widgets.BaseWC
import kotlinx.android.parcel.Parcelize

@Parcelize
class ExerciseWC(val exercise: Exercise?) : BaseWC(), Parcelable {
    companion object {
        val type = ExerciseWC::class.java.hashCode()
        const val ACTION_EXERCISE_PICK = "action_exercise_pick"
        const val ACTION_EXERCISE_UPDATE = "action_exercise_update"
        const val ACTION_EXERCISE_DELETE = "action_exercise_delete"
    }

    override fun copy() = ExerciseWC(exercise?.copy()).apply { copyValuesIntoSuper(this@ExerciseWC) }
}