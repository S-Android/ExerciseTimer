package com.ht.exceciseinternal.ui.exercise

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ht.exceciseinternal.R
import com.ht.exceciseinternal.base.BaseFragment
import com.ht.exceciseinternal.base.BaseAdapter
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.databinding.ExerciseFragmentBinding

class ExerciseFragment : BaseFragment() {

    companion object {
        private const val KEY_CIRCUIT = "key_circuit"

        fun newInstance(circuit: Circuit?) = ExerciseFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_CIRCUIT, circuit)
            }
        }
    }

    private lateinit var binding: ExerciseFragmentBinding
    private lateinit var viewModel: ExerciseVM
    private val baseAdapter = BaseAdapter()

    private val circuitNameTextWatcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(p0: Editable?) {
            viewModel.onCircuitNameChange(p0?.toString())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(activity!!).get(ExerciseVM::class.java)

        val circuit = arguments?.getParcelable<Circuit>(KEY_CIRCUIT)
        viewModel.resumeCircuitExercise(circuit)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ExerciseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpUI()

        setUpClickListeners()

        setUpObservers()
    }

    private fun setUpUI() {
        binding.apply {
            /** recycler view */
            circuitRv.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = baseAdapter
            }
        }
    }

    private fun setUpClickListeners() {
        binding.apply {
            circuitTiet.addTextChangedListener(circuitNameTextWatcher)

            saveAciv.setOnClickListener {
                viewModel.handleSaveCircuitClick()
            }
        }
    }

    private fun setUpObservers() {
        /** circuit name */
        viewModel.circuitNameLiveEvent.observe(viewLifecycleOwner) {
            binding.circuitTiet.setText(it)
        }

        /** save icon */
        viewModel.saveIconEnableLiveEvent.observe(viewLifecycleOwner) { enableSaveIcon ->
            binding.saveAciv.apply {
                isEnabled = enableSaveIcon

                val saveCTAIcon = if (enableSaveIcon) R.drawable.ic_single_tick_red else R.drawable.ic_single_tick_grey
                setImageResource(saveCTAIcon)
            }
        }

        /** exercise */
        viewModel.exerciseWCListLiveData.observe(viewLifecycleOwner) {
            baseAdapter.submitList(it)
        }

        /** back press */
        viewModel.closeCircuitExerciseScreenLiveEvent.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }
    }

}