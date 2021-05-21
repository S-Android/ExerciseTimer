package com.ht.exceciseinternal.ui.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.beans.MyActivity
import com.ht.exceciseinternal.databinding.MyActivityAdapterItemBinding
import com.ht.exceciseinternal.databinding.MyActivityAdapterParentItemBinding
import com.ht.exceciseinternal.widgets.BaseWC
import com.ht.exceciseinternal.widgets.my_activity.MyActivityWC

class MyActivityParentAdapter :
        ListAdapter<Circuit, MyActivityParentAdapter.MyActivityParentAdapterViewHolder>(
                DiffCallback()
        ) {

    companion object {
        val myActivityChildAdapter = MyActivityChildAdapter()
    }

    class DiffCallback : DiffUtil.ItemCallback<Circuit>() {
        override fun areContentsTheSame(oldItem: Circuit, newItem: Circuit) = oldItem == newItem
        override fun areItemsTheSame(oldItem: Circuit, newItem: Circuit) = oldItem.name == newItem.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyActivityParentAdapterViewHolder {
        val binding = MyActivityAdapterParentItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyActivityParentAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyActivityParentAdapterViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    class MyActivityParentAdapterViewHolder(private val binding: MyActivityAdapterParentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(circuit: Circuit, position: Int) {
            binding.apply {
                myActivityCircuitTiet.text = circuit.name
                myActivityCircuitDetailRv.apply {
                    layoutManager = LinearLayoutManager(binding.root.context)
                    adapter = myActivityChildAdapter
                    myActivityChildAdapter.submitList(circuit.exerciseList)
                }
            }
        }
    }
}