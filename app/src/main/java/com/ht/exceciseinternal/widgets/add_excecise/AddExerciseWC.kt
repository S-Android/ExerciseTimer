package com.ht.exceciseinternal.widgets.add_excecise

import android.os.Parcelable
import com.ht.exceciseinternal.widgets.BaseWC
import kotlinx.android.parcel.Parcelize

@Parcelize
class AddExerciseWC : BaseWC(), Parcelable {
    companion object {
        val type = AddExerciseWC::class.java.hashCode()
        const val ACTION_ADD_EXERCISE_CLICK = "action_add_exercise_click"
    }

    override fun copy() = AddExerciseWC().apply { copyValuesIntoSuper(this@AddExerciseWC) }
}