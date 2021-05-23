package com.ht.exceciseinternal.widgets.my_activity_parent

import android.os.Parcelable
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.widgets.BaseWC
import com.ht.exceciseinternal.widgets.my_activity_child.MyActivityChildWC
import kotlinx.android.parcel.Parcelize

@Parcelize
class MyActivityParentWC(val circuit: Circuit?) : BaseWC(), Parcelable {

    val myActivityChildWCList : List<BaseWC> by lazy {
        val list = mutableListOf<BaseWC>()

        for (exercise in circuit?.exerciseList ?: mutableListOf()) {
            list.add(
                    MyActivityChildWC(exercise).apply {
                        vmNotifier = this@MyActivityParentWC.vmNotifier
                    }
            )
        }

        list
    }

    companion object {
        val type = MyActivityParentWC::class.java.hashCode()
    }

    override fun copy() = MyActivityParentWC(circuit?.copy()).apply { copyValuesIntoSuper(this@MyActivityParentWC) }
}