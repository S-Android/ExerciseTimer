package com.ht.exceciseinternal.widgets.my_activity_child

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ht.exceciseinternal.databinding.MyActivityDetailItemBinding
import com.ht.exceciseinternal.widgets.BaseWV

class MyActivityChildWV(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): BaseWV<MyActivityChildWC>(context, attrs, defStyleAttr) {
    private val binding: MyActivityDetailItemBinding by lazy {
        MyActivityDetailItemBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)
    }

    override fun updateView(config: MyActivityChildWC) {
        super.updateView(config)

        binding.apply {
            myActivityExerciseTiet.text = config.exercise?.name
            myActivityExerciseDurationMinTiet.text = config.exercise?.exerciseDuration?.min.toString()
            myActivityExerciseDurationSecTiet.text = config.exercise?.exerciseDuration?.sec.toString()
        }
    }
}