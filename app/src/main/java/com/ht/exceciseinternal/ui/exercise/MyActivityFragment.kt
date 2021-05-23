package com.ht.exceciseinternal.ui.exercise

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ht.exceciseinternal.base.BaseAdapter
import com.ht.exceciseinternal.base.BaseFragment
import com.ht.exceciseinternal.databinding.MyActivityFragmentBinding

class MyActivityFragment : BaseFragment() {

    companion object {
        fun newInstance() = MyActivityFragment()
    }

    private lateinit var binding: MyActivityFragmentBinding
    private lateinit var viewModel: MyActivityVM
    private val baseAdapter = BaseAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MyActivityVM::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = MyActivityFragmentBinding.inflate(inflater, container, false)
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
            myActivityCircuitRv.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = baseAdapter
            }
        }
    }

    private fun setUpObservers() {
        /** circuit observer */
        viewModel.myActivityListLiveData.observe(viewLifecycleOwner) {
            baseAdapter.submitList(it.toMutableList())
        }
    }
}