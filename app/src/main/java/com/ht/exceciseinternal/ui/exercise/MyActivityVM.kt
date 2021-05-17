package com.ht.exceciseinternal.ui.exercise

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import com.ht.exceciseinternal.base.BaseVM
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.database.ExerciseDataBase
import com.ht.exceciseinternal.utility.SingleLiveEvent
import com.ht.exceciseinternal.widgets.BaseWC

class MyActivityVM(app : Application) : BaseVM(app) {

    val myActivityListLiveData = MediatorLiveData<MutableList<Circuit>>()
    val openCircuitScreenListLiveEvent = SingleLiveEvent<Nothing>() /** open circuits */

    private val interactor: ExerciseInteractor by lazy { ExerciseInteractor() }
    private val repository: ExerciseRepository by lazy { ExerciseRepository() }

    init {
        databaseObserver()
    }

    fun openCircuitScreen() {
        openCircuitScreenListLiveEvent.postValue(null)
    }

    private fun databaseObserver() {
        val liveData = ExerciseDataBase.getInstance(getApplication()).exerciseDao().getCircuits()
        myActivityListLiveData.addSource(liveData) {
//            val circuitWCList = interactor.getCircuitConfigList(it, this@MyActivityVM)
            myActivityListLiveData.postValue(it.toMutableList())
        }
    }
}