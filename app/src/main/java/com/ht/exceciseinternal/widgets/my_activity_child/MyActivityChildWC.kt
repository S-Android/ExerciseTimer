package com.ht.exceciseinternal.widgets.my_activity_child

import android.os.Parcelable
import com.ht.exceciseinternal.beans.Exercise
import com.ht.exceciseinternal.widgets.BaseWC
import kotlinx.android.parcel.Parcelize

@Parcelize
class MyActivityChildWC(val exercise: Exercise?) : BaseWC(), Parcelable {
    companion object {
        val type = MyActivityChildWC::class.java.hashCode()
    }

    override fun copy() = MyActivityChildWC(exercise?.copy()).apply { copyValuesIntoSuper(this@MyActivityChildWC) }
}