package com.ht.exceciseinternal.widgets.my_activity

import android.os.Parcelable
import com.ht.exceciseinternal.beans.MyActivity
import com.ht.exceciseinternal.widgets.BaseWC
import kotlinx.android.parcel.Parcelize

@Parcelize
class MyActivityWC(val myActivity: MyActivity?) : BaseWC(), Parcelable {
    companion object {
        val type = MyActivityWC::class.java.hashCode()
    }

    override fun copy() = MyActivityWC(myActivity?.copy()).apply { copyValuesIntoSuper(this@MyActivityWC) }
}