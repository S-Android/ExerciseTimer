package com.ht.exceciseinternal.base.widgets.organism

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.beans.Duration
import com.ht.exceciseinternal.databinding.TimerBinding
import com.ht.exceciseinternal.utility.format
import com.ht.exceciseinternal.utility.getRawPath
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

    private val mediaPlayer =  MediaPlayer().apply {
        setAudioStreamType(AudioManager.STREAM_MUSIC)
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

                var audioDuration = prepareMedia(exerciseList.getOrNull(0)?.exerciseAudio)
                if (audioDuration != null) {
                    mediaPlayer.start()
                    delay(audioDuration)
                }

                for ((index, exercise) in exerciseList.withIndex()) {
                    audioDuration = prepareMedia("rest")

                    runOnUI { callback?.invoke(exercise.name, exercise.imageName, false) }
                    timer(exercise.exerciseDuration) { duration ->
                        val minStr = duration.min.format()
                        val secStr = duration.sec.format()

                        minActv.text = minStr
                        secActv.text = secStr

                        if (duration.min == 0L && duration.sec.isLessThanEquals(audioDuration)) {
                            mediaPlayer.start()
                        }
                    }

                    audioDuration = prepareMedia(exerciseList.getOrNull(index + 1)?.exerciseAudio)

                    runOnUI { callback?.invoke(exercise.name, exercise.imageName, true) }
                    timer(exercise.restDuration) { duration ->
                        val minStr = duration.min.format()
                        val secStr = duration.sec.format()

                        minActv.text = minStr
                        secActv.text = secStr

                        if (duration.min == 0L && duration.sec.isLessThanEquals(audioDuration)) {
                            mediaPlayer.start()
                        }
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

    private fun prepareMedia(path: String?): Long? {
        return try {
            mediaPlayer.reset()
            mediaPlayer.setDataSource(context, Uri.parse(path.getRawPath()), null)
            mediaPlayer.prepare()

            return mediaPlayer.duration.toLong()
        } catch (ex: Exception) {
            null
        }
    }

    private fun Long?.isLessThanEquals(duration: Long?) = (this != null && duration != null && this * 1000 <= duration)

    fun stop() {
        job.cancel()
        mediaPlayer.stop()
    }
}