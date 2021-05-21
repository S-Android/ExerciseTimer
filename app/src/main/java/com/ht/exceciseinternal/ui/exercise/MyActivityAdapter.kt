package com.ht.exceciseinternal.ui.exercise

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ht.exceciseinternal.beans.MyActivity
import com.ht.exceciseinternal.databinding.MyActivityAdapterItemBinding
import com.ht.exceciseinternal.utility.Utils.getFormattedTime

class MyActivityAdapter :
        ListAdapter<MyActivity, MyActivityAdapter.MyActivityAdapterViewHolder>(
                DiffCallback()
        ) {

    companion object {
        val myActivityParentAdapter = MyActivityParentAdapter()
    }

    class DiffCallback : DiffUtil.ItemCallback<MyActivity>() {
        override fun areContentsTheSame(oldItem: MyActivity, newItem: MyActivity) = oldItem == newItem
        override fun areItemsTheSame(oldItem: MyActivity, newItem: MyActivity) = oldItem.circuits == newItem.circuits
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyActivityAdapterViewHolder {
        val binding = MyActivityAdapterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyActivityAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyActivityAdapterViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    class MyActivityAdapterViewHolder(private val binding: MyActivityAdapterItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(myActivity: MyActivity, position: Int) {
            binding.apply {
                currentDateTv.text = getFormattedTime(myActivity.date)
                myActivityCircuitsRv.apply {
                    layoutManager = LinearLayoutManager(binding.root.context)
                    adapter = myActivityParentAdapter
                    myActivityParentAdapter.submitList(myActivity.circuits)
                }
            }
        }
    }
}