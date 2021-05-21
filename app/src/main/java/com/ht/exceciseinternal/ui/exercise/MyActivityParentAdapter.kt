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
import com.ht.exceciseinternal.widgets.BaseWC
import com.ht.exceciseinternal.widgets.my_activity.MyActivityWC

class MyActivityParentAdapter :
        ListAdapter<MyActivity, MyActivityParentAdapter.MyActivityParentAdapterViewHolder>(
                DiffCallback()
        ) {

    companion object {
        val myActivityChildAdapter = MyActivityChildAdapter()
    }

    class DiffCallback : DiffUtil.ItemCallback<MyActivity>() {
        override fun areContentsTheSame(oldItem: MyActivity, newItem: MyActivity) = oldItem == newItem
        override fun areItemsTheSame(oldItem: MyActivity, newItem: MyActivity) = oldItem.circuits == newItem.circuits
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyActivityParentAdapterViewHolder {
        val binding = MyActivityAdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyActivityParentAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyActivityParentAdapterViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    class MyActivityParentAdapterViewHolder(private val binding: MyActivityAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(myActivity: MyActivity, position: Int) {
            binding.apply {
                currentDateTv.text = myActivity.date.toString()
                myActivityCircuitTiet.text = myActivity.circuits!![position].name
                myActivityCircuitDetailRv.apply {
                    layoutManager = LinearLayoutManager(binding.root.context)
                    adapter = myActivityChildAdapter
                    myActivityChildAdapter.submitList(myActivity.circuits[position].exerciseList)
                }
            }
        }
    }
}