package com.ht.exceciseinternal.widgets

import androidx.recyclerview.widget.RecyclerView

class BaseWVH<View, Config: BaseWC> (private val view: View): RecyclerView.ViewHolder(view) where View : BaseWV<Config> {
    fun updateView(config: Config) {
        view.updateView(config)
    }
}