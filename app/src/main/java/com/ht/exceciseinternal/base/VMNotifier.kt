package com.ht.exceciseinternal.base

interface VMNotifier {
    fun <T> notify(actionType: String, actionData: T? = null)
}