package com.ht.exceciseinternal.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.ht.exceciseinternal.R
import com.ht.exceciseinternal.base.BaseFragment
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.databinding.TimerFragmentBinding
import com.ht.exceciseinternal.utility.getDrawable

class TimerFragment : BaseFragment() {

    companion object {
        private const val KEY_CIRCUIT = "key_circuit"

        fun newInstance(circuit: Circuit?) = TimerFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_CIRCUIT, circuit)
            }
        }
    }

    private lateinit var binding: TimerFragmentBinding
    private lateinit var viewModel: ExerciseVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(activity!!).get(ExerciseVM::class.java)

        val circuit = arguments?.getParcelable<Circuit>(KEY_CIRCUIT)
        viewModel.resumeCircuitTimer(circuit)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = TimerFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpUI()

        setUpOnClickListeners()

        setUpObservers()
    }

    private fun setUpUI() {
        binding.apply {

        }
    }

    private fun setUpOnClickListeners() {
        binding.apply {

        }
    }

    private fun setUpObservers() {
        /** resume the timer */
        viewModel.startCircuitLiveEvent.observe(viewLifecycleOwner) { circuit ->
            updateUI(circuit)
        }
    }

    private fun updateUI(circuit: Circuit?) {
        binding.apply {
            /** circuit name */
            circuitActv.text = circuit?.name

            /** timer */
            timer.start(circuit) { exerciseName, exerciseImageName, isRest ->
                /** exercise name */
                exerciseActv.text = if (isRest) "" else exerciseName

                /** exercise image */
                val drawable = if (isRest) ContextCompat.getDrawable(context!!, R.mipmap.rest_bg) else exerciseImageName.getDrawable()
                if (drawable != null) { exerciseAciv.setImageDrawable(drawable) }

//                /** type */
//                typeActv.text = if (isRest) "REST" else "EXERCISE"
            }
        }
    }

    override fun onStop() {
        super.onStop()

        binding.timer.stop()
    }
}