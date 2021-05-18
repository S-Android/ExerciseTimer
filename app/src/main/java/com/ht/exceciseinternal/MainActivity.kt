package com.ht.exceciseinternal

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ht.exceciseinternal.base.BaseActivity
import com.ht.exceciseinternal.ui.exercise.*

class MainActivity : BaseActivity() {
    private lateinit var viewModel: ExerciseVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        viewModel = ViewModelProvider(this).get(ExerciseVM::class.java)

        setUpObservers()

        viewModel.openCircuitScreen()
    }

    private fun setUpObservers() {
        /** circuit screen */
        viewModel.openCircuitScreenListLiveEvent.observe(this) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, CircuitFragment.newInstance())
                .commit()
        }

        /** circuit's exercise screen */
        viewModel.openCircuitExerciseScreenLiveEvent.observe(this) { circuit ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ExerciseFragment.newInstance(circuit))
                .addToBackStack(null)
                .commit()
        }

        /** circuit's timer screen */
        viewModel.openCircuitTimerScreenLiveEvent.observe(this) { circuit ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TimerFragment.newInstance(circuit))
                .addToBackStack(null)
                .commit()
        }

        /** pick exercise screen */
        viewModel.openExercisePickerScreenLiveEvent.observe(this) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PickExerciseFragment.newInstance())
                .addToBackStack(null)
                .commit()
        }

        /** open my-activity screen */
        viewModel.openMyActivityScreenLiveEvent.observe(this) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MyActivityFragment.newInstance())
                    .addToBackStack(null)
                    .commit()
        }
    }
}