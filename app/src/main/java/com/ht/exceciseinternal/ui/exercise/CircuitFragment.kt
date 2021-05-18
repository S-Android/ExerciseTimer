package com.ht.exceciseinternal.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ht.exceciseinternal.base.BaseFragment
import com.ht.exceciseinternal.base.BaseAdapter
import com.ht.exceciseinternal.databinding.CircuitFragmentBinding

class CircuitFragment : BaseFragment() {

    companion object {
        fun newInstance() = CircuitFragment()
    }

    private lateinit var binding: CircuitFragmentBinding
    private lateinit var viewModel: ExerciseVM
    private val baseAdapter = BaseAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(activity!!).get(ExerciseVM::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = CircuitFragmentBinding.inflate(inflater, container, false)
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
            /** recycler view */
            circuitRv.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = baseAdapter
            }
        }
    }

    private fun setUpOnClickListeners() {
        binding.apply {
            /** fab click */
            addFab.setOnClickListener {
                viewModel.onFabClick()
            }

            /** profile click */
            myActivityAciv.setOnClickListener {
                viewModel.onMyActivityClick()
            }
        }
    }

    private fun setUpObservers() {
        /** circuit observer */
        viewModel.circuitListLiveData.observe(viewLifecycleOwner) {
            baseAdapter.submitList(it)
        }
    }
}