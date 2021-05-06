package com.ht.exceciseinternal.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ht.exceciseinternal.base.BaseFragment
import com.ht.exceciseinternal.base.BaseAdapter
import com.ht.exceciseinternal.databinding.PickExerciseFragmentBinding

class PickExerciseFragment : BaseFragment() {

    companion object {
        fun newInstance() = PickExerciseFragment()
    }

    private lateinit var binding: PickExerciseFragmentBinding
    private lateinit var viewModel: ExerciseVM
    private val baseAdapter = BaseAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(activity!!).get(ExerciseVM::class.java)

        viewModel.resumeExercisePicker()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = PickExerciseFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setUpUI()

        setUpObservers()
    }

    private fun setUpUI() {
        binding.apply {
            /** recycler view */
            pickExerciseRv.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = baseAdapter
            }
        }
    }

    private fun setUpObservers() {
        /** raw exercise observer */
        viewModel.pickExerciseWCListLiveData.observe(viewLifecycleOwner) {
            baseAdapter.submitList(it)
        }

        /** back press */
        viewModel.closeExercisePickerScreenLiveEvent.observe(viewLifecycleOwner) {
            activity?.onBackPressed()
        }
    }
}