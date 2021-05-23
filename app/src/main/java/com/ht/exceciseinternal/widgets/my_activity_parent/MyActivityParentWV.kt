package com.ht.exceciseinternal.widgets.my_activity_parent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.ht.exceciseinternal.base.BaseAdapter
import com.ht.exceciseinternal.databinding.MyActivityAdapterParentItemBinding
import com.ht.exceciseinternal.widgets.BaseWV

class MyActivityParentWV(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): BaseWV<MyActivityParentWC>(context, attrs, defStyleAttr) {
    private val binding: MyActivityAdapterParentItemBinding by lazy {
        MyActivityAdapterParentItemBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)
    }

    override fun updateView(config: MyActivityParentWC) {
        super.updateView(config)

        binding.apply {
            myActivityCircuitTiet.text = config.circuit?.name
            val baseAdapter = BaseAdapter()
            myActivityCircuitDetailRv.apply {
                layoutManager = LinearLayoutManager(binding.root.context)
                adapter = baseAdapter
                // what's the difference convert to (run and with ) ?
                baseAdapter.submitList(config.myActivityChildWCList)
            }

        }
    }
}