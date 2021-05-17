package com.ht.exceciseinternal.widgets.my_activity

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ht.exceciseinternal.databinding.MyActivityAdapterItemBinding
import com.ht.exceciseinternal.widgets.BaseWV

class MyActivityWV(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): BaseWV<MyActivityWC>(context, attrs, defStyleAttr) {
    private val binding: MyActivityAdapterItemBinding by lazy {
        MyActivityAdapterItemBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)
    }

    override fun updateView(config: MyActivityWC) {
        super.updateView(config)

        binding.apply {
           myActivityCircuitTiet.text = config.circuit?.name

        }
    }
}