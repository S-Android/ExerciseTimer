package com.ht.exceciseinternal.widgets.pick_exercise

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ht.exceciseinternal.databinding.PickExerciseAdapterItemBinding
import com.ht.exceciseinternal.widgets.BaseWV

class PickExerciseWV(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): BaseWV<PickExerciseWC>(context, attrs, defStyleAttr) {
    private val binding: PickExerciseAdapterItemBinding by lazy {
        PickExerciseAdapterItemBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)
    }

    override fun updateView(config: PickExerciseWC) {
        super.updateView(config)

        binding.apply {
            pickExerciseNameActv.text = config.rawExercise?.name

            main.setOnClickListener {
                config.vmNotifier?.notify(PickExerciseWC.ACTION_RAW_EXERCISE_CLICK, config)
            }
        }
    }
}