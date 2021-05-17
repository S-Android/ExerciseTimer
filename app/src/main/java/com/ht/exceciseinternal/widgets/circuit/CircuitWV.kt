package com.ht.exceciseinternal.widgets.circuit

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.ht.exceciseinternal.databinding.CircuitAdapterItemBinding
import com.ht.exceciseinternal.widgets.BaseWV
import com.ht.exceciseinternal.widgets.exercise.ExerciseWC

class CircuitWV(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): BaseWV<CircuitWC>(context, attrs, defStyleAttr) {
    private val binding: CircuitAdapterItemBinding by lazy {
        CircuitAdapterItemBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)
    }

    override fun updateView(config: CircuitWC) {
        super.updateView(config)

        binding.apply {
            circuitNameActv.text = config.circuit?.name

            main.setOnClickListener {
                config.vmNotifier?.notify(CircuitWC.ACTION_CIRCUIT_CLICK, config)
            }

            main.setOnLongClickListener {
                config.vmNotifier?.notify(CircuitWC.ACTION_CIRCUIT_LONG_CLICK, config)
                true
            }

            /** delete */
            deleteAciv.setOnClickListener {
                config.vmNotifier?.notify(CircuitWC.ACTION_CIRCUIT_DELETE, config)
            }
        }
    }
}