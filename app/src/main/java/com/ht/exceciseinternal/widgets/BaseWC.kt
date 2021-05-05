package com.ht.exceciseinternal.widgets

import android.os.Parcelable
import com.ht.exceciseinternal.base.VMNotifier

abstract class BaseWC: Parcelable {
    @Transient var vmNotifier: VMNotifier? = null

    abstract fun copy(): BaseWC

    open fun isSame(newConfig: BaseWC) = this.type() == newConfig.type()

    fun type(): Int {
        return this.javaClass.hashCode()
    }

    fun copyValuesIntoSuper(config: BaseWC) {
        this.vmNotifier = config.vmNotifier
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}