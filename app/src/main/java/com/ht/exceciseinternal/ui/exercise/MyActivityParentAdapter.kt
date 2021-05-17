package com.ht.exceciseinternal.ui.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ht.exceciseinternal.beans.Circuit
import com.ht.exceciseinternal.databinding.MyActivityAdapterItemBinding

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
        val binding = MyActivityAdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyActivityParentAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyActivityParentAdapterViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class MyActivityParentAdapterViewHolder(private val binding: MyActivityAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(circuit: Circuit) {
            binding.apply {
                currentDateTv.text = "May 16 2021"
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