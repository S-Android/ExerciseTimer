package com.ht.exceciseinternal.widgets.my_activity

import android.os.Parcelable
import com.ht.exceciseinternal.beans.MyActivity
import com.ht.exceciseinternal.widgets.BaseWC
import com.ht.exceciseinternal.widgets.my_activity_parent.MyActivityParentWC
import kotlinx.android.parcel.Parcelize

@Parcelize
class MyActivityWC(val myActivity: MyActivity?) : BaseWC(), Parcelable {

    val myActivityParentWCList : List<BaseWC> by lazy {
        val list = mutableListOf<BaseWC>()

        for (circuit in myActivity?.circuits ?: mutableListOf()) {
            list.add(
                    MyActivityParentWC(circuit).apply {
                        vmNotifier = this@MyActivityWC.vmNotifier
                    }
            )
        }

        list
    }
    companion object {
        val type = MyActivityWC::class.java.hashCode()
    }

    override fun copy() = MyActivityWC(myActivity?.copy()).apply { copyValuesIntoSuper(this@MyActivityWC) }
}