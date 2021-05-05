package com.ht.exceciseinternal.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.ht.exceciseinternal.BuildConfig
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

open class BaseVM(app: Application): AndroidViewModel(app), VMNotifier {
    protected val coroutineContext = Dispatchers.Default + CoroutineExceptionHandler{ _, th ->
        if (BuildConfig.DEBUG) { th.printStackTrace() }
    }
    override fun <T> notify(actionType: String, actionData: T?) {}
}