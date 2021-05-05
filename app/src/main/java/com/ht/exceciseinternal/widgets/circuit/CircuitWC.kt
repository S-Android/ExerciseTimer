package com.ht.exceciseinternal.widgets.circuit

import android.os.Parcelable
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.beans.Exercise
import com.ht.exceciseinternal.widgets.BaseWC
import kotlinx.android.parcel.Parcelize

@Parcelize
class CircuitWC(val circuit: Circuit?) : BaseWC(), Parcelable {
    companion object {
        val type = CircuitWC::class.java.hashCode()
        const val ACTION_CIRCUIT_CLICK = "action_circuit_click"
        const val ACTION_CIRCUIT_LONG_CLICK = "action_circuit_long_click"
    }

    override fun copy() = CircuitWC(circuit?.copy()).apply { copyValuesIntoSuper(this@CircuitWC) }
}