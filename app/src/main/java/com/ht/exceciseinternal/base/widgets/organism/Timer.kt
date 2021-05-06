package com.ht.exceciseinternal.base.widgets.organism

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.beans.Duration
import com.ht.exceciseinternal.databinding.TimerBinding
import com.ht.exceciseinternal.utility.format
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class Timer @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr), CoroutineScope {
    private val binding: TimerBinding by lazy {
        TimerBinding.inflate(LayoutInflater.from(context), this, true)
    }
    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job + CoroutineExceptionHandler { coroutineContext, throwable ->
            println(throwable.printStackTrace())
        }
    init {
        binding
    }

    private var circuit: Circuit? = null

    fun start(circuit: Circuit?, callback: ((exerciseName: String?, exerciseImageName: String?, isRest: Boolean) -> Unit)? = null) {
        this.circuit = circuit

        launch {
            startTimer(circuit, callback)
        }
    }

    private suspend fun startTimer(circuit: Circuit?, callback: ((exerciseName: String?, exerciseImageName: String?, isRest: Boolean) -> Unit)? = null) {
        binding.apply {
            for (round in 0 until (circuit?.noOfRounds ?: 0)) {
                val exerciseList = circuit?.exerciseList ?: return

                for (exercise in exerciseList) {
                    runOnUI { callback?.invoke(exercise.name, exercise.imageName, false) }

                    timer(exercise.exerciseDuration) { duration ->
                        val minStr = duration.min.format()
                        val secStr = duration.sec.format()

                        minActv.text = minStr
                        secActv.text = secStr
                    }

                    runOnUI { callback?.invoke(exercise.name, exercise.imageName, true) }
                    timer(exercise.restDuration) { duration ->
                        val minStr = duration.min.format()
                        val secStr = duration.sec.format()

                        minActv.text = minStr
                        secActv.text = secStr
                    }
                }
            }
        }
    }

    private suspend fun timer(duration: Duration, callback: (duration: Duration) -> Unit) {
        val min = duration.min ?: 0
        val sec = duration.sec ?: 0

        var minCount = 0
        var secCount = 0
        while (minCount <= min) {
            while (secCount <= sec) {
                val updatedDuration = Duration(min - minCount, sec - secCount)
                runOnUI { callback(updatedDuration) }

                delay(1000)
                secCount++
            }
            minCount++
        }
    }

    private suspend fun runOnUI(block: () -> Unit) {
        withContext(Dispatchers.Main) {
            block()
        }
    }
    fun stop() {
        job.cancel()
    }
}