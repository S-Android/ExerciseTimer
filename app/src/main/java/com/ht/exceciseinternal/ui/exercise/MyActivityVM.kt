package com.ht.exceciseinternal.ui.exercise

import android.app.Application
import androidx.lifecycle.MediatorLiveData
import com.ht.exceciseinternal.base.BaseVM
import com.ht.exceciseinternal.beans.MyActivity
import com.ht.exceciseinternal.database.ExerciseDataBase
import com.ht.exceciseinternal.widgets.BaseWC

class MyActivityVM(app : Application) : BaseVM(app) {

    val myActivityListLiveData = MediatorLiveData<MutableList<MyActivity>>()

    private val interactor: MyActivityInteractor by lazy { MyActivityInteractor() }

    init {
        databaseObserver()
    }

    private fun databaseObserver() {
        val myActivitiesLiveData = ExerciseDataBase.getInstance(getApplication()).exerciseDao().getMyActivities()
        myActivityListLiveData.addSource(myActivitiesLiveData) {
//            val myActivityWCList = interactor.getMyActivityConfigList(it, this@MyActivityVM)
            myActivityListLiveData.postValue(it.toMutableList())
        }
    }
}