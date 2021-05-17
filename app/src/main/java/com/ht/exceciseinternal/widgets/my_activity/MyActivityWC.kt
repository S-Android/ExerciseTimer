package com.ht.exceciseinternal.widgets.my_activity

import android.os.Parcelable
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.widgets.BaseWC
import kotlinx.android.parcel.Parcelize

@Parcelize
class MyActivityWC(val circuit: Circuit?) : BaseWC(), Parcelable {
    companion object {
        val type = MyActivityWC::class.java.hashCode()
    }

    override fun copy() = MyActivityWC(circuit?.copy()).apply { copyValuesIntoSuper(this@MyActivityWC) }
}