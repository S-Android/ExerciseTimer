package com.ht.exceciseinternal.widgets.add_excecise

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ht.exceciseinternal.databinding.AddExerciseAdapterItemBinding
import com.ht.exceciseinternal.widgets.BaseWV

class AddExerciseWV(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): BaseWV<AddExerciseWC>(context, attrs, defStyleAttr) {
    private val binding: AddExerciseAdapterItemBinding by lazy {
        AddExerciseAdapterItemBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)
    }

    override fun updateView(config: AddExerciseWC) {
        super.updateView(config)

        binding.main.setOnClickListener {
            config.vmNotifier?.notify(AddExerciseWC.ACTION_ADD_EXERCISE_CLICK, null)
        }
    }
}