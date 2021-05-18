package com.ht.exceciseinternal.ui.exercise

import com.ht.exceciseinternal.base.BaseInteractor
import com.ht.exceciseinternal.base.VMNotifier
import com.ht.exceciseinternal.beans.MyActivity
import com.ht.exceciseinternal.widgets.BaseWC
import com.ht.exceciseinternal.widgets.my_activity.MyActivityWC


class MyActivityInteractor: BaseInteractor() {
    fun getMyActivityConfigList(myActivityList: List<MyActivity>?, vmNotifier: VMNotifier): MutableList<BaseWC> {
        val returnValue = mutableListOf<BaseWC>()

        for (activity in myActivityList ?: mutableListOf()) {
            returnValue.add(MyActivityWC(activity).apply { this.vmNotifier = vmNotifier })
        }

        return returnValue
    }
}