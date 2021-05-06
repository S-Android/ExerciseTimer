package com.ht.exceciseinternal.widgets.pick_exercise

import android.os.Parcelable
import com.ht.exceciseinternal.beans.RawExercise
import com.ht.exceciseinternal.widgets.BaseWC
import kotlinx.android.parcel.Parcelize

@Parcelize
class PickExerciseWC(val rawExercise: RawExercise?) : BaseWC(), Parcelable {
    companion object {
        val type = PickExerciseWC::class.java.hashCode()
        const val ACTION_RAW_EXERCISE_CLICK = "action_raw_exercise_click"
    }

    override fun copy() = PickExerciseWC(rawExercise?.copy()).apply { copyValuesIntoSuper(this@PickExerciseWC) }
}