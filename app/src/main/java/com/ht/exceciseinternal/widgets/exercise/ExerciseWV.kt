package com.ht.exceciseinternal.widgets.exercise

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.ht.exceciseinternal.databinding.ExerciseAdapterItemBinding
import com.ht.exceciseinternal.widgets.BaseWV

class ExerciseWV(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): BaseWV<ExerciseWC>(context, attrs, defStyleAttr) {
    private val binding: ExerciseAdapterItemBinding by lazy {
        ExerciseAdapterItemBinding.inflate(LayoutInflater.from(context), this, false)
    }

    init {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        addView(binding.root)
    }

    private val exerciseNameFocusListener = OnFocusChangeListener { view, hasFocus ->
        if (hasFocus) {
            this@ExerciseWV.config?.vmNotifier?.notify(ExerciseWC.ACTION_EXERCISE_PICK, config)
        }
    }
    private val exerciseNameWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            val copyConfig = this@ExerciseWV.config.let { copyConfig ->
                copyConfig?.exercise?.name = editable?.toString()
                copyConfig
            }
            this@ExerciseWV.config?.vmNotifier?.notify(ExerciseWC.ACTION_EXERCISE_UPDATE, copyConfig)
        }
    }

    private val exerciseMinWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            val min = try {
                editable?.toString()?.toLong()
            } catch (ex: Exception) {
                null
            }
            val copyConfig = this@ExerciseWV.config.let { copyConfig ->
                copyConfig?.exercise?.exerciseDuration?.min = min
                copyConfig
            }
            this@ExerciseWV.config?.vmNotifier?.notify(ExerciseWC.ACTION_EXERCISE_UPDATE, copyConfig)
        }
    }

    private val exerciseSecWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            val sec = try {
                editable?.toString()?.toLong()
            } catch (ex: Exception) {
                null
            }
            val copyConfig = this@ExerciseWV.config.let { copyConfig ->
                copyConfig?.exercise?.exerciseDuration?.sec = sec
                copyConfig
            }
            this@ExerciseWV.config?.vmNotifier?.notify(ExerciseWC.ACTION_EXERCISE_UPDATE, copyConfig)
        }
    }

    private val restMinWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            val min = try {
                editable?.toString()?.toLong()
            } catch (ex: Exception) {
                null
            }
            val copyConfig = this@ExerciseWV.config.let { copyConfig ->
                copyConfig?.exercise?.restDuration?.min = min
                copyConfig
            }
            this@ExerciseWV.config?.vmNotifier?.notify(ExerciseWC.ACTION_EXERCISE_UPDATE, copyConfig)
        }
    }

    private val restSecWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(editable: Editable?) {
            val sec = try {
                editable?.toString()?.toLong()
            } catch (ex: Exception) {
                null
            }
            val copyConfig = this@ExerciseWV.config.let { copyConfig ->
                copyConfig?.exercise?.restDuration?.sec = sec
                copyConfig
            }
            this@ExerciseWV.config?.vmNotifier?.notify(ExerciseWC.ACTION_EXERCISE_UPDATE, copyConfig)
        }
    }


    override fun updateView(config: ExerciseWC) {
        super.updateView(config)

        binding.apply {
            val exercise = config.exercise ?: return

            /** name */
            exerciseTiet.apply {
                onFocusChangeListener = null
//                removeTextChangedListener(exerciseNameWatcher)

                setText(exercise.name ?: "")

//                addTextChangedListener(exerciseNameWatcher)

                onFocusChangeListener = exerciseNameFocusListener
            }

            /** exercise min */
            repDurationMinTiet.apply {
                removeTextChangedListener(exerciseMinWatcher)

                setText(exercise.exerciseDuration.min?.toString() ?: "")

                addTextChangedListener(exerciseMinWatcher)
            }

            /** exercise sec */
            repDurationSecTiet.apply {
                removeTextChangedListener(exerciseSecWatcher)

                setText(exercise.exerciseDuration.sec?.toString() ?: "")

                addTextChangedListener(exerciseSecWatcher)
            }

            /** rest min */
            repRestMinTiet.apply {
                removeTextChangedListener(restMinWatcher)

                setText(exercise.restDuration.min?.toString() ?: "")

                addTextChangedListener(restMinWatcher)
            }

            /** reset sec */
            repRestSecTiet.apply {
                removeTextChangedListener(restSecWatcher)

                setText(exercise.restDuration.sec?.toString() ?: "")

                addTextChangedListener(restSecWatcher)
            }

            /** delete */
            deleteAciv.setOnClickListener {
                this@ExerciseWV.config?.vmNotifier?.notify(ExerciseWC.ACTION_EXERCISE_DELETE, this@ExerciseWV.config)
            }
        }
    }
}